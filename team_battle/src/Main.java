import java.net.*;
import java.io.*;
import java.util.*;

public class Main {
    /////////////////////////////////
    // 메인 프로그램 통신 변수 정의
    /////////////////////////////////
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8747;
    private static String ARGS = "";
    private static Socket socket = null;

    ///////////////////////////////
    // 입력 데이터 변수 정의
    ///////////////////////////////
    private static String[][] mapData;
    private static Map<String, String[]> myAllies = new HashMap<>();
    private static Map<String, String[]> enemies = new HashMap<>();
    private static String[] codes;

    private static final int[][] DIRS = {{0,1}, {1,0}, {0,-1}, {-1,0}}; // R D L U
    private static final String[] MOVE_CMDS = {"R A", "D A", "L A", "U A"};
    private static final String[] FIRE_CMDS = {"R F", "D F", "L F", "U F"};
    private static final int RANGE = 3;

    public static void main(String[] args) {
        ARGS = args.length > 0 ? args[0] : "";

        String NICKNAME = "원거리포탑헌터";
        String gameData = init(NICKNAME);

        if (gameData == null || gameData.length() == 0) {
            close();
            return;
        }

        parseData(gameData);

        while (gameData != null && gameData.length() > 0) {
            printData(gameData);

            String output = decideAction();
            gameData = submit(output);

            if (gameData != null && gameData.length() > 0) {
                parseData(gameData);
            }
        }

        close();
    }

    ////////////////////////////////////
    // 알고리즘 함수/메서드 부분 구현 시작
    ////////////////////////////////////

    private static String decideAction() {
        int[] me = findSymbol("M");
        int[] tower = findSymbol("X");

        if (me == null) return "S";

        // 1. 사거리 3 이내 적 탱크가 보이면 탱크 우선 공격
        String enemyTankShot = getShootCommandInRange(me, true, false);
        if (enemyTankShot != null) return enemyTankShot;

        // 2. 사거리 3 이내 적 포탑이 보이면 포탑 공격
        String towerShot = getShootCommandInRange(me, false, true);
        if (towerShot != null) return towerShot;

        // 3. 포탑까지 가는 경로를 구하고 그 경로 위 적 탱크를 우선 목표로 선정
        if (tower != null) {
            List<int[]> pathToTowerSight = bfsToShootPosition(me, tower, false);
            if (pathToTowerSight != null && !pathToTowerSight.isEmpty()) {
                int[] enemyOnPath = findEnemyTankOnPath(pathToTowerSight);
                if (enemyOnPath != null) {
                    String act = moveToShootPositionOrBreakTree(me, enemyOnPath, true);
                    if (act != null) return act;
                }

                String act = moveToShootPositionOrBreakTree(me, tower, false);
                if (act != null) return act;
            }
        }

        // 4. 포탑 쪽이 어렵다면 가장 가까운 적 탱크부터 정리
        int[] nearestEnemy = findNearestEnemyTank(me);
        if (nearestEnemy != null) {
            String act = moveToShootPositionOrBreakTree(me, nearestEnemy, true);
            if (act != null) return act;
        }

        // 5. 할 수 있는 게 없으면 대기
        return "S";
    }

    // 목표를 사거리 3 이내 직선 공격 가능한 위치까지 이동
    // 다음 칸이 나무면 쏴서 제거
    private static String moveToShootPositionOrBreakTree(int[] me, int[] target, boolean enemyTank) {
        // 이미 쏠 수 있으면 발사
        String canShootNow = getShootCommandToSpecificTarget(me, target[0], target[1]);
        if (canShootNow != null) return canShootNow;

        List<int[]> path = bfsToShootPosition(me, target, enemyTank);
        if (path == null || path.size() < 2) return null;

        int[] next = path.get(1);
        int dir = getDirection(me[0], me[1], next[0], next[1]);
        String nextCell = mapData[next[0]][next[1]];

        if ("T".equals(nextCell)) {
            return FIRE_CMDS[dir];
        }

        if (isPassableForTank(next[0], next[1])) {
            return MOVE_CMDS[dir];
        }

        return null;
    }

    // 목표를 사거리 3 내 직선 공격 가능한 위치까지의 BFS
    private static List<int[]> bfsToShootPosition(int[] start, int[] target, boolean enemyTank) {
        int rows = mapData.length;
        int cols = mapData[0].length;

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];
        int[][][] prev = new int[rows][cols][2];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                prev[i][j][0] = -1;
                prev[i][j][1] = -1;
            }
        }

        q.offer(start);
        visited[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            String shootCmd = getShootCommandToSpecificTarget(new int[]{r, c}, target[0], target[1]);
            if (shootCmd != null) {
                return reconstructPath(prev, start, cur);
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + DIRS[d][0];
                int nc = c + DIRS[d][1];

                if (!inRange(nr, nc) || visited[nr][nc]) continue;

                // 목표 칸 그 자체는 올라가지 않음
                if (nr == target[0] && nc == target[1]) continue;

                if (isPassableForTank(nr, nc) || "T".equals(mapData[nr][nc])) {
                    visited[nr][nc] = true;
                    prev[nr][nc][0] = r;
                    prev[nr][nc][1] = c;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        return null;
    }

    // 현재 위치에서 사거리 내 적 탱크/포탑 사격 가능 여부
    private static String getShootCommandInRange(int[] me, boolean enemyTankOnly, boolean towerOnly) {
        for (int d = 0; d < 4; d++) {
            for (int dist = 1; dist <= RANGE; dist++) {
                int nr = me[0] + DIRS[d][0] * dist;
                int nc = me[1] + DIRS[d][1] * dist;

                if (!inRange(nr, nc)) break;

                String cell = mapData[nr][nc];

                // 포탄 통과 가능한 지형
                if ("G".equals(cell) || "S".equals(cell) || "W".equals(cell)) {
                    continue;
                }

                // 적 탱크
                if (isEnemyTank(cell)) {
                    if (enemyTankOnly) return FIRE_CMDS[d];
                    break;
                }

                // 적 포탑
                if ("X".equals(cell)) {
                    if (towerOnly) return FIRE_CMDS[d];
                    break;
                }

                // 나무/바위/보급시설/아군/기타 유닛은 탄도 차단
                break;
            }
        }
        return null;
    }

    // 현재 위치에서 특정 목표를 사거리 3 이내 직선으로 공격 가능한지 확인
    private static String getShootCommandToSpecificTarget(int[] me, int tr, int tc) {
        for (int d = 0; d < 4; d++) {
            for (int dist = 1; dist <= RANGE; dist++) {
                int nr = me[0] + DIRS[d][0] * dist;
                int nc = me[1] + DIRS[d][1] * dist;

                if (!inRange(nr, nc)) break;

                String cell = mapData[nr][nc];

                if (nr == tr && nc == tc) {
                    return FIRE_CMDS[d];
                }

                // 포탄 통과 가능 지형
                if ("G".equals(cell) || "S".equals(cell) || "W".equals(cell)) {
                    continue;
                }

                // 목표가 아닌 다른 무언가에 막힘
                break;
            }
        }
        return null;
    }

    private static List<int[]> reconstructPath(int[][][] prev, int[] start, int[] end) {
        LinkedList<int[]> path = new LinkedList<>();
        int cr = end[0];
        int cc = end[1];

        while (!(cr == start[0] && cc == start[1])) {
            path.addFirst(new int[]{cr, cc});
            int pr = prev[cr][cc][0];
            int pc = prev[cr][cc][1];
            if (pr == -1 && pc == -1) return null;
            cr = pr;
            cc = pc;
        }
        path.addFirst(new int[]{start[0], start[1]});
        return path;
    }

    // 포탑으로 가는 경로에 적 탱크가 있는지 검사
    private static int[] findEnemyTankOnPath(List<int[]> path) {
        for (int[] p : path) {
            for (int d = 0; d < 4; d++) {
                for (int dist = 1; dist <= RANGE; dist++) {
                    int nr = p[0] + DIRS[d][0] * dist;
                    int nc = p[1] + DIRS[d][1] * dist;
                    if (!inRange(nr, nc)) break;

                    String cell = mapData[nr][nc];

                    if (isEnemyTank(cell)) {
                        return new int[]{nr, nc};
                    }

                    if ("G".equals(cell) || "S".equals(cell) || "W".equals(cell)) {
                        continue;
                    }

                    break;
                }
            }
        }
        return null;
    }

    private static int[] findNearestEnemyTank(int[] me) {
        int minDist = Integer.MAX_VALUE;
        int[] result = null;

        for (int i = 0; i < mapData.length; i++) {
            for (int j = 0; j < mapData[0].length; j++) {
                if (isEnemyTank(mapData[i][j])) {
                    int dist = Math.abs(me[0] - i) + Math.abs(me[1] - j);
                    if (dist < minDist) {
                        minDist = dist;
                        result = new int[]{i, j};
                    }
                }
            }
        }
        return result;
    }

    private static boolean isEnemyTank(String s) {
        return "E1".equals(s) || "E2".equals(s) || "E3".equals(s);
    }

    private static boolean isMyUnit(String s) {
        return "M".equals(s) || "H".equals(s) || "M1".equals(s) || "M2".equals(s) || "M3".equals(s);
    }

    private static boolean isEnemyUnit(String s) {
        return isEnemyTank(s) || "X".equals(s);
    }

    private static boolean isPassableForTank(int r, int c) {
        String cell = mapData[r][c];
        if (cell == null) return false;

        if ("G".equals(cell) || "S".equals(cell)) return true;

        if ("W".equals(cell) || "R".equals(cell) || "F".equals(cell) || "T".equals(cell)) return false;
        if (isMyUnit(cell) || isEnemyUnit(cell)) return false;

        return false;
    }

    private static int[] findSymbol(String symbol) {
        for (int i = 0; i < mapData.length; i++) {
            for (int j = 0; j < mapData[0].length; j++) {
                if (symbol.equals(mapData[i][j])) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private static int getDirection(int r, int c, int nr, int nc) {
        for (int d = 0; d < 4; d++) {
            if (r + DIRS[d][0] == nr && c + DIRS[d][1] == nc) {
                return d;
            }
        }
        return -1;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < mapData.length && c >= 0 && c < mapData[0].length;
    }

    ////////////////////////////////////
    // 알고리즘 함수/메서드 부분 구현 끝
    ////////////////////////////////////

    ///////////////////////////////
    // 메인 프로그램 통신 메서드 정의
    ///////////////////////////////

    private static String init(String nickname) {
        try {
            System.out.println("[STATUS] Trying to connect to " + HOST + ":" + PORT + "...");
            socket = new Socket();
            socket.connect(new InetSocketAddress(HOST, PORT));
            System.out.println("[STATUS] Connected");
            String initCommand = "INIT " + nickname;

            return submit(initCommand);
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to connect. Please check if the main program is waiting for connection.");
            e.printStackTrace();
            return null;
        }
    }

    private static String submit(String stringToSend) {
        try {
            OutputStream os = socket.getOutputStream();
            String sendData = ARGS + stringToSend + " ";
            os.write(sendData.getBytes("UTF-8"));
            os.flush();

            return receive();
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to send data. Please check if connection to the main program is valid.");
            e.printStackTrace();
        }
        return null;
    }

    private static String receive() {
        try {
            InputStream is = socket.getInputStream();
            byte[] bytes = new byte[4096];
            int length = is.read(bytes);
            if (length <= 0) {
                System.out.println("[STATUS] No receive data from the main program.");
                close();
                return null;
            }

            String gameData = new String(bytes, 0, length, "UTF-8");
            if (gameData.length() > 0 && gameData.charAt(0) >= '1' && gameData.charAt(0) <= '9') {
                return gameData;
            }

            System.out.println("[STATUS] No receive data from the main program.");
            close();
            return null;
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to receive data. Please check if connection to the main program is valid.");
            e.printStackTrace();
        }
        return null;
    }

    private static void close() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                System.out.println("[STATUS] Connection closed");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Network connection has been corrupted.");
            e.printStackTrace();
        }
    }

    ///////////////////////////////
    // 입력 데이터 파싱
    ///////////////////////////////

    private static void parseData(String gameData) {
        String[] gameDataRows = gameData.split("\n");
        int rowIndex = 0;

        String[] header = gameDataRows[rowIndex].split(" ");
        int mapHeight = header.length >= 1 ? Integer.parseInt(header[0]) : 0;
        int mapWidth = header.length >= 2 ? Integer.parseInt(header[1]) : 0;
        int numOfAllies = header.length >= 3 ? Integer.parseInt(header[2]) : 0;
        int numOfEnemies = header.length >= 4 ? Integer.parseInt(header[3]) : 0;
        int numOfCodes = header.length >= 5 ? Integer.parseInt(header[4]) : 0;
        rowIndex++;

        mapData = new String[mapHeight][mapWidth];
        for (int i = 0; i < mapHeight; i++) {
            String[] col = gameDataRows[rowIndex + i].split(" ");
            for (int j = 0; j < col.length; j++) {
                mapData[i][j] = col[j];
            }
        }
        rowIndex += mapHeight;

        myAllies.clear();
        for (int i = rowIndex; i < rowIndex + numOfAllies; i++) {
            String[] ally = gameDataRows[i].split(" ");
            String allyName = ally.length >= 1 ? ally[0] : "-";
            String[] allyData = new String[ally.length - 1];
            System.arraycopy(ally, 1, allyData, 0, ally.length - 1);
            myAllies.put(allyName, allyData);
        }
        rowIndex += numOfAllies;

        enemies.clear();
        for (int i = rowIndex; i < rowIndex + numOfEnemies; i++) {
            String[] enemy = gameDataRows[i].split(" ");
            String enemyName = enemy.length >= 1 ? enemy[0] : "-";
            String[] enemyData = new String[enemy.length - 1];
            System.arraycopy(enemy, 1, enemyData, 0, enemy.length - 1);
            enemies.put(enemyName, enemyData);
        }
        rowIndex += numOfEnemies;

        codes = new String[numOfCodes];
        for (int i = 0; i < numOfCodes; i++) {
            codes[i] = gameDataRows[rowIndex + i];
        }
    }

    private static void printData(String gameData) {
        System.out.printf("\n----------입력 데이터----------\n%s\n----------------------------\n", gameData);

        System.out.printf("\n[맵 정보] (%d x %d)\n", mapData.length, mapData[0].length);
        for (String[] row : mapData) {
            for (String cell : row) {
                System.out.printf("%s ", cell);
            }
            System.out.println();
        }

        System.out.printf("\n[아군 정보] (아군 수: %d)\n", myAllies.size());
        for (String key : myAllies.keySet()) {
            String[] value = myAllies.get(key);
            if (key.equals("M")) {
                System.out.printf("M (내 탱크) - 체력: %s, 방향: %s, 보유한 일반 포탄: %s, 보유한 메가 포탄: %s\n",
                        value[0], value[1], value[2], value[3]);
            } else if (key.equals("H")) {
                System.out.printf("H (아군 포탑) - 체력: %s\n", value[0]);
            } else {
                System.out.printf("%s (아군 탱크) - 체력: %s\n", key, value[0]);
            }
        }

        System.out.printf("\n[적군 정보] (적군 수: %d)\n", enemies.size());
        for (String key : enemies.keySet()) {
            String[] value = enemies.get(key);
            if (key.equals("X")) {
                System.out.printf("X (적군 포탑) - 체력: %s\n", value[0]);
            } else {
                System.out.printf("%s (적군 탱크) - 체력: %s\n", key, value[0]);
            }
        }

        System.out.printf("\n[암호문 정보] (암호문 수: %d)\n", codes.length);
        for (String code : codes) {
            System.out.println(code);
        }
    }
}
