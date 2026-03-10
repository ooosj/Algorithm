import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < tc ; t++){

            sb.append("#").append(t+1).append(" ");

            StringTokenizer st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            char[][] arr = new char[h][w];

            int[] tank = new int[2];

            for(int i = 0 ; i < h ; i++){
                String s = br.readLine();
                for(int j = 0 ; j < w ; j++){
                    arr[i][j] = s.charAt(j);
                    if(arr[i][j] == '<' || arr[i][j] == 'v' || arr[i][j] == '>' || arr[i][j] == '^'){
                        tank[0] = i;
                        tank[1] = j;
                    }
                }
            }

            int n = Integer.parseInt(br.readLine());

            String s = br.readLine();

            for(int i = 0 ; i < n ; i++){

                char input = s.charAt(i);

                if(input == 'U'){
                    arr[tank[0]][tank[1]] = '^';
                    if(tank[0] > 0 && arr[tank[0]-1][tank[1]] == '.'){
                        arr[tank[0]][tank[1]] = '.';
                        tank[0] -= 1;
                        arr[tank[0]][tank[1]] = '^';
                    }

                }
                else if (input == 'D') {
                    arr[tank[0]][tank[1]] = 'v';
                    if(tank[0] < h-1 && arr[tank[0]+1][tank[1]] == '.'){
                        arr[tank[0]][tank[1]] = '.';
                        tank[0] += 1;
                        arr[tank[0]][tank[1]] = 'v';
                    }
                }
                else if (input == 'L') {
                    arr[tank[0]][tank[1]] = '<';
                    if(tank[1] > 0 && arr[tank[0]][tank[1]-1] == '.'){
                        arr[tank[0]][tank[1]] = '.';
                        tank[1] -= 1;
                        arr[tank[0]][tank[1]] = '<';
                    }
                }
                else if (input == 'R') {
                    arr[tank[0]][tank[1]] = '>';
                    if(tank[1] < w-1 && arr[tank[0]][tank[1]+1] == '.'){
                        arr[tank[0]][tank[1]] = '.';
                        tank[1] += 1;
                        arr[tank[0]][tank[1]] = '>';
                    }
                }
                else if (input == 'S') {
                    if(arr[tank[0]][tank[1]] == '^' ){
                        for(int d = tank[0] ; d >= 0 ; d--){
                            if(arr[d][tank[1]] == '*'){
                                arr[d][tank[1]] = '.';
                                break;
                            }
                            else if(arr[d][tank[1]] == '#'){
                                break;
                            }
                        }
                    } else if (arr[tank[0]][tank[1]] == 'v') {
                        for(int d = tank[0] ; d < h ; d++){
                            if(arr[d][tank[1]] == '*'){
                                arr[d][tank[1]] = '.';
                                break;
                            }
                            else if(arr[d][tank[1]] == '#'){
                                break;
                            }
                        }
                    }else if (arr[tank[0]][tank[1]] == '<') {
                        for(int d = tank[1] ; d >= 0 ; d--){
                            if(arr[tank[0]][d] == '*'){
                                arr[tank[0]][d] = '.';
                                break;
                            }
                            else if(arr[tank[0]][d] == '#'){
                                break;
                            }
                        }
                    }else if (arr[tank[0]][tank[1]] == '>') {
                        for(int d = tank[1] ; d < w ; d++){
                            if(arr[tank[0]][d] == '*'){
                                arr[tank[0]][d] = '.';
                                break;
                            }
                            else if(arr[tank[0]][d] == '#'){
                                break;
                            }
                        }
                    }
                }

            }

            for(int i = 0 ; i < h ; i++){
                for(int j = 0 ; j < w ; j++){
                    sb.append(arr[i][j]);
                }
                sb.append("\n");
            }

        }

        System.out.println(sb);

    }
}
