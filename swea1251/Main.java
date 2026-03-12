import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static long[][] arr;

    static PriorityQueue<long[]> pq = new PriorityQueue<>(new Comparator<long[]>() {
        @Override
        public int compare(long[] o1, long[] o2) {
            return Long.compare(o1[0], o2[0]);
        }
    });
    static int n;
    static double E;
    static long totalDistSq;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            n = Integer.parseInt(br.readLine());

            arr = new long[n][2];
            visited = new boolean[n];
            totalDistSq = 0;
            pq.clear();

            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[j][i] = Long.parseLong(st.nextToken());
                }
            }

            E = Double.parseDouble(br.readLine());

            prim();

            long result = Math.round(totalDistSq * E);
            sb.append("#").append(t + 1).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    static void prim() {
        visited[0] = true;
        int cnt = 1;

        for (int i = 1; i < n; i++) {
            long dx = arr[i][0] - arr[0][0];
            long dy = arr[i][1] - arr[0][1];
            pq.add(new long[]{dx * dx + dy * dy, i});
        }

        while (!pq.isEmpty()) {
            long[] temp = pq.poll();
            long distSq = temp[0];
            int v = (int) temp[1];

            if (!visited[v]) {
                visited[v] = true;
                totalDistSq += distSq;
                cnt++;

                if (cnt == n) return;

                for (int i = 0; i < n; i++) {
                    if (!visited[i]) {
                        long dx = arr[i][0] - arr[v][0];
                        long dy = arr[i][1] - arr[v][1];
                        pq.add(new long[]{dx * dx + dy * dy, i});
                    }
                }
            }
        }
    }
}