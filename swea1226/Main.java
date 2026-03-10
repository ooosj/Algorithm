import javax.swing.text.html.parser.Parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for(int tc = 0 ; tc < 10; tc++){

            sb.append("#").append(Integer.parseInt(br.readLine())).append(" ");

            int[][] arr = new int[16][16];
            boolean[][] visited = new boolean[16][16];

            Deque<int[]> q = new ArrayDeque<>();

            int startX = 1;
            int startY = 1;

            int result = 0;

            for(int i = 0 ; i < 16 ; i++){
                String line = br.readLine();
                for(int j = 0 ; j < 16 ; j++){
                    arr[i][j] = line.charAt(j) - '0';
                    if (arr[i][j] == 2){
                        startX = i;
                        startY = j;
                    }
                }
            }

            q.offer(new int[]{startX,startY});

            while(!q.isEmpty() && result == 0){

                int[] cur = q.poll();

                int x = cur[0];
                int y = cur[1];

                for(int i = 0 ; i < 4 ; i++){
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(nx >= 0 && ny >= 0 && nx <= 15 && ny <= 15 && !visited[nx][ny]){
                        visited[nx][ny] = true;
                        if(arr[nx][ny] == 0){
                            q.offer(new int[]{nx, ny});
                        } else if (arr[nx][ny] == 3) {
                            result = 1;
                        }
                    }
                }
            }

            sb.append(result).append("\n");

        }

        System.out.println(sb.toString());

    }
}
