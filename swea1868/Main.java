import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    static int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
    static int[] dy = {0, 0, 1, -1, 1, -1, -1, 1};

    static int n;
    static char[][] map;
    static int[][] visited;

    static Deque<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int tc = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < tc ; t++){
            n = Integer.parseInt(br.readLine());

            map = new char[n][n];
            visited = new int[n][n];
            q = new ArrayDeque<>();
            int cnt = 0;

            for(int i = 0 ; i < n ; i++){
                String s = br.readLine();
                for(int j = 0 ; j < n ; j++){
                    map[i][j] = s.charAt(j);
                }
            }

            // 0 먼저 처리
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    if(map[i][j] == '.'&& visited[i][j] == 0 && check(i, j)){
                        q.add(new int[]{i, j});
                        cnt++;
                        visited[i][j] = cnt;
                        bfs(cnt);
                    }
                }
            }

            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    if(map[i][j] == '.' && visited[i][j] == 0){
                       cnt++;
                    }
                }
            }

            sb.append("#").append(t+1).append(" ").append(cnt).append("\n");

        }

        System.out.println(sb);

    }

    static void bfs(int num){

        while (!q.isEmpty()){
            int[] cur = q.poll();

            for(int i = 0 ; i < 8 ; i++){
                int nr = cur[0] + dx[i];
                int nc = cur[1] + dy[i];

                if(nr >= 0 && nr < n && nc >= 0 && nc < n && visited[nr][nc] == 0){
                    visited[nr][nc] = num;
                    if(check(nr, nc)){
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }
    }

    static boolean check(int r, int c){
        for(int i = 0 ; i < 8 ; i++){
            int nr = r + dx[i];
            int nc = c + dy[i];

            if(nr >= 0 && nr < n && nc >= 0 && nc < n){
                if(map[nr][nc] == '*'){
                    return false;
                }
            }
        }

        return true;

    }

}
