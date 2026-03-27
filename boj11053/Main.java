import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        int[] dp = new int[n];

        for(int i = 0 ; i < n ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i < n ; i++){

            dp[i] = 1;

            for(int j = 0 ; j < i ; j++){
                if(dp[i] < dp[j] + 1 && arr[i] > arr[j]){
                    dp[i] = dp[j] + 1;
                }
            }
        }

        int result = 1;

        for(int i = 0 ; i < n ; i++){
            result = Math.max(result, dp[i]);
        }

        System.out.println(result);

    }
}
