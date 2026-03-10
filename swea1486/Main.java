import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int b;
    static int result;
    static boolean[] visited;
    static Integer[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        for(int t = 0; t < tc ; t++){
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            arr = new Integer[n];
            visited = new boolean[n];

            for(int i = 0 ; i < n ; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr, Comparator.reverseOrder());

            result = Integer.MAX_VALUE;

            for(int i = 1 ; i < n + 1 ; i++){
                dfs(0, 0, i, 0);
            }

            sb.append("#").append(t+1).append(" ").append(result - b).append("\n");

        }
        System.out.println(sb);
    }

    static void dfs(int depth, int temp, int cnt, int start){
        if(temp > result){
            return;
        }

        if(temp >= b){
            result = temp;
            return;
        }

        if(depth == cnt){
            return;
        }

        for(int i = start ; i < n; i++){
            temp += arr[i];
            dfs(depth+1, temp, cnt, i+1);
            temp -= arr[i];
        }
    }

}
