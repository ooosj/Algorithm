import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int[] rank;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        int tc = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < tc ; t++){

            sb.append("#").append(t+1).append(" ");

            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            arr = new int[m][3];
            rank = new int[n+1];
            parents = new int[n+1];


            for(int i = 0 ; i < n ; i++){
                parents[i] = i;
            }

            for(int i = 0 ; i < m ; i++){
                st = new StringTokenizer(br.readLine());

                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
                arr[i][2] = Integer.parseInt(st.nextToken());

                if(arr[i][0] == 0){
                    union(arr[i][1], arr[i][2]);
                }
                else{
                    if(findset(arr[i][1]) == findset(arr[i][2])){
                        sb.append(1);
                    }
                    else {
                        sb.append(0);
                    }
                }
            }

            sb.append("\n");

        }

        System.out.println(sb);

    }

    static void union(int x, int y){
        int unionX = findset(x);
        int unionY = findset(y);

        if(unionX == unionY){
            return;
        }

        if(rank[unionX] == rank[unionY]){
            parents[unionY] = unionX;
            rank[unionX]++;
        } else if (rank[unionX] > rank[unionY]) {
            parents[unionY] = unionX;
        } else if (rank[unionX] < rank[unionY]) {
            parents[unionX] = unionY;
        }
    }

    static int findset(int x){
        if(x == parents[x]) return x;

        return parents[x] = findset(parents[x]);
    }


}
