import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < tc ; t++){
            String s = new String(br.readLine());

            int cnt = 0;

            char n = '0';

            for(int i = 0 ; i < s.length() ; i++){
                char c = s.charAt(i);
                if(c != n){
                    cnt++;

                    if (n == '0'){
                        n = '1';
                    }else {
                        n = '0';
                    }
                }
            }

            System.out.println("#"+(t+1)+" "+cnt);

        }

    }
}
