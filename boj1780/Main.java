import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] map;
    static int[] arr;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        arr = new int[3];
        visited = new boolean[n/3][n/3];

        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < n ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        partition(0,0, n);

        for(int i = 0 ; i < 3 ; i++){
            System.out.println(arr[i]);
        }

    }

    static boolean check( int size, int r, int c, int num){

        for(int i = r ; i < r + size ; i++){
            for(int j = c ; j < c + size ; j++){
                if(map[i][j] != num){
                    return false;
                }
            }
        }

        return true;
    }

    static void partition(int r, int c, int size){

        if (check(size, r, c, map[r][c])) {
            if(map[r][c] == -1) {
                arr[0]++;
            }
            else if(map[r][c] == 0) {
                arr[1]++;
            }
            else {
                arr[2]++;
            }

            return;
        }

        int newSize = size / 3;

        partition(r, c, newSize);								// 왼쪽 위
        partition(r, c + newSize, newSize);						// 중앙 위
        partition(r, c + 2 * newSize, newSize);					// 오른쪽 위

        partition(r + newSize, c, newSize);						// 왼쪽 중간
        partition(r + newSize, c + newSize, newSize);			// 중앙 중간
        partition(r + newSize, c + 2 * newSize, newSize);		// 오른쪽 중간

        partition(r + 2 * newSize, c, newSize);					// 왼쪽 아래
        partition(r + 2 * newSize, c + newSize, newSize);		// 중앙 아래
        partition(r + 2 * newSize, c + 2 * newSize, newSize);	// 오른쪽 아래

    }

}
