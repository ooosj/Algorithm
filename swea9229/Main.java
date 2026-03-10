import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < tc ; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];

            st = new StringTokenizer(br.readLine());

            for(int i = 0 ; i < n ; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            int result = -1;

            for(int i = n-1 ; i > 0 ; i--){
                for(int j = 0 ; j < i ; j++){
                    if(arr[i] + arr[j] <= m){
                        result = Math.max(result, arr[i] + arr[j]);
                    }
                }
            }

            sb.append("#").append(t+1).append(" ").append(result).append("\n");

        }

        System.out.println(sb.toString());
    }
}
