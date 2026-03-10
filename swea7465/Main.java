import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Integer[] parents;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        int tc = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < tc ; t++){
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            parents = new Integer[n+1];
            rank = new int[n+1];

            for(int i = 1 ; i < n+1 ; i++){
                parents[i] = i;
            }

            for(int i = 0 ; i < m ; i++){
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                union(x, y);

            }

            Set<Integer> set = new HashSet<>();

            for(int i = 1 ; i < n+1 ; i++){
                set.add(find(i));
            }

            sb.append("#").append(t+1).append(" ").append(set.size()).append("\n");

        }

        System.out.println(sb);
    }

    static int find(int x){

        if (parents[x] == x) return x;

        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y){

        int unionX = find(x);
        int unionY = find(y);

        if( unionX == unionY) return;

        if(rank[unionX] == rank[unionY]){
            parents[unionY] = unionX;
            rank[unionX]++;
        } else if (rank[unionX] > rank[unionY]) {
            parents[unionY] = unionX;
        } else if (rank[unionX] < rank[unionY]) {
            parents[unionX] = unionY;
        }

    }
}
