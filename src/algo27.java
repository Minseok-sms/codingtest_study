import java.util.*;
import java.io.*;

public class algo27 {

    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int N,M;
    static int globalCount = 0;

    static class Node{
        int x;
        int y;
        int count;
        Node(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
//    public static void dfs(int start, int end, int count){
//        visited[start][end] = true;
//        if(start == N - 1 && end == M - 1){
//           if(globalCount > count)
//               globalCount = count;
//           return ;
//        }
//        for(int i = 0 ; i < 4 ; i++){
//            int nextRow = start + dy[i];
//            int nextCol = end + dx[i];
//
//            if(nextRow <= N - 1 && nextRow >= 0 && nextCol <= M - 1  && nextCol >= 0){
//                if(visited[nextRow][nextCol] == false && map[nextRow][nextCol] == 1){
//                    visited[nextRow][nextCol] = true;
//                    dfs(nextRow, nextCol, count + 1);
//                    visited[nextRow][nextCol] = false;
//                }
//            }
//        }
//    }
    public static void bfs(int start, int end, int count){
        Queue<Node> node = new LinkedList<>();
        node.add(new Node(start, end, count));
        visited[start][end] = true;

        while(!node.isEmpty()){
            Node temp = node.poll();
            int row = temp.x;
            int col = temp.y;
            globalCount = temp.count;
            visited[row][col] = true;
            if(row == N - 1 && col == M - 1)
                break;

            for(int i = 0 ; i < 4; i++){
                int nextRow = row + dy[i];
                int nextCol = col + dx[i];
                if(nextRow <= N -1 && nextRow >= 0 && nextCol <= M - 1 && nextCol >= 0){
                    if(visited[nextRow][nextCol] == false && map[nextRow][nextCol] == 1) {
                        visited[nextRow][nextCol] = true;
                        node.add(new Node(nextRow, nextCol, globalCount + 1));
                    }
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
        visited = new boolean[N][M];

//        globalCount = N*M;
        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++)
                map[i][j] = str.charAt(j) - '0';
        }
        //dfs(0,0, 1);
        bfs(0,0, 1);
        System.out.println(globalCount);
    }
}
