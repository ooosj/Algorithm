import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < tc; t++){
            sb.append("#").append(t+1).append(" ");

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] arr = new int[n][n];

            int result = Integer.MIN_VALUE;

            for(int i = 0 ; i < n ; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < n ; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0 ; i < n-m+1 ; i++){
                for(int j = 0 ; j < n-m+1 ; j++){
                    int temp = 0;
                    for(int k = 0 ; k < m ; k++){
                       for(int l = 0; l < m ; l++){
                           temp += arr[i+k][j+l];
                       }
                    }
                    result = Math.max(result, temp);
                }
            }

            sb.append(result).append("\n");

        }

        System.out.println(sb.toString());
    }
}
