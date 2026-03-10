import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    static int[][] map;
    static int n;
    static int max;
    static Deque<int[]> q;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < tc ; t++){
            n = Integer.parseInt(br.readLine());

            q = new ArrayDeque<>();

            map = new int[n][n];

            max = 0;

            for(int i = 0 ; i < n ; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < n ; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, map[i][j]);
                }
            }

            int day = 0;
            int result = 0;

            while(true){

                if(day > max){
                    break;
                }

                int cnt = 0;

                visited = new boolean[n][n];

                for(int i = 0 ; i < n ; i++){
                    for(int j = 0 ; j < n; j++){
                        if(map[i][j] == day){
                            map[i][j] = 0;
                        }
                    }
                }

                for(int i = 0 ; i < n ; i++){
                    for(int j = 0 ; j < n; j++){
                        if(!visited[i][j] && map[i][j] != 0){
                            visited[i][j] = true;
                            q.add(new int[]{i, j});
                            bfs();
                            cnt++;
                        }
                    }
                }

                result = Math.max(result, cnt);

                day++;

            }

            sb.append("#").append(t+1).append(" ").append(result).append("\n");

        }
        System.out.println(sb);
    }

    static void bfs(){
        int[] cur;

        while(!q.isEmpty()){

            cur = q.poll();

            int x = cur[0];
            int y = cur[1];

            for(int i = 0 ; i < 4 ; i++ ){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx >= 0 && nx < n && ny >=0 && ny < n && map[nx][ny] != 0 && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.add(new int[]{nx,ny});
                }
            }

        }
    }

}
