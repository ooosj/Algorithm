import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};

    static int n;
    static int l;
    static int r;
    static int cnt;
    static int[] sum;
    static int[] count;
    static int[][] map;
    static int[][] open;
    static Deque<int[]> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < n ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }



        int result = 0;

        while(true){
            cnt = 1;
            open = new int[n][n];
            sum = new int[n*n + 1];
            count = new int[n*n + 1];

            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    if(open[i][j] == 0){
                        q.add(new int[]{i,j});
                        open[i][j] = cnt;
                        bfs();
                        cnt++;

//                        System.out.println(i+" " + j + " " + cnt +" "+ count[cnt]);
                    }
                }
            }

//            System.out.println(cnt);

            if(cnt == 1){
                break;
            }

            for(int i = 1 ; i < cnt + 1 ; i++){
                for(int x = 0 ; x < n ; x++){
                    for(int y = 0 ; y < n ; y++){
                        if(open[x][y] == i ){
                            map[x][y] = sum[i] / count[i];
                        }
                    }
                }
            }

//            System.out.println(map[0][0]);

            result++;

        }

        System.out.println(result);

    }

    static void bfs(){
        int[] arr = new int[0];
        while(!q.isEmpty()){
            arr = q.poll();
            count[cnt]++;
            sum[cnt] += map[arr[0]][arr[1]];

            for(int i = 0 ; i < 4 ; i++){
                int nx = arr[0] + dx[i];
                int ny = arr[1] + dy[i];

                if(nx >= 0 && nx < n && ny >= 0 && ny < n && open[nx][ny] == 0 ){
                    if( Math.abs(map[arr[0]][arr[1]] - map[nx][ny]) >= l && Math.abs(map[arr[0]][arr[1]] - map[nx][ny]) <= r){
                        q.add(new int[]{nx,ny});
                        open[nx][ny] = cnt;
                    }
                }
            }

        }

        if(count[cnt] == 1){
//            System.out.println("aaaa");
            count[cnt] = 0;
            sum[cnt] = 0;
            cnt--;
            open[arr[0]][arr[1]] = 0;
        }

    }

}
