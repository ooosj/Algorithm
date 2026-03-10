import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] arr;
    static int[] comb;
    static int[] diff;
    static int result;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        for(int t = 0 ; t < tc ; t++){
            n = Integer.parseInt(br.readLine());

            arr = new int[n][n];

            for(int i = 0 ; i < n ; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < n ; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            comb = new int[n/2];
            diff = new int[n/2];
            result = Integer.MAX_VALUE;
            visited = new boolean[n];

            dfs(0,0);

            sb.append("#").append(t+1).append(" ").append(result).append("\n");

        }

        System.out.println(sb);
    }

    static void dfs(int start, int depth){
        if(depth == n/2){
            int c = 0;
            int d = 0;
            int cnt = 0;
            for(int i = 0 ; i < n ; i++){
                if(!visited[i]){
                    diff[cnt++] = i;
                }
            }

            for(int i = 0 ; i < n/2 ; i++){
                for(int j = i+1 ; j < n/2 ; j++){
                    c += arr[comb[i]][comb[j]] + arr[comb[j]][comb[i]];
                    d += arr[diff[i]][diff[j]] + arr[diff[j]][diff[i]];
                }
            }

            result = Math.min(result, Math.abs(c-d));

            return;

        }

        for(int i = start ; i < n ; i++){
            comb[depth] = i;
            visited[i] = true;
            dfs(i+1, depth+1);
            visited[i] = false;
        }
    }
}
