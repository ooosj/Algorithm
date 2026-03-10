import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int tc = sc.nextInt();

        for(int t = 0 ; t < tc ; t++){
            int n = sc.nextInt();

            int[] arr = new int[n+1];

            for(int i = 0 ; i < n ; i++){
                arr[i] = sc.nextInt();
            }

            int po1 = 0;
            int po2 = 0;
            int top = n;

            int result = 0;

            while(true){
                if(po2 == n-1){
                    break;
                }

                if(arr[po2] > arr[po2+1]){      // 다음 인덱스 감소
                    if(po1 == po2){
                        po1++;
                        po2++;
                    }
                    else if (po1 < po2) {
                        if(arr[top] < arr[po2]){
                            top = po2;
                        }

                        po2++;

                        for(int i = po1 ; i < top  ; i++){
                            result++;
                        }
                    }
                }
                else if(arr[po2] < arr[po2+1]){     // 다음 인덱스 증가
                    if(po1 == po2){
                        po2++;
                    }
                    else if (po1 < po2) {
                        if(arr[po2-1] < arr[po2]){
                            po2++;
                        }
                        else {
                            po1 = po2;
                            top = n;
                            po2++;
                        }
                    }
                }
            }

            sb.append("#").append(t+1).append(" ").append(result).append("\n");

        }
        System.out.println(sb);
    }
}
