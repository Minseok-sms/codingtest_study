import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class algo154 {

    /*
    백준 : 14502
    연구소
 */

    static boolean[][] visited;
    static boolean[][] virusVisited;
    static int N,M;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static ArrayList<Node> virus = new ArrayList<>();
    static int MAX = 0;
    static class Node{
        int row;
        int col;
        Node(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    // 벽 3개 설치하는 과정
    public static void bfs(int row, int col, int[][] tempmap){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(row, col));
        virusVisited[row][col] = true;

        while(!queue.isEmpty()){
            Node temp = queue.poll();

            for(int i = 0 ; i < 4; i++){
                int nextRow = temp.row + dy[i];
                int nextCol = temp.col + dx[i];

                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
                    continue;

                // 바이러스를 감염시키자 -> 가려는곳이 벽이거나, 이미 갓던곳이면 continue
                if(virusVisited[nextRow][nextCol] == true || tempmap[nextRow][nextCol] == 1)
                    continue;

                virusVisited[nextRow][nextCol] = true;
                // 감염
                tempmap[nextRow][nextCol] = 2;
                queue.add(new Node(nextRow, nextCol));
            }
        }

    }
    public static void dfs(int startRow, int startCol, int count, int depth){

        // 3개 설치 완료햇을시, 바이러스 좌표 감염시키고 0의 갯수 체크.
        if(count == depth){
            virusVisited = new boolean[N][M];
            int[][] tempmap = new int[N][M];
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < M; j++){
                    tempmap[i][j] = map[i][j];
                }
            }
            // 벽다놓고 이제 바이러스 감염시키자.
            for(int k = 0 ; k < virus.size(); k++){
                if(virusVisited[virus.get(k).row][virus.get(k).col] == false)
                    bfs(virus.get(k).row, virus.get(k).col, tempmap);
            }

            // 감염다시키고 safe zone = 0 의 갯수 파악
            int safeZoneCnt = 0;
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < M ; j++){
                    if(tempmap[i][j] == 0)
                        safeZoneCnt++;
                }
            }
            MAX = Math.max(MAX, safeZoneCnt);
            return ;
        }
        for(int i = 0 ; i < map.length; i++){
            for(int j = 0 ; j < map[0].length; j++){
                //설치하려는 공간이 이미 설치 or 벽 or 바이러스일경우 continue
                if(visited[i][j] == true || map[i][j] == 1 || map[i][j] == 2)
                    continue;

                visited[i][j] = true;
                map[i][j] = 1;
                dfs(i, j, count + 1, depth);
                map[i][j] = 0;
                visited[i][j] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2)
                    virus.add(new Node(i,j));
            }
        }
        visited = new boolean[N][M];

        dfs(0, 0, 0, 3);
        System.out.println(MAX);

    }
}
