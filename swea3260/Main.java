import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for (int t = 0 ; t < tc ; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            BigInteger a = new BigInteger(st.nextToken());
            BigInteger b = new BigInteger(st.nextToken());

            System.out.println("#"+(t+1)+" "+(a.add(b)));
        }
    }
}
