import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];
        int[] dp = new int[n+1];

        st = new StringTokenizer(br.readLine());

        for(int i = 1 ; i < n+1 ; i++){
            arr[i] = Integer.parseInt(st.nextToken());

            dp[i] = dp[i-1] + arr[i];
        }

        for(int i = 0 ; i < m ; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sb.append(dp[e] - dp[s-1]).append("\n");
        }

        System.out.println(sb);

    }
}
