import java.util.*;
import java.io.*;

public class Main {
    static int N, personCount;
    static List<int[]> persons;
    static List<int[]> stairs;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            persons = new ArrayList<>();
            stairs = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int val = Integer.parseInt(st.nextToken());
                    if (val == 1) persons.add(new int[]{i, j});
                    else if (val > 1) stairs.add(new int[]{i, j, val});
                }
            }

            personCount = persons.size();
            // 모든 선택 조합을 저장할 DP 배열 (사실상 모든 경우의 수 기록)
            dp = new int[1 << personCount];
            int answer = Integer.MAX_VALUE;

            // 1. 모든 비트 상태(어느 계단을 갈지)에 대해 시뮬레이션 수행
            for (int state = 0; state < (1 << personCount); state++) {
                dp[state] = solve(state);
                answer = Math.min(answer, dp[state]);
            }

            System.out.println("#" + tc + " " + answer);
        }
    }

    static int solve(int state) {
        // 두 계단 각각의 소요 시간 중 더 큰 값이 해당 상태의 전체 종료 시간
        return Math.max(calcTime(state, 0), calcTime(state, 1));
    }

    static int calcTime(int state, int stairIdx) {
        List<Integer> arrivalTimes = new ArrayList<>();
        int[] s = stairs.get(stairIdx);

        for (int i = 0; i < personCount; i++) {
            // 비트가 stairIdx와 일치하는 사람만 골라냄
            int bit = (state >> i) & 1;
            if (bit == stairIdx) {
                int[] p = persons.get(i);
                arrivalTimes.add(Math.abs(p[0] - s[0]) + Math.abs(p[1] - s[1]));
            }
        }

        if (arrivalTimes.isEmpty()) return 0;
        Collections.sort(arrivalTimes);

        // 계단 시뮬레이션 (DP 요소: 이전 사람의 완료 시간이 다음 사람에게 영향을 줌)
        int[] finishTimes = new int[arrivalTimes.size()];
        for (int i = 0; i < arrivalTimes.size(); i++) {
            int arrival = arrivalTimes.get(i);

            if (i < 3) {
                // 3명까지는 대기 없이 도착 1분 후 진입 가능
                finishTimes[i] = arrival + 1 + s[2];
            } else {
                // 4번째 사람부터는 3명 전 사람의 완료 시간과 비교
                // (3명 전 사람이 나가는 시간) vs (내가 도착해서 1분 대기한 시간) 중 늦은 시간에 진입
                int readyToEnter = Math.max(arrival + 1, finishTimes[i - 3]);
                finishTimes[i] = readyToEnter + s[2];
            }
        }

        return finishTimes[arrivalTimes.size() - 1];
    }
}