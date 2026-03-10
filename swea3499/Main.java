import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for (int t = 0 ; t < tc ; t++){
            sb.append("#").append(t);

            int n = Integer.parseInt(br.readLine());

            String str = br.readLine();

            String[] strs = str.split(" ");

            for(int i = 0 ; i < n ; i++){
                sb.append(" ").append(strs[i]);
                sb.append(" ").append(strs[i+(n/2)]);
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());

    }
}
