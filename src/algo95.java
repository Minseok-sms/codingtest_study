import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class algo95 {
    /*
        DFS + DP
        백준 : 1520 내리막길
     */
    static boolean[][] visited;
    static int[][] map;
    static int[][] dp;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int globalCount = 0;

    static class Node{
        int row;
        int col;
        Node(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    public static int dfs(int row, int col, int N, int M){
        if(row == N - 1 && col == M - 1){
            return 1;
        }
        if(dp[row][col] != -1)
            return dp[row][col];

        dp[row][col] = 0;
        for(int i = 0 ; i < 4; i++){
            int nextRow = row + dy[i];
            int nextCol = col + dx[i];


            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
                continue;
            if(visited[nextRow][nextCol] == true)
                continue;

            if(map[nextRow][nextCol] < map[row][col]){
                visited[nextRow][nextCol] = true;
                dp[row][col] += dfs(nextRow, nextCol, N, M);
                visited[nextRow][nextCol] = false;
            }
        }
        return dp[row][col];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        int answer = dfs(0,0,N,M);
        System.out.println(answer);

    }
}

//
//    public static void bfs(int row, int col,int N, int M){
//        Queue<Node> queue = new LinkedList<>();
//        queue.add(new Node(row, col));
//
//        while(!queue.isEmpty()){
//            Node node = queue.poll();
//            row = node.row;
//            col = node.col;
//            if(row == N - 1 && col == M - 1){
//                globalCount++;
//                continue;
//            }
//            for(int i = 0 ; i < 4; i++){
//                int nextRow = row + dy[i];
//                int nextCol = col + dx[i];
//
//                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
//                    continue;
//                if(map[nextRow][nextCol] < map[row][col]){
//                    queue.add(new Node(nextRow, nextCol));
//                }
//
//            }
//        }
//    }
