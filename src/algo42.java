import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class algo42 {

    static boolean[][] visited;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static int N,M;
    static int globalCount = 0;
    static void CheckDfs(int start, int end){
        visited[start][end] = true;
        map[start][end] = 2;

        for(int i = 0; i < 4; i++){
            int nextRow = start + dy[i];
            int nextCol = end + dx[i];
            if(0 <= nextRow && nextRow <= N - 1 && 0 <= nextCol && nextCol <= M - 1){
                if(visited[nextRow][nextCol] == false && (map[nextRow][nextCol] == 0 || map[nextRow][nextCol] == 2)){
                    visited[nextRow][nextCol] = true;
                    CheckDfs(nextRow, nextCol);
                }
            }
        }
    }
    static void dfs(int start, int end){
        if(map[start][end] == 0 || map[start][end] == 2)
            return ;

        visited[start][end] = true;

        int airCount = 0;
        for(int i = 0 ; i < 4; i++){
            int nextRow = start + dy[i];
            int nextCol = end + dx[i];
            if(0 <= nextRow && nextRow <= N - 1 && 0 <= nextCol && nextCol <= M - 1){
                if(map[nextRow][nextCol] == 2 && visited[nextRow][nextCol] == false){
                    airCount++;
                }
            }
        }
        if(airCount >= 2){
            map[start][end] = 2;
            globalCount++;
        }

        for(int i = 0 ; i < 4; i++){
            int nextRow = start + dy[i];
            int nextCol = end + dx[i];
            if(0 <= nextRow && nextRow <= N - 1 && 0 <= nextCol && nextCol <= M - 1){
                if(visited[nextRow][nextCol] == false && map[nextRow][nextCol] == 1){
                    visited[nextRow][nextCol] = true;
                    dfs(nextRow, nextCol);

                }
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        int answer = 0;
        while(true) {
            globalCount = 0;
            visited = new boolean[N][M];
            CheckDfs(0, 0);
            visited = new boolean[N][M];
            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < M - 1; j++) {
                    dfs(i,j);
                }
            }
            if(globalCount > 0){
                answer++;
            }else
                break;
        }
        System.out.println(answer);
    }
}
