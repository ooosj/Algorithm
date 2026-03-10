import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {0, 0, -1};
    static int[] dy = {-1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int[][] arr = new int[100][100];
        int endx = 0;
        int endy = 0;

        for(int tc = 0 ; tc < 10 ; tc++){
            br.readLine();

            for(int i = 0 ; i < 100 ; i++){
                st = new StringTokenizer(br.readLine());

                for(int j = 0 ; j < 100; j++){
                    int s = Integer.parseInt(st.nextToken());
                    arr[i][j] = s;
                    if (s == 2){
                        endx = i;
                        endy = j;
                    }
                }
            }

            boolean flag = true;

            while(flag){
                for(int i = 0 ; i < 3 ; i++){
                    int nx = endx + dx[i];
                    int ny = endy+ dy[i];

                    if(nx>=0 && nx < 100 && ny>=0 && ny < 100 && arr[nx][ny] == 1){

                        arr[endx][endy] = 0;

                        endx = nx;
                        endy = ny;

                        if(nx == 0) {
                            flag = false;
                            break;
                        }

                        break;
                    }
                }
            }

            sb.append("#").append(tc+1).append(" ").append(endy).append("\n");

        }
        System.out.println(sb.toString());
    }
}
