import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int tc = 0 ; tc < 10 ; tc++){
            int t =  Integer.parseInt(br.readLine());

            sb.append("#").append(t);

            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[8];

            int min = Integer.MAX_VALUE;

            for(int i = 0; i < 8 ; i++){
                arr[i] = Integer.parseInt(st.nextToken());
                min = Math.min(min, arr[i]);
            }

            int time = min % 15 == 0 ? min / 15 - 1 : min / 15;

            for(int i = 0 ; i < 8 ; i++){
                arr[i] -= 15 * time;
            }

            int cnt = 0;
            boolean bool = true;

            while(bool){
                for(int i = 1 ; i < 6 ; i++){
                    if (cnt == 8){
                        cnt = 0;
                    }
                    arr[cnt++] -= i;
                    if (arr[cnt-1] <= 0){
                        arr[cnt-1] = 0;
                        bool = false;
                        break;
                    }
                }
            }

            for(int i = 0; i < 8 ; i++){
                if(cnt == 8){
                    cnt -= 8;
                }
                sb.append(" ").append(arr[cnt++]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
