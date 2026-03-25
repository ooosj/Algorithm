import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] cost = new int[n][3];
        int[][] dp = new int[n][3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken()); // R
            cost[i][1] = Integer.parseInt(st.nextToken()); // G
            cost[i][2] = Integer.parseInt(st.nextToken()); // B
        }

        // 첫 번째 집은 초기값 그대로 설정
        dp[0][0] = cost[0][0];
        dp[0][1] = cost[0][1];
        dp[0][2] = cost[0][2];

        // 두 번째 집부터 점화식 적용
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
        }

        // 마지막 집의 세 가지 경우 중 최솟값 출력
        System.out.println(Math.min(Math.min(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]));
    }
}