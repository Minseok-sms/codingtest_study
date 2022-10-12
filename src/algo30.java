import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class algo30 {

    static int N,K;
    static int[][] map;
    static boolean[] visited;
    static int answer = 0;
    public static void dfs(int start, int weight ,int value){
        if( weight <= K){
            if(value >= answer)
                answer = value;
        }
        for(int i = start; i < map.length; i++){
            if(visited[i] == true)
                continue;

            visited[i] = true;
            dfs(i, weight + map[i][0],value + map[i][1]);
            visited[i] = false;

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][2];
        visited = new boolean[N];

        /*
        4 7

        6 13
        4 8
        3 6
        5 12
         */

        // dp = [5][8]

        for(int i = 1; i < N + 1; i++){
             st = new StringTokenizer(br.readLine(), " ");
             map[i][0] = Integer.parseInt(st.nextToken());
             map[i][1] = Integer.parseInt(st.nextToken());
        }
        int[][] dp = new int[N + 1][K + 1];
        for(int i = 1; i < N + 1; i++){
            for(int j = 1; j < K + 1; j++){
                // 가방의 제한 무게가 넣으려는 보석의 무게보다 가벼울 때: i-1번째 보석을 넣은 dp값 사용
                if(j < map[i][0])
                    dp[i][j] = dp[i-1][j];
                    // i번째 보석을 넣을 수 있는 경우
                    // i번째 보석의 무게를 뺐을 때의 value+i번째 보석 value와 dp[i-1][j]를 비교
                else
                    dp[i][j] = Math.max(dp[i-1][j-map[i][0]] + map[i][1], dp[i-1][j]);
            }
        }

        dfs(0, 0,0);
        System.out.println(answer);

    }
}
