import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] rgb = new int[n+1][4];
        int[] check = new int[n+2];
        PriorityQueue <int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        for(int i = 1 ; i < n+1 ; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 1 ; j < 4 ; j++){
                int t = Integer.parseInt(st.nextToken());
                rgb[i][j] = t;
                pq.add(new int[]{t, i, j});
            }
        }

        int cnt = 0;
        int result = 0;

        for(int i = 0 ; i < 3 * n ; i++){
            int[] temp = pq.poll();

            int r = temp[1];
            int c = temp[2];
            int v = temp[0];

            if(check[r-1] != c && check[r+1] != c && check[r] == 0){
                check[r] = c;
                cnt++;
                result += v;
            }

            if(cnt == n) break;

        }

        System.out.println(result);

    }
}
