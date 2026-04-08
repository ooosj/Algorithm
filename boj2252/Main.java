import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] rank = new int[n+1];
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        Deque<Integer> q = new ArrayDeque<>();

        for(int i = 1 ; i < n+1 ; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            rank[b]++;
        }

        for(int i = 1 ; i < n+1 ; i++){
            if(rank[i] == 0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            sb.append(cur).append(" ");

            for(int next : graph[cur]){
                rank[next]--;
                if(rank[next] == 0){
                    q.add(next);
                }
            }
        }

        System.out.println(sb);

    }
}
