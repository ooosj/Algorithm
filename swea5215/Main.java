import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < tc ; t++){
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            int taste, kal;

            int[] dp = new int[L+1];

            for(int j = 0 ; j < n ; j++){
                st = new StringTokenizer(br.readLine());

                taste = Integer.parseInt(st.nextToken());
                kal = Integer.parseInt(st.nextToken());

                for(int i = L ; i > kal-1; i--){
                    dp[i] = Math.max(dp[i], dp[i-kal] + taste);
                }
            }

            sb.append("#").append(t+1).append(" ").append(dp[L]).append("\n");

        }
        System.out.println(sb);
    }
}
