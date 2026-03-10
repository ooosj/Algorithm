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

        for(int t = 0 ; t < tc; t++){

            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n];

            st = new StringTokenizer(br.readLine());

            int top = 0;

            for(int i = 0 ; i < n ; i++){
                int a = Integer.parseInt(st.nextToken());
                arr[i] = a;
                top = Math.max(top, a);
            }

            int even = 0;
            int odd = 0;

            for(int i = 0 ; i < n ; i++){
                int k = top - arr[i];

                even += k / 2;
                odd += k % 2;
            }

            while (true){
                if(even - odd > 1 ){
                    even -= 1;
                    odd += 2;
                }
                else {
                    break;
                }
            }

            int result = 0;

            if(odd > even){
                result = (odd-1) * 2 + 1;
            }
            else{
                result = even * 2;
            }

            sb.append("#").append(t+1).append(" ").append(result).append("\n");

        }

        System.out.println(sb);

    }
}