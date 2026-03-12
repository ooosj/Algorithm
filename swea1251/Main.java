import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static long[][] arr;
    static PriorityQueue<int[]> pq;
    static int n;
    static double E;
    static int cnt;
    static int result;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < tc ; t++){
            n = Integer.parseInt(br.readLine());

            cnt = n;
            arr = new long[n][2];
            visited = new boolean[n];
            result = 0;

            pq = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });

            for(int i = 0 ; i < 2 ; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < n ; j++){
                    arr[j][i] = Long.parseLong(st.nextToken());
                }
            }

            E = Double.parseDouble(br.readLine());

            prim();

            sb.append("#").append(t+1).append(" ").append(result).append("\n");

        }

    }

    static void prim(){
        if(pq.isEmpty()){
            visited[0] = true;
            for(int i = 1 ; i < n ; i++){
                int dist = (int)Math.sqrt(Math.pow(arr[i][0] - arr[0][0], 2) + Math.pow(arr[i][1] - arr[0][1], 2));
                pq.add(new int[]{dist, i});
            }
        }

        while(true){
            int[] temp = pq.poll();
            int e = temp[0];
            int v = temp[1];

            if(!visited[v]){
                cnt++;

                if(cnt == n-1) return;

                result += E*e*e;
                for(int i = 0 ; i < n ; i++){
                    if(i == v) continue;
                    int dist = (int)Math.sqrt(Math.pow(arr[i][0] - arr[v][0], 2) + Math.pow(arr[i][1] - arr[v][1], 2));
                    pq.add(new int[]{dist, i});
                }
            }
        }
    }

}
