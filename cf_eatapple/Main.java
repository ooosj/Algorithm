import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for(int t = 0 ; t < tc ; t++){
            int n = Integer.parseInt(br.readLine());

            int[][] map = new int[n][n];
            int[][] apple = new int[10][2];
            int cnt = 0;
            int dir = 2; // 상 0 우 1 하 2 좌 3
            int result = 1;

            for(int i = 0 ; i < n ; i++){
                String st = br.readLine();
                for(int j = 0 ; j < n ; j++){
                    int a = st.charAt(j) - '0';
                    map[i][j] = a;
                    
                    if(a != 0){
                        apple[a][0] = i;
                        apple[a][1] = j;
                        cnt++;
                    }
                }
            }
            
            int cur_r = apple[1][0];
            int cur_c = apple[1][1];

            for(int i = 2 ; i < cnt+1 ; i++){
                int nr = apple[i][0];
                int nc = apple[i][1];

                if(dir == 0){
                    if(nr < cur_r && nc > cur_c){   // 1구역
                        result += 1;
                        dir = dir+1 % 4;
                    } 
                    else if (nr > cur_r && nc > cur_c) {    // 2구역
                        result += 2;
                        dir = dir+2 % 4;
                    }
                    else if (nr != 0 && nc < cur_c) {    // 3구역
                        result += 3;
                        dir = dir+3 % 4;
                    }
                    else {    // 4구역
                        result += 4;
                    }
                }
                else if (dir == 1) {
                    if(nr > cur_r && nc > cur_c){   // 1구역
                        result += 1;
                        dir = (dir+1) % 4;
                    }
                    else if (nr > cur_r && nc < cur_c) {    // 2구역
                        result += 2;
                        dir = (dir+2) % 4;
                    }
                    else if (nc != n-1 && nr < cur_r) {    // 3구역
                        result += 3;
                        dir = (dir+3) % 4;
                    }
                    else {    // 4구역
                        result += 4;
                    }
                }
                else if (dir == 2) {
                    if(nr > cur_r && nc < cur_c){   // 1구역
                        result += 1;
                        dir = (dir+1) % 4;
                    }
                    else if (nr < cur_r && nc < cur_c) {    // 2구역
                        result += 2;
                        dir = (dir+2) % 4;
                    }
                    else if (nr != n-1 && nc > cur_c) {    // 3구역
                        result += 3;
                        dir = (dir+3)% 4;
                    }
                    else {    // 4구역
                        result += 4;
                    }
                }
                else if (dir == 3) {
                    if(nr < cur_r && nc < cur_c){   // 1구역
                        result += 1;
                        dir = (dir+1) % 4;
                    }
                    else if (nr < cur_r && nc > cur_c) {    // 2구역
                        result += 2;
                        dir = (dir+2) % 4;
                    }
                    else if (nc != 0 && nr > cur_r) {    // 3구역
                        result += 3;
                        dir = (dir+3) % 4;
                    }
                    else {    // 4구역
                        result += 4;
                    }
                }

                cur_r = nr;
                cur_c = nc;

            }
            sb.append(result).append("\n");
            
        }
        System.out.println(sb);
    }
}
