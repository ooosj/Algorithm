import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] monster;
    static int[][] home;
    static boolean[][] visited;
    static int m;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < tc ; t++){
            int n = Integer.parseInt(br.readLine());

            monster = new int[6][2];
            home = new int[6][2];
            visited = new boolean[6][2];    // 0은 몬스터 1은 집

            m = 0;

            for(int i = 0 ; i < n ; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < n ; j++){
                    int a = Integer.parseInt(st.nextToken());
                    if(a > 0){
                        monster[a][0] = i;
                        monster[a][1] = j;
                        m++;
                    } else if (a < 0) {
                        home[Math.abs(a)][0] = i;
                        home[Math.abs(a)][1] = j;
                    }
                }
            }

            result = Integer.MAX_VALUE;

            dfs(0,0,0,0);

            System.out.println(result);

        }
    }

    static void dfs(int r, int c, int cnt, int temp){
        if(cnt == m * 2){
            result = Math.min(temp, result);
            return;
        }

        if(temp > result){
            return;
        }

        for(int i = 1 ; i < m + 1 ; i++){
            if(visited[i][1]){
                continue;
            }
            else if(visited[i][0]){  // 몬스터를 잡은 경우
                visited[i][1] = true;
                dfs(home[i][0], home[i][1], cnt+1, temp + Math.abs(home[i][0] - r) + Math.abs(home[i][1] - c));
                visited[i][1] = false;
            }
            else{
                visited[i][0] = true;
                dfs(monster[i][0], monster[i][1], cnt +1, temp + Math.abs(monster[i][0] - r) + Math.abs(monster[i][1] - c));
                visited[i][0] = false;
            }
        }
    }
}
