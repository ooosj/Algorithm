import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Deque<Integer> q = new ArrayDeque<>();

        int n = Integer.parseInt(br.readLine());

        int result = 0;

        q.add(0);

        for(int i = 0 ; i < n ; i++ ){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(y > q.peekLast()){
                result++;
                q.addLast(y);
            }
            else {
                while(q.peekLast() > y){
                    q.pollLast();
                }

                if(q.peekLast() < y ){
                    q.addLast(y);
                    result++;
                }
            }
        }



        System.out.println(result);
    }
}
