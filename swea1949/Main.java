import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    static int n;
    static int k;
    static int result;
    static int[][] arr;
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        for(int t = 0 ; t < tc ; t++){
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            arr = new int[n][n];

            int top = 0;
            int top_cnt = 0;
            int[][] top_loc = new int[n*n][2];

            for(int i = 0 ; i < n ; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < n ; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());

                    if (arr[i][j] > top){
                        top_cnt = 1;
                        top_loc[0][0] = i;
                        top_loc[0][1] = j;
                        top = arr[i][j];
                    } else if (arr[i][j] == top) {
                        top_loc[top_cnt][0] = i;
                        top_loc[top_cnt][1] = j;
                        top_cnt++;
                    }
                }
            }

            result = 0;

            for(int i = 0 ; i < top_cnt ; i++){
                visited = new boolean[n][n];
                visited[top_loc[i][0]][top_loc[i][1]] = true;
                dfs(top_loc[i][0],top_loc[i][1], top, 1, false);
            }

            sb.append("#").append(t+1).append(" ").append(result).append("\n");

        }
        System.out.println(sb);
    }

    // val 현재 위치 값
    static void dfs(int x, int y, int val, int cnt, boolean con){

        boolean fin = true;

        for(int i = 0 ; i < 4 ; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < n && ny >= 0 && ny < n){
                if(con == false){
                    if(arr[nx][ny] < val && !visited[nx][ny]){
                        fin = false;
                        visited[nx][ny] = true;
                        dfs(nx, ny, arr[nx][ny], cnt+1, false);
                        visited[nx][ny] = false;
                    }
                    else{
                        for(int t = 1 ; t < k + 1 ; t++){
                            if(arr[nx][ny] - t < val && !visited[nx][ny]){
                                fin = false;
                                visited[nx][ny] = true;
                                dfs(nx, ny, arr[nx][ny] - t, cnt+1, true);
                                visited[nx][ny] = false;
                                break;
                            }
                        }
                    }

                }
                else {
                    if(arr[nx][ny] < val && !visited[nx][ny]){
                        fin = false;
                        visited[nx][ny] = true;
                        dfs(nx, ny, arr[nx][ny], cnt+1, true);
                        visited[nx][ny] = false;
                    }
                }

            }


        }

        if(fin){
            result = Math.max(result, cnt);
        }

    }
}
