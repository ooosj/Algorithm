import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int idx;
        int weight;

        Node(int idx, int weight){
            this.idx = idx;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();

        for(int i = 0 ; i < v+1 ; i++){
            graph.add(new ArrayList<Node>());
        }

        for(int i = 0 ; i < e ; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, w));
        }

        int[] dist = new int[v+1];
        for(int i = 1 ; i < v+1; i++){
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> pq = new PriorityQueue<Node>(((o1, o2) -> Integer.compare(o1.weight, o2.weight)));

        pq.add(new Node(k, 0));
        dist[k] = 0;

        while(!pq.isEmpty()){

            Node cur = pq.poll();

            if(dist[cur.idx] < cur.weight) continue;

            for(int i = 0 ; i < graph.get(cur.idx).size() ; i++){
                Node next = graph.get(cur.idx).get(i);

                if(dist[next.idx] > cur.weight + next.weight){
                    dist[next.idx] = cur.weight + next.weight;
                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        for(int i = 1 ; i < v+1 ; i++){
            if(dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            }
            else{
                System.out.println(dist[i]);
            }
        }

    }
}
