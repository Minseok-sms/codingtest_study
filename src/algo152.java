import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class algo152 {

    /*
    안전영역 : 2468
     */
    static int[][] map;
    static int low = 0;
    static int high = 0;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;

    static class Node{
        int row;
        int col;
        Node(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    public static void bfs(int row, int col, int k){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(row, col));
        visited[row][col] = true;

        while(!queue.isEmpty()){
            Node temp = queue.poll();

            for(int i = 0 ; i < 4 ; i++){
                int nextRow = temp.row + dy[i];
                int nextCol = temp.col + dx[i];

                if(nextRow < 0 || nextRow >= map.length || nextCol < 0 || nextCol >= map.length)
                    continue;

                if(visited[nextRow][nextCol] == true || map[nextRow][nextCol] <= k)
                    continue;

                queue.add(new Node(nextRow, nextCol));
                visited[nextRow][nextCol] = true;
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int j = 0 ; j  < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                high = Math.max(map[i][j], high);
            }
        }
        int answer = 0;
        for(int k = low ; k <= high; k++){
            visited = new boolean[N][N];
            int maxRange = 0;
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j  < N ; j++){
                    // 잠수안되는 좌표 입장, visited = false일때 입장,
                    if(map[i][j] > k && visited[i][j] == false){
                        maxRange++;
                        bfs(i,j, k);
                    }
                }
            }
            answer = Math.max(answer, maxRange);
        }
        System.out.println(answer);
    }
}
