import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class algo151 {


    static int N, M;
    static int dir = 0;
    static int nRow, nCol;
    static int clean = 1;
    static int[][] map;
    static boolean[][] visited;
    //    북 = 0, 동 = 1, 남 = 2, 서 = 3
    static int[] dx = {0,  1 , 0 , -1};
    static int[] dy = {-1, 0 , 1 ,  0};
    public static void dfs(int row, int col, int direction){

        visited[row][col] = true;

        boolean check = false;
        for(int i = 0 ; i < 4; i++){
            direction = (direction + 3) % 4;
            int nextRow = row + dy[direction];
            int nextCol = col + dx[direction];

            // 왼쪽방향에 청소할 공간이 업으면
            if(visited[nextRow][nextCol] == true || map[nextRow][nextCol] == 1)
                continue;

            check = true;
            visited[nextRow][nextCol] = true;
            clean++;
            dfs(nextRow, nextCol, direction);
        }

        // 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는
        // 바라보는 방향기준 한칸후진
        if(check == false){
            int nextRow = row + dy[(direction + 2) % 4];
            int nextCol = col + dx[(direction + 2) % 4];
            if(map[nextRow][nextCol] == 1) {
                System.out.println(clean);
                System.exit(0);
            }
            else
                dfs(nextRow, nextCol, direction);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        nRow = Integer.parseInt(st.nextToken());
        nCol = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        dfs(nRow, nCol, dir);
        System.out.println(clean);
    }
}
