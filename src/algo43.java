import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    3109 : 빵집
    
 */
public class algo43 {

    static int N,M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, 1, 1};
    static int[] dy = {-1, 0, 1};
    static int globalCount = 0;
    static int flag = 0;
    public static void dfs(int start, int end){
        visited[start][end] = true;
        if(end == M - 1){
            globalCount++;
            flag = 1;
            return ;
        }
        if(flag == 1){
            return ;
        }
        for(int i = 0 ; i < 3; i++){
            int nextRow = start + dy[i];
            int nextCol = end + dx[i];
            if(0 <= nextRow && nextRow <= N - 1 && 0 <= nextCol && nextCol <= M - 1){
                if(visited[nextRow][nextCol] == false && map[nextRow][nextCol] == 1 && flag == 0){
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
        visited = new boolean[N][M];
        map = new int[N][M];

        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M; j++){
                if(str.charAt(j) == '.')
                    map[i][j] = 1;
                else
                    map[i][j] = 0;
            }
        }
        int answer = 0;
        for(int i = 0 ; i < N; i++){
            dfs(i,0);
            answer += globalCount;
            globalCount = 0;
            flag = 0;
        }
        System.out.println(answer);


    }
}
