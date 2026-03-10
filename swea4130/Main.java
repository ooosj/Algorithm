import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static  int[][] arr;
    static int[] top;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        br.readLine();

        for(int tc = 0 ; tc < 10 ; tc ++){
            arr = new int[4][8];
            top = new int[4];

            int k = Integer.parseInt(br.readLine());

            for(int i = 0 ; i < 4 ; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < 8 ; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0 ; i < k ; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());

                int spin_num = Integer.parseInt(st.nextToken()) - 1;
                int spin = Integer.parseInt(st.nextToken());

                if(spin_num == 0){
                    if(arr[spin_num][(top[spin_num] + 2) % 8] != arr[spin_num+1][(top[spin_num+1] + 6) % 8]){
                        right(spin_num + 1, -spin );
                    }

                    move(spin_num, spin);

                }
                else if (spin_num == 3) {
                    if(arr[spin_num][(top[spin_num] + 6) % 8] != arr[spin_num-1][(top[spin_num-1] + 2) % 8]){
                        left(spin_num-1, -spin);
                    }

                    move(spin_num, spin);

                }
                else{
                    if(arr[spin_num][(top[spin_num] + 2) % 8] != arr[spin_num+1][(top[spin_num+1] + 6) % 8]){
                        right(spin_num+1, -spin);
                    }
                    if(arr[spin_num][(top[spin_num] + 6) % 8] != arr[spin_num-1][(top[spin_num-1] + 2) % 8]){
                        left(spin_num-1, -spin);
                    }

                    move(spin_num, spin);

                }
            }

            int result = 0;

            for(int i = 0 ; i < 4 ; i++){
                result += arr[i][top[i]] * (i + 1);
            }

            sb.append("#").append(tc+1).append(" ").append(result).append("\n");

        }

        System.out.println(sb.toString());

    }

    static void move(int spin_num,int spin){
        top[spin_num] = (8 + (top[spin_num] - spin)) % 8;
    }

    static void left(int spin_num, int spin){
        if (spin_num == -1){
            return;
        }

        if(spin_num > 0 && arr[spin_num][(top[spin_num] + 6) % 8] != arr[spin_num-1][(top[spin_num - 1] + 2) % 8]){
            left(spin_num-1, -spin);
        }
        move(spin_num, spin);

    }

    static void right(int spin_num, int spin){
        if (spin_num == 4){
            return;
        }

        if(spin_num < 3 && arr[spin_num][(top[spin_num] + 2) % 8] != arr[spin_num+1][(top[spin_num+1] + 6) % 8]){
            right(spin_num + 1, -spin);
        }
        move(spin_num, spin);

    }

}
