import java.util.LinkedList;
import java.util.Queue;

public class algo85 {

    static boolean[][] visited;
    static int N,M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1 ,0 ,1};
    static int MIN = Integer.MAX_VALUE;
    static class Node{
        int x;
        int y;
        int count;
        Node(int y, int x, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    public static void bfs(int[][] maps, int row, int col){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(row, col, 1));
        visited[row][col] = true;

        while(!queue.isEmpty()){
            Node node = queue.poll();

            if(node.y == N - 1 && node.x == M - 1){
                MIN = Math.min(MIN, node.count);
                continue;
            }

            for(int i = 0 ; i < 4 ; i++){
                int nextRow = node.y + dy[i];
                int nextCol = node.x + dx[i];
                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
                    continue;
                if(visited[nextRow][nextCol] == true || maps[nextRow][nextCol] == 0)
                    continue;

                visited[nextRow][nextCol] = true;
                queue.add(new Node(nextRow, nextCol, node.count + 1));
            }
        }
    }
    public static void dfs(int[][] maps, int row, int col, int count){
        visited[row][col] = true;
        if(row == N - 1 && col == M - 1){
            MIN = Math.min(MIN, count);
            return ;
        }

        for(int i = 0 ; i < 4; i++){
            int nextRow = row + dy[i];
            int nextCol = col + dx[i];
            if(nextCol < 0 || nextCol >= M || nextRow < 0 || nextRow >= N)
                continue;

            if(visited[nextRow][nextCol] == true || maps[nextRow][nextCol] == 0)
                continue;

            visited[nextRow][nextCol] = true;
            dfs(maps,nextRow, nextCol, count + 1);
            visited[nextRow][nextCol] = false;

        }
    }
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        visited = new boolean[N][M];

       // dfs(maps, 0, 0, 1);
        bfs(maps, 0, 0);

        if(MIN == Integer.MAX_VALUE)
            return -1;
        else
            return MIN;

    }
    public static void main(String[] args) {

    }
}
