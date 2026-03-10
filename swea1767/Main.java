import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] arr;

    // 코어 좌표 저장 (가장자리 제외한 코어만 저장)
    static int[][] core;
    static int cnt;      // 저장된 코어 개수

    static int maxCore;  // 연결된 코어 수 최대
    static int minLine;  // 그때의 전선 길이 최소

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n][n];

            // 최대 코어 수는 n*n 이하지만 넉넉하게
            core = new int[n * n][2];
            cnt = 0;

            StringTokenizer st;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] == 1) {
                        // 가장자리 코어는 이미 연결된 것으로 처리(전선 필요 없음)
                        if (i == 0 || j == 0 || i == n - 1 || j == n - 1) continue;
                        core[cnt][0] = i;
                        core[cnt][1] = j;
                        cnt++;
                    }
                }
            }

            maxCore = 0;
            minLine = Integer.MAX_VALUE;

            dfs(0, arr, 0, 0);

            sb.append("#").append(t).append(" ").append(minLine).append("\n");
        }

        System.out.print(sb);
    }

    // idx번째 코어를 처리, connected=연결된 코어 수, lineSum=전선 길이 합
    static void dfs(int idx, int[][] map, int connected, int lineSum) {
        // 가지치기: 남은 코어를 다 연결해도 maxCore 못 넘으면 중단
        if (connected + (cnt - idx) < maxCore) return;

        if (idx == cnt) {
            if (connected > maxCore) {
                maxCore = connected;
                minLine = lineSum;
            } else if (connected == maxCore) {
                if (lineSum < minLine) minLine = lineSum;
            }
            return;
        }

        int x = core[idx][0];
        int y = core[idx][1];

        boolean didConnect = false;

        // 4방향 시도
        for (int dir = 0; dir < 4; dir++) {
            int len = canConnect(map, x, y, dir);
            if (len == -1) continue; // 이 방향은 불가능

            didConnect = true;
            setWire(map, x, y, dir, 2);                 // 전선 깔기
            dfs(idx + 1, map, connected + 1, lineSum + len);
            setWire(map, x, y, dir, 0);                 // 전선 제거(백트래킹)
        }

        // 이 코어를 연결하지 않는 경우도 탐색(중요)
        dfs(idx + 1, map, connected, lineSum);
    }

    // dir 방향으로 끝까지 갈 수 있으면 필요한 길이 반환, 막히면 -1
    static int canConnect(int[][] map, int x, int y, int dir) {
        int nx = x;
        int ny = y;
        int len = 0;

        while (true) {
            nx += dx[dir];
            ny += dy[dir];

            // 가장자리 밖으로 나가면 성공 (len이 전선 길이)
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) return len;

            // 코어(1) 또는 이미 깔린 전선(2)이 있으면 실패
            if (map[nx][ny] != 0) return -1;

            len++;
        }
    }

    // dir 방향으로 전선을 val(2:설치, 0:제거)로 세팅
    static void setWire(int[][] map, int x, int y, int dir, int val) {
        int nx = x;
        int ny = y;

        while (true) {
            nx += dx[dir];
            ny += dy[dir];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) return;
            map[nx][ny] = val;
        }
    }
}
