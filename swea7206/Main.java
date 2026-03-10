import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int n;
    static int[] arr;
    static boolean[] visited;
    static int[] num = {0,1,2,3,4,5};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < tc ; t++){
            String str = br.readLine();

            n = str.length();



            arr = new int[n-1];
            visited = new boolean[n];

            perm(0, 3);

//            for(int i = 1 ; i < st.length() ; i++){
//                for(int j =)
//            }

        }
    }

    static void perm(int depth, int r){
        if(depth == n-1){
            System.out.println(Arrays.toString(arr));
            return;
        }

        for(int i = 1 ; i < n ; i++){
            if(!visited[i]){
                visited[i] = true;
                arr[depth] = num[i];
                perm(depth+1, r);
                visited[i] = false;
            }
        }

    }

    static void dfs(){

    }
}
