import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < tc ; t++){

            int n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());

            int[] arr = new int[n];

            for(int i = 0 ; i < n ; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int m = Integer.parseInt(br.readLine());



        }
    }
}
