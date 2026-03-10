import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int tc = 0 ; tc < 10 ; tc++){

            sb.append("#").append(tc+1).append(" ");

            LinkedList<Integer> list = new LinkedList<>();

            int n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 0 ; i < n ; i++){
                list.add(Integer.parseInt(st.nextToken()));
            }

            int op = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());

            for(int i = 0 ; i < op ; i++){
                String s = st.nextToken();
                if(s.equals("I")){
                    int idx = Integer.parseInt(st.nextToken());

                    int time = Integer.parseInt(st.nextToken());

                    for(int j = 0 ; j < time; j++){
                        list.add(idx++,Integer.parseInt(st.nextToken()));
                    }

                } else if (s.equals("D")) {
                    int idx = Integer.parseInt(st.nextToken());

                    int time = Integer.parseInt(st.nextToken());

                    for(int j = 0 ; j < time; j++){
                        list.remove(idx);
                    }
                }
            }

            for(int i = 0 ; i < 10 ; i++){
                sb.append(list.get(i)).append(" ");
            }

            sb.append("\n");

        }

        System.out.println(sb.toString());

    }
}
