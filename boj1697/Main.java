import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int[] dp;
    static int s;
    static int e;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Deque<int[]> q = new ArrayDeque<>();

        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        dp = new int[Math.max(s, e) * 2];

        for(int i = 0 ; i < e*2 ; i++){
            dp[i] = Integer.MAX_VALUE;
        }

        result = Math.abs(e - s);

        q.add(new int[]{s, 0});

        while(!q.isEmpty()){

            int[] cur = q.poll();

            if(e == cur[0]){
                result = cur[1];
                break;
            }

            if(dp[cur[0]] > cur[1]){

                dp[cur[0]] = cur[1];

                if(cur[0]+1 < dp.length) q.add(new int[]{cur[0]+1,cur[1]+1});
                if(cur[0]-1 > 0) q.add(new int[]{cur[0]-1,cur[1]+1});
                if(cur[0]*2 < dp.length) q.add(new int[]{cur[0]*2,cur[1]+1});
            }


        }

        System.out.println(result);

    }
}
