import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[][] arr = new int[r][c];

        for(int i = 0 ; i < r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < c ; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }



    }
}
