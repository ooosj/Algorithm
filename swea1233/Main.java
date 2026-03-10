import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int tc = 0 ; tc < 10 ; tc++){

            int n = Integer.parseInt(br.readLine());

            int leaf = n/2 + 1;

            int result = 1;
            
            for(int j = 0 ; j < n ; j++){

                String st = br.readLine();

                String[] arr = st.split(" ");
                
                if(Integer.parseInt(arr[0]) < leaf){
                    if (arr.length < 4){
                        result = 0;
                    }

                    String op = arr[1];

                    if(!(op.equals("+") || op.equals("-") || op.equals("/") || op.equals("*"))){
                        result = 0;
                    }
                } else {
                    if (arr.length != 2){
                        result = 0;
                    }

                    String  num = arr[1];

                    if(num.equals("+") || num.equals("-") || num.equals("/") || num.equals("*")){
                        result = 0;
                    }

                }

            }

            sb.append("#").append(tc+1).append(" ").append(result).append("\n");

        }

        System.out.println(sb.toString());

    }
}
