import java.net.*;
import java.io.*;
import java.util.*;

public class Main {
    private static final String NICKNAME = "통신테스트";
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8747;

    private static Socket socket = null;

    public static void main(String[] args) {
        String gameData = init(NICKNAME);

        // while 반복문: 배틀싸피 메인 프로그램과 클라이언트(이 코드)가 데이터를 계속해서 주고받는 부분
        while (gameData.length() > 0) {

        	// 수신 데이터 출력
            System.out.printf("%s\n", gameData);

            // 커맨드 지정
            String output = "A";

            // 커맨드 전송
            gameData = submit(output);
            
            // 강제 종료(통신테스트용)
            break;
        }

        close();
    }

    // 초기 연결
    private static String init(String nickname) {
        try {
            socket = new Socket();
            System.out.println("[STATUS] Connecting to " + HOST + ":" + PORT);
            socket.connect(new InetSocketAddress(HOST, PORT));
            System.out.println("[STATUS] Connected");

            return submit("INIT " + nickname);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    // 명령 전송
    private static String submit(String command) {
        try {
            OutputStream os = socket.getOutputStream();
            os.write((command + " ").getBytes("UTF-8"));
            os.flush();

            return "[RESULT] test ok";
        } catch (Exception e) {
            close();
            return "";
        }
    }


    // 소켓 종료
    private static void close() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                System.out.println("[STATUS] Connection Closed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
