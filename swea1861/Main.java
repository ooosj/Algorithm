import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int result;
    static int max;
    static int n;
    static int num;

    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};


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
            max = 0;
            result = 0 ;
            num = 0;

            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    dfs(i,j,1,arr[i][j]);
                }
            }

            sb.append("#").append(t+1).append(" ").append(result).append(" ").append(num).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int r, int c, int cnt, int start){
        for(int i = 0 ; i < 4 ; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr >= 0 && nr < n && nc >= 0 && nc < n){
                if(arr[nr][nc] == arr[r][c] + 1){
                    dfs(nr,nc,cnt+1, start);
                }
            }
        }

        if(cnt > max){
            result = start;
            num = cnt;
            max = cnt;
        }
        else if (cnt == max && start < result) {
            result = start;
        }
    }

}
