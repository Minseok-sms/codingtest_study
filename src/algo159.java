import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class algo159 {

    /*
   백준 : 2146
    다리만들기
     */
    static int totalIsland = 0;
    static int[][] map;
    static int[][] tempmap;
    static int N;
    static boolean[][] visited;
    static boolean[][] zeroVisited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static ArrayList<Node> array = new ArrayList<>();
    static int zeroRow, zeroCol;
    static class Node{
        int row;
        int col;
        Node(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    public static void dfs(int row, int col,int count, int depth){

        // 다리 다놧으면 이제 검사
        if(count == depth){

            // 총 섬이 하나 감소하면 그 depth가 답
            visited = new boolean[N][N];
            int tempIsland = 0;
            for(int i = 0; i < array.size(); i++){
                if(visited[array.get(i).row][array.get(i).col] == false) {
                    findIsland(array.get(i).row, array.get(i).col, tempmap);
                    tempIsland++;
                }
            }
            if(tempIsland != totalIsland){
                System.out.println(depth);
                System.exit(0);
            }
            return ;
        }
        for(int i = 0 ; i < 4; i++){
            int nextRow = row + dy[i];
            int nextCol = col + dx[i];

            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N)
                continue;
            if(zeroVisited[nextRow][nextCol] == true || tempmap[nextRow][nextCol] == 1)
                continue;

            zeroVisited[nextRow][nextCol] = true;
            tempmap[nextRow][nextCol] = 1;
            dfs(nextRow, nextCol, count + 1, depth);
            tempmap[nextRow][nextCol] = 0;
            zeroVisited[nextRow][nextCol] = false;
        }
    }
    public static void findIsland(int row, int col, int[][] map){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(row, col));
        visited[row][col] = true;

        while(!queue.isEmpty()){
            Node temp = queue.poll();

            for(int i = 0 ; i < 4; i++){
                int nextRow = temp.row + dy[i];
                int nextCol = temp.col + dx[i];

                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N)
                    continue;

                if(visited[nextRow][nextCol] == true || map[nextRow][nextCol] == 0)
                    continue;

                visited[nextRow][nextCol] = true;
                queue.add(new Node(nextRow, nextCol));
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1)
                    array.add(new Node(i, j));
                else {
                    zeroRow = i;
                    zeroCol = j;
                }
            }
        }


        // 일단 총 섬의 갯수를 파악
        visited = new boolean[N][N];
        for(int i = 0; i < array.size(); i++){
            if(visited[array.get(i).row][array.get(i).col] == false) {
                findIsland(array.get(i).row, array.get(i).col, map);
                totalIsland++;
            }
        }


        // 다리를 놔보자
        for(int i = 1 ; i <= N; i++){

            // map하나 복사
            tempmap = new int[N][N];
            for(int j = 0 ; j < N ; j++)
                System.arraycopy(map[j], 0, tempmap[j], 0, map[j].length);

            zeroVisited = new boolean[N][N];
            dfs(zeroRow, zeroCol, 0, i);

        }

    }
}
