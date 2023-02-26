import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class algo191 {

    static char[][] map;
    static int N;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void dfs(int row, int col, char color){


        visited[row][col] = true;

        for(int i = 0 ; i < 4; i++){
            int nextRow = row + dy[i];
            int nextCol = col + dx[i];

            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N)
                continue;
            if(visited[nextRow][nextCol] == true || map[nextRow][nextCol] != color)
                continue;

            visited[nextRow][nextCol] = true;
            dfs(nextRow, nextCol, color);
        }
    }

    public static void dfs2(int row, int col, char color){



        for(int i = 0 ; i < 4; i++){
            int nextRow = row + dy[i];
            int nextCol = col + dx[i];

            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N)
                continue;
            if(visited[nextRow][nextCol] == true)
                continue;


            if(map[row][col] == 'B'){
                if(map[nextRow][nextCol] == 'B') {
                    visited[nextRow][nextCol] = true;
                    dfs2(nextRow, nextCol, 'B');

                }

            }else{
                if(map[nextRow][nextCol] == 'R' || map[nextRow][nextCol] == 'G') {
                    visited[nextRow][nextCol] = true;
                    dfs2(nextRow, nextCol, 'R');

                }
            }

        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        for(int i = 0 ; i < N; i++){
            String str = br.readLine();
            for(int j = 0 ; j  < N ; j++){
                map[i][j] = str.charAt(j);
            }
        }

        visited = new boolean[N][N];

        // 적록색야이 아닌사람이 봣을경우.
        int cnt = 0;
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                if(visited[i][j] == true)
                    continue;
                cnt++;
                dfs(i, j, map[i][j]);
            }
        }

        // 적록색약이 봣을경우.
        visited = new boolean[N][N];
        int cnt2 = 0;
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                if(visited[i][j] == true)
                    continue;
                visited[i][j] = true;
                cnt2++;
                dfs2(i, j, map[i][j]);
            }
        }

        System.out.println(cnt + " " + cnt2);





    }
}
