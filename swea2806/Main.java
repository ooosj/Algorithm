import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            n = Integer.parseInt(br.readLine());
            result = 0;

            // blocked[r][c] == 0 이면 안전, >0 이면 공격받는 중
            int[][] blocked = new int[n][n];

            // 0행에 퀸을 놓는 모든 시작점 시도
            for (int c = 0; c < n; c++) {
                apply(blocked, 0, c, +1);   // 퀸 (0,c) 배치
                dfs(1, blocked);            // 다음 행(1)부터 진행
                apply(blocked, 0, c, -1);   // 되돌리기
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }

        System.out.print(sb);
    }

    // r행에 퀸을 하나 놓는 DFS (행 단위로 1개씩)
    static void dfs(int r, int[][] blocked) {
        if (r == n) {
            result++;
            return;
        }

        for (int c = 0; c < n; c++) {
            if (blocked[r][c] != 0) continue; // 공격받으면 못 둠

            apply(blocked, r, c, +1);
            dfs(r + 1, blocked);
            apply(blocked, r, c, -1); // 복구 (겹침도 안전)
        }
    }

    // (r,c)에 퀸을 두었을 때 공격 칸을 delta만큼 반영 (+1 배치, -1 제거)
    static void apply(int[][] blocked, int r, int c, int delta) {
        // 같은 열
        for (int i = 0; i < n; i++) blocked[i][c] += delta;

        // 같은 행
        for (int j = 0; j < n; j++) blocked[r][j] += delta;

        // 대각선 4방향
        for (int k = 1; k < n; k++) {
            int nr, nc;

            nr = r + k; nc = c + k;
            if (nr < n && nc < n) blocked[nr][nc] += delta;

            nr = r - k; nc = c - k;
            if (nr >= 0 && nc >= 0) blocked[nr][nc] += delta;

            nr = r - k; nc = c + k;
            if (nr >= 0 && nc < n) blocked[nr][nc] += delta;

            nr = r + k; nc = c - k;
            if (nr < n && nc >= 0) blocked[nr][nc] += delta;
        }

        // (r,c) 자기 자신은 위에서 행/열에 의해 delta가 2번 더해졌고,
        // 대각선은 k=0을 안 넣었으니 추가 없음.
        // 우리는 "퀸이 놓인 칸도 blocked>0" 이어도 상관없지만,
        // 보기 깔끔하게 자기 칸은 항상 0으로 유지하고 싶다면 아래를 사용:
        blocked[r][c] -= 2 * delta; // 행+열로 더해진 것만 취소해서 자기칸을 0 유지
    }
}
