import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    static int h;
    static int w;

    static int[][] map;
    static int[][] time;
    static boolean[][] visited;
    static Deque<int[]> q;
    static Deque<int[]> q_gas;

    static int zero;
    static int escape;
    static int visit_cnt;

    static int man_cnt;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        for (int t = 0 ; t < tc ; t++) {

            sb.append("#").append(t+1).append(" ");

            st = new StringTokenizer(br.readLine());

            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new int[h][w];
            time = new int[h][w];
            visited = new boolean[h][w];

            for(int i = 0 ; i < h ; i++) {
                for(int j = 0 ; j < w ; j++) {
                    time[i][j] = 9;
                }
            }

            zero = 0;
            visit_cnt = 0;
            int gas_cnt = 0;

            q = new ArrayDeque<>();
            q_gas = new ArrayDeque<>();

            for(int i = 0 ; i < h ; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < w ; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    if(map[i][j] == 3) {
                        visited[i][j] = true;
                        visit_cnt++;
                        zero++;
                        q.add(new int[] {i,j});
                    }
                    else if(map[i][j] == 2) {
                        time[i][j] = 1;
                        gas_cnt++;
                        zero++;
                        q_gas.add(new int[] {i,j});
                    }
                    else if (map[i][j] == 0) {
                        zero++;
                    }
                }
            }

            for(int i = 0 ; i < gas_cnt ; i++){
                int cnt = 1;
                bfs_gas(cnt);
            }

            man_cnt = 1;

            escape = Integer.MAX_VALUE;

            bfs(man_cnt);

            if(escape == 1) {
                sb.append(man_cnt).append("\n");
            }
            else if(escape == 0){
                sb.append("CANNOT ESCAPE").append("\n");
            }
            else if(escape == 2){
                sb.append("ZOMBIE").append("\n");
            }

            for(int i = 0 ; i < h ; i++) {
                for(int j = 0 ; j < w ; j++) {
                    System.out.print(time[i][j]);
                }
                System.out.println("\n");
            }

        }

        System.out.println(sb);
    }

    static void bfs_gas(int cnt) {
        while(!q_gas.isEmpty()) {
            int[] gas = q_gas.poll();
            int x = gas[0];
            int y = gas[1];

            for(int i = 0 ; i < 4 ; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx >= 0 && nx < h && ny >= 0 && ny < w && map[nx][ny] != 1) {
                    if(cnt < time[nx][ny]) {
                        time[nx][ny] = cnt;
                        q_gas.add(new int[] {nx,ny});
                        bfs_gas(cnt+1);
                    }
                }

            }
        }
    }

    static void bfs(int cnt) {
        while(!q.isEmpty()) {
            int[] man = q.poll();
            int x = man[0];
            int y = man[1];

            for(int i = 0 ; i < 4 ; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx >= 0 && nx < h && ny >= 0 && ny < w && map[nx][ny] != 1) {
                    if(cnt < time[nx][ny]) {
                        visited[nx][ny] = true;
                        visit_cnt++;
                        q.add(new int[] {nx,ny});
                        man_cnt++;
                        bfs(cnt+1);
                    }
                }
                else if(nx < 0 && nx >= h && ny < 0 && ny >= w) {
                    escape = 1;
                    break;
                }

            }

            if(escape == 1) {
                break;
            }
        }

        if(escape == 1) {
            return;
        }
        else if(visit_cnt == zero) {
            escape = 0;
        }
        else {
            escape = 2;
        }

    }

}
