import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int n;
    static int result;
    static boolean[] visited;

    static int startR;
    static int startC;
    static int endR;
    static int endC;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < tc ; t++){

            n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());

            arr = new int[n][2];
            visited = new boolean[n];
            result = Integer.MAX_VALUE;

            startR = Integer.parseInt(st.nextToken());
            startC = Integer.parseInt(st.nextToken());
            endR = Integer.parseInt(st.nextToken());
            endC = Integer.parseInt(st.nextToken());


            for(int i = 0 ; i < n ; i++){
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }

            dfs(0,0, startR, startC);

            sb.append("#").append(t+1).append(" ").append(result).append("\n");

        }
        System.out.println(sb);
    }

    static void dfs(int depth, int temp, int curR, int curC){
        if(temp >= result){
            return;
        }

        if(depth == n){
            temp += Math.abs(endR - curR) + Math.abs(endC - curC);
            result = Math.min(result, temp);
            return;
        }

        for(int i = 0 ; i < n ; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(depth+1, temp + Math.abs(curR - arr[i][0]) + Math.abs(curC - arr[i][1]) ,arr[i][0], arr[i][1]);
                visited[i] = false;
            }
        }


    }

}
