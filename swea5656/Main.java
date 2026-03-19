import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int n, w, h, result;
    // 사방 탐색용 델타 배열 (상, 하, 좌, 우)
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < tc ; t++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            int[][] map = new int[h][w];

            // 1. result 초기화 (최댓값으로)
            result = Integer.MAX_VALUE;

            for(int i = 0 ; i < h ; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < w ; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, map);

            sb.append("#").append(t+1).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int depth, int[][] map) {
        // 남은 벽돌 개수 세기
        int remain = brick_cnt(map);

        // 기저 조건: 남은 벽돌이 0개거나 구슬을 다 던졌을 때
        if (remain == 0 || depth == n) {
            result = Math.min(result, remain);
            return;
        }

        for (int c = 0; c < w; c++) {
            // 2. DFS 분기마다 원본 맵을 보호하기 위해 깊은 복사 진행
            int[][] nextMap = copyMap(map);

            // 해당 열(c)에서 맨 위에 있는 벽돌 찾기
            int r = -1;
            for (int i = 0; i < h; i++) {
                if (nextMap[i][c] != 0) {
                    r = i;
                    break;
                }
            }

            // 해당 열에 벽돌이 없으면 다음 열로 넘어감
            if (r == -1) continue;

            // 폭발 시작
            breakBricks(nextMap, r, c);

            // 3. 빈 공간 채우기 (중력 작용)
            applyGravity(nextMap);

            // 다음 구슬 던지러 가기
            dfs(depth + 1, nextMap);
        }
    }

    // 4. 벽돌 연쇄 폭발 로직 (BFS)
    static void breakBricks(int[][] map, int startR, int startC) {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{startR, startC, map[startR][startC]});
        map[startR][startC] = 0; // 큐에 넣고 즉시 0으로 만들어 중복 방지

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int range = cur[2];

            // range가 1이면 주변 폭발 없으므로 넘어감
            if (range == 1) continue;

            for (int d = 0; d < 4; d++) {
                // range-1 만큼 해당 방향으로 뻗어나감
                for (int k = 1; k < range; k++) {
                    int nr = r + dr[d] * k;
                    int nc = c + dc[d] * k;

                    // 맵 범위 안이고, 벽돌이 있다면
                    if (nr >= 0 && nr < h && nc >= 0 && nc < w && map[nr][nc] != 0) {
                        if (map[nr][nc] > 1) { // 2 이상이면 연쇄 폭발을 위해 큐에 삽입
                            q.add(new int[]{nr, nc, map[nr][nc]});
                        }
                        map[nr][nc] = 0; // 터졌으므로 0으로 변경
                    }
                }
            }
        }
    }

    // 3. 중력 작용 (밑바닥부터 채워넣기)
    static void applyGravity(int[][] map) {
        for (int c = 0; c < w; c++) {
            int emptyRow = h - 1; // 바닥부터 시작
            for (int r = h - 1; r >= 0; r--) {
                if (map[r][c] != 0) {
                    int temp = map[r][c];
                    map[r][c] = 0;
                    map[emptyRow][c] = temp;
                    emptyRow--; // 하나 채웠으니 빈 공간 인덱스를 한 칸 위로
                }
            }
        }
    }

    // 맵 복사 메서드
    static int[][] copyMap(int[][] map) {
        int[][] newMap = new int[h][w];
        for (int i = 0; i < h; i++) {
            newMap[i] = map[i].clone();
        }
        return newMap;
    }

    // 남은 벽돌 카운트
    static int brick_cnt(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] != 0) cnt++;
            }
        }
        return cnt;
    }
}