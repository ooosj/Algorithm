import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] rank = new int[n+1];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        ArrayList<Integer>[] arr = new ArrayList[n+1];

        for(int i = 1 ; i < n+1 ; i++){
            arr[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < m ; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a].add(b);
            rank[b]++;
        }

        for(int i = 1 ; i < n+1 ; i++){
            if(rank[i] == 0){
                pq.add(i);
            }
        }

        while(!pq.isEmpty()){
            int cur = pq.poll();

            sb.append(cur).append(" ");

            for(int next : arr[cur]){
                rank[next]--;

                if(rank[next] == 0){
                    pq.add(next);
                }
            }
        }

        System.out.println(sb);

    }
}
