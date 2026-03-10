import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int result_max, result_min;
    static int[] arr;

    static void dfs(int idx, int cur, int plus, int minus, int mul, int div) {
        if (idx == n) {
            result_min = Math.min(result_min, cur);
            result_max = Math.max(result_max, cur);
            return;
        }

        int next = arr[idx];

        if (plus > 0)  dfs(idx + 1, cur + next, plus - 1, minus, mul, div);
        if (minus > 0) dfs(idx + 1, cur - next, plus, minus - 1, mul, div);
        if (mul > 0)   dfs(idx + 1, cur * next, plus, minus, mul - 1, div);
        if (div > 0)   dfs(idx + 1, cur / next, plus, minus, mul, div - 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int plus = Integer.parseInt(st.nextToken());
            int minus = Integer.parseInt(st.nextToken());
            int mul = Integer.parseInt(st.nextToken());
            int div = Integer.parseInt(st.nextToken());

            arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

            result_max = Integer.MIN_VALUE;
            result_min = Integer.MAX_VALUE;

            dfs(1, arr[0], plus, minus, mul, div);

            sb.append("#").append(t).append(" ").append(result_max - result_min).append("\n");
        }
        System.out.print(sb);
    }
}
