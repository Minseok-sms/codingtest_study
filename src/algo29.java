import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class algo29 {
    static int N,M;
    static int[][] map;
    static int[][] tempMap;
    static boolean[][] visited;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static int globalCount = 0;

    public static void dfs(int start, int end){
        if(visited[start][end] == false){
            visited[start][end] = true;
            //globalCount++;
        }
        for(int i = 0 ; i < 4; i++){
            int nextRow = start + dy[i];
            int nextCol = end + dx[i];

            if(nextRow <= N - 2 && nextRow >= 1 && nextCol <= M - 2 && nextCol >= 1){
                if(visited[nextRow][nextCol] == false && map[nextRow][nextCol] != 0){
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
        tempMap = new int[N][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                tempMap[i][j] = map[i][j];
            }
        }
        int answer = 0;
        while(true){
            visited = new boolean[N][M];
            globalCount = 0;
            for(int i = 1; i < N - 1; i++){
                for(int j = 1; j < M - 1; j++){
                    if(visited[i][j] == false && map[i][j] != 0) {
                        dfs(i, j);
                        globalCount++;
                    }
                }
            }

            if(globalCount >= 2) {
                System.out.println(answer);
                break;
            }
            else
                answer++;



            visited = new boolean[N][M];
            for(int i = 1; i < N - 1; i++){
                for(int j = 1; j < M - 1; j++){
                    int zeroCount = 0;

                    if(map[i][j] == 0)
                        continue;
                    visited[i][j] = true;
                    for(int k = 0 ; k < 4; k++){
                        int nextRow = i + dy[k];
                        int nextCol = j + dx[k];
                        if(map[nextRow][nextCol] == 0 && visited[nextRow][nextCol] == false) {
                            zeroCount++;
                            visited[nextRow][nextCol] = true;
                        }
                    }
                    map[i][j] -= zeroCount;
                    if(map[i][j] < 0) {
                        map[i][j] = 0;
                    }

                }
            }
        }
    }
}
