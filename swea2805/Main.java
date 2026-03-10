import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for(int t = 0 ; t < tc ; t++){
            int n = Integer.parseInt(br.readLine());

            int[][] arr = new int[n][n];

            for(int i = 0 ; i < n ; i++){
                String st = br.readLine();
                for(int j = 0 ; j < n ; j++){
                    arr[i][j] = Integer.parseInt(String.valueOf(st.charAt(j)));
                }

            }

            int result = 0;

            for(int i = 0 ; i < n / 2 ; i++){
                for(int j = 0 ; j < 2 * i + 1; j++){
                    result += arr[i][n/2 - i + j];
                }
            }
            for(int i = 0 ; i < n ; i++){
                result += arr[n/2][i];
            }
            for(int i = n - 1 ; i > n / 2 ; i--){
                for(int j = 0 ; j < 2 * (n - 1 - i) + 1; j++){
                    result += arr[i][n/2 - (n - 1 - i)+ j];
                }
            }

            sb.append("#").append(t+1).append(" ").append(result).append("\n");

        }

        System.out.println(sb);

    }
}
