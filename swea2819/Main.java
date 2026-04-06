import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static char[][] map;
    static HashSet<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < tc ; t++){

            set = new HashSet<>();
            map = new char[4][4];

            for(int i = 0 ; i < 4 ; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < 4 ; j++){
                    map[i][j] = st.nextToken().charAt(0);
                }
            }

            for(int i = 0 ; i < 4 ; i++){
                for(int j = 0 ; j < 4 ; j++){
                    dfs(i,j,1, Character.toString(map[i][j]));
                }
            }

            sb.append("#").append(t+1).append(" ").append(set.size()).append("\n");

        }

        System.out.println(sb);

    }

    static void dfs(int r, int c, int depth, String s){

        if(depth == 7){
            set.add(s);
            return;
        }

        for(int i = 0 ; i < 4; i++){
            int nr = r + dx[i];
            int nc = c + dy[i];

            if(nr < 4 && nr >= 0 && nc < 4 && nc >=0){
                dfs(nr, nc, depth+1, s+map[nr][nc]);
            }

        }

    }

}
