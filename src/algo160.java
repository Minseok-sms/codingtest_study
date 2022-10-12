import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class algo160 {

    /*
        백준 : 20055
        컨베이어 벨트 위의 로봇
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] array = new int[N * 2];
        int[] robot = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N * 2 ; i++)
            array[i] = Integer.parseInt(st.nextToken());
        int answer = 0;
        while(true){

            // 9 8 10 10 10 10
            // 벨트를 한칸 회전하자
            int last = 0;
            for(int i = 2 * N - 1 ; i >= 0; i--){
                if(i == 2 * N - 1)
                    last = array[2* N - 1];
                else
                    array[i + 1] = array[i];
            }
            array[0] = last;

            //로봇도 함께 회전하자
            for(int i = N - 1; i >= 1; i--){
                robot[i] = robot[i - 1];
            }
            robot[0] = 0;

            // 벨트가 회전하는 방향으로 로봇 한칸이동하자, (먼저 올라간로봇부터)
            // 만약 이동하려는곳에 로봇이 잇거나, 내구도가 0이면불가
            for(int i = N - 1; i >= 0; i--){
                // 로봇이없으면 다음꺼
                if(robot[i] == 0)
                    continue;
                if( i == N - 1 && robot[i] == 1) {
                    robot[i] = 0;
                    continue;
                }
                // 내구도가 0 이거나, 다음칸에 로봇이잇을경우
                else if(array[i + 1] == 0 || robot[i + 1] == 1)
                    continue;

                //아니면 로봇이동하고 내구도 -1
                robot[i + 1] = robot[i];
                robot[i] = 0;
                array[i + 1]--;
            }

            // 올리는 위치에 칸의 내구도가 0이아니면 로봇올리고 내구도 하나차감
            if(array[0] != 0) {
                robot[0] = 1;
                array[0]--;
            }

            // 내구도 확인
            int cnt = 0;
            for(int i = 0 ; i < 2 * N; i++){
                if(array[i] == 0)
                    cnt++;
            }

            answer++;
            // 내구도 0이 K이상일 시 종료후 횟수반환
            if(cnt >= K)
                break;
        }
        System.out.println(answer);

    }
}
