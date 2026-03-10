import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[][] graph = new int[e][3];
        parent = new int[v+1];

        for(int i = 1 ; i < v+1 ; i ++){
            parent[i] = i;
        }

        for(int i = 0 ; i < e ; i++){
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
            graph[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(graph, Comparator.comparingInt(o -> o[2]));

        int cnt = 0;
        int result = 0;

        for(int i = 0 ; i < e ; i++){
            if(union(graph[i][0], graph[i][1])){
                result += graph[i][2];
                cnt++;
            }

            if(cnt == v-1){
                break;
            }

        }

        System.out.println(result);

    }

    public static boolean union(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y)  {
            return false;
        }

        if(x <= y){
            parent[y] = x;
        }
        else {
            parent[x] = y;
        }
        return true;

    }

    public static int find(int x){
        if(parent[x] == x){
            return x;
        }

        return parent[x] = find(parent[x]);
    }

}
