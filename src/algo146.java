import java.util.LinkedList;
import java.util.Queue;

public class algo146 {


    static int[] dx = {1, 0, -1 ,0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][][] visited;
    static int result = Integer.MAX_VALUE;
    static int totalSize = 0;
    static class Node{
        int row;
        int col;
        int cost;
        int dir;

        Node(int y, int x, int cost, int dir){
            this.row = y;
            this.col = x;
            this.cost = cost;
            this.dir = dir;
        }
    }
    public static void bfs(int[][] board){
        Queue<Node> queue = new LinkedList<>();

        //처음시작부분
        queue.add(new Node(0,0, 0, -1));
        visited[0][0][0] = true;
        visited[0][0][1] = true;
        visited[0][0][2] = true;
        visited[0][0][3] = true;

        while(!queue.isEmpty()){
            Node temp = queue.poll();

            //만약 종점에왔으면
            if(temp.row == totalSize && temp.col == totalSize){
                result = Math.min(result, temp.cost);
                continue;
            }

            for(int i = 0 ; i < 4; i++){
                int nextRow = temp.row + dy[i];
                int nextCol = temp.col + dx[i];

                if(nextRow < 0 || nextRow >= board.length || nextCol < 0 || nextCol >= board.length)
                    continue;
                //벽이면 continue
                if(board[nextRow][nextCol] == 1)
                    continue;

                //직진
                int tempCost;
                if(temp.dir == -1 || temp.dir == i){
                    tempCost = temp.cost + 100;
                }else{
                    tempCost = temp.cost + 600;
                }

                if(visited[nextRow][nextCol][i] == false || board[nextRow][nextCol] >= tempCost){
                    visited[nextRow][nextCol][i] = true;
                    board[nextRow][nextCol] = tempCost;
                    queue.add(new Node(nextRow, nextCol, tempCost, i));
                }
            }
        }
    }
    public static int solution(int[][] board) {
        visited = new boolean[board.length][board.length][4];
        totalSize = board.length - 1;
        bfs(board);
        System.out.println(result);
        return result;
    }
    public static void main(String[] args) {
        //int[][] board = {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}};
        //{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}
        //{0,0,0},{0,0,0},{0,0,0}
        int[][] board = {{0,0,1,0}, {0,0,0,0}, {0,1,0,1}, {1,0,0,0}};
        solution(board);
    }
}

/*
  int min = Integer.MAX_VALUE;
    int[] dx = {0, -1, 0, 1};
    int[] dy = {1, 0, -1, 0};
    int n;
    boolean[][] visited;

    public int solution(int[][] board) {
        n = board.length;
        visited = new boolean[n][n];
        bfs(board);
        return min;
    }

    public void bfs(int[][] board) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0, -1));
        visited[0][0] = true;

        while(!q.isEmpty()) {
            Node node = q.poll();

            if(node.x == n - 1 && node.y == n - 1) {
                min = Math.min(min, node.cost);
            }

            for(int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < n && ny < n && board[nx][ny] != 1) {
                    int newCost;
                    if(node.dir == -1 || node.dir == i) { //직진
                        newCost = node.cost + 100;
                    } else { //코너
                        newCost = node.cost + 600;
                    }

                    //처음 방문하거나 이전에 방문했을 때의 cost보다 작거나 같은 비용이면
                    if(visited[nx][ny] == false || board[nx][ny] >= newCost) {
                        visited[nx][ny] = true;
                        board[nx][ny] = newCost; //값을 갱신해주고
                        q.add(new Node(nx, ny, newCost, i)); //해당 지점으로 이동한다.
                    }
                }
            }
        }
    }

    class Node{
        int x;
        int y;
        int cost;
        int dir;

        public Node(int x, int y, int cost, int dir) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dir = dir;
        }
    }
 */