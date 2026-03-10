import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] people = new int[n];

        int[][] graph = new int[n][n];

        boolean[] visited = new boolean[n];

        for(int i = 0 ; i < n ; i++){
            people[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < Integer.parseInt(st.nextToken()) ; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }




    }

}
