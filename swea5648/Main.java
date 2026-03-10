import java.io.*;
import java.util.*;

public class Main {


    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static final int SIZE = 4001;
    static final int OFF = 2000;

    static int[][] cntMap;
    static int[][] sumMap;
    static int[][] atomic;
    static int[] nextR, nextC;

    static int n, dead, result;
    static ArrayDeque<int[]> touched = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            n = Integer.parseInt(br.readLine());

            cntMap = new int[SIZE][SIZE];
            sumMap = new int[SIZE][SIZE];

            atomic = new int[n + 1][5];
            nextR = new int[n + 1];
            nextC = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());


                int r = (-y) * 2 + OFF;
                int c = (x) * 2 + OFF;

                atomic[i][0] = r;
                atomic[i][1] = c;
                atomic[i][2] = dir;
                atomic[i][3] = e;
                atomic[i][4] = 1; // alive
            }

            dead = 0;
            result = 0;

            // 충분한 턴 상한(대략 SIZE 정도면 안전)
            for (int time = 0; time <= SIZE && dead < n; time++) {

                // 1) 다음 위치 계산 + 도착 칸 집계
                for (int i = 1; i <= n; i++) {
                    if (atomic[i][4] == 0) continue;

                    int dir = atomic[i][2];
                    int nr = atomic[i][0] + dr[dir];
                    int nc = atomic[i][1] + dc[dir];

                    // 범위 밖으로 나가면 더 이상 충돌 불가 -> 제거
                    if (nr < 0 || nr >= SIZE || nc < 0 || nc >= SIZE) {
                        atomic[i][4] = 0;
                        dead++;
                        continue;
                    }

                    nextR[i] = nr;
                    nextC[i] = nc;

                    if (cntMap[nr][nc] == 0) touched.add(new int[]{nr, nc});
                    cntMap[nr][nc]++;
                    sumMap[nr][nc] += atomic[i][3];
                }

                // 2) 충돌 칸에 들어간 원자 제거, 아니면 위치 업데이트
                for (int i = 1; i <= n; i++) {
                    if (atomic[i][4] == 0) continue;

                    int nr = nextR[i];
                    int nc = nextC[i];

                    if (cntMap[nr][nc] >= 2) {
                        atomic[i][4] = 0;
                        dead++;
                    } else {
                        atomic[i][0] = nr;
                        atomic[i][1] = nc;
                    }
                }

                // 3) 충돌 에너지 더하고 맵 초기화
                while (!touched.isEmpty()) {
                    int[] p = touched.pollFirst();
                    int r = p[0], c = p[1];

                    if (cntMap[r][c] >= 2) result += sumMap[r][c];

                    cntMap[r][c] = 0;
                    sumMap[r][c] = 0;
                }
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }

        System.out.print(sb);
    }
}
