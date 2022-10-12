//import java.util.LinkedList;
//import java.util.Queue;
//
//public class algo75 {
//
//
//    static boolean[][] visited;
//    static int[] dx = {1,0};
//    static int[] dy = {0,1};
//    static int[][] map;
//    static int globalCount = 0;
//    public static void dfs(int start, int end){
//
//        visited[start][end] = true;
//        if(start == map.length - 1 && end == map[0].length - 1){
//            globalCount++;
//            return ;
//        }
//
//        for(int i = 0 ; i < 2; i++){
//            int nextRow = start + dy[i];
//            int nextCol = end + dx[i];
//
//            if(nextRow >= 0 && nextRow <= map.length - 1 && nextCol >= 0 && nextCol <= map[0].length - 1){
//                if(map[nextRow][nextCol] != 0 && visited[nextRow][nextCol] == false){
//                    visited[nextRow][nextCol] = true;
//                    dfs(nextRow, nextCol);
//                    visited[nextRow][nextCol] = false;
//                }
//            }
//        }
//
//    }
//    static class Node{
//        int row;
//        int col;
//        Node(int row, int col){
//            this.row = row;
//            this.col = col;
//        }
//    }
//    public static void bfs(){
//        Queue<Node> queue = new LinkedList<>();
//        queue.add(new Node(0,0));
//
//        while(!queue.isEmpty()){
//            Node node = queue.poll();
//            int start = node.row;
//            int end = node.col;
//
//            if(start == map.length - 1 && end == map[0].length - 1){
//                globalCount++;
//                continue;
//            }
//
//            for(int i = 0; i < 2; i++){
//                int nextRow = start + dy[i];
//                int nextCol = end + dx[i];
//                if(0 <= nextRow && nextRow <= map.length - 1 && 0 <= nextCol && nextCol <= map[0].length - 1){
//                    if(map[nextRow][nextCol] != 0){
//                        queue.add(new Node(nextRow, nextCol));
//                    }
//                }
//            }
//        }
//    }
//
//    public static int solution(int m, int n, int[][] puddles) {
//        int answer = 0;
//        // m = 4, n = 3
//        visited = new boolean[n][m];
//        map = new int[n][m];
//        for(int i = 0 ; i < n; i++){
//            for(int j = 0 ; j < m; j++){
//                map[i][j] = 1;
//            }
//        }
//        for(int i = 0; i < puddles.length; i++)
//            map[puddles[i][1] - 1][puddles[i][0] - 1] = 0;
//
//        //dfs(0,0);
//        bfs();
//        answer = globalCount % 1000000007;
//        System.out.println(answer);
//        return answer;
//    }
//    public static void main(String[] args) {
//        int[][] puddles = {{2,2}};
//        solution(4,3,puddles);
//
//    }
//}
import java.util.LinkedList;
import java.util.Queue;

public class algo75 {

    /*
    프로그래머스 : 등굣길 dp
     */

    static boolean[][] visited;
    static int[] dx = {1,0};
    static int[] dy = {0,1};
    static int[][] map;
    static int globalCount = 0;

    public static int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        map = new int[n][m];


        for(int i = 0; i < puddles.length; i++)
            map[puddles[i][1] - 1][puddles[i][0] - 1] = -1;

        map[0][0] = 1;
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < m; j++){
                // map이 웅덩이이면 0으로 바꿈
                if(map[i][j] == -1) {
                    map[i][j] = 0;
                    continue;
                }

                // 우측으로 이동
                if(i != 0)
                    map[i][j] += map[i - 1][j] % 1000000007;
                // 아래로 이동
                if(j != 0)
                    map[i][j] += map[i][j - 1] % 1000000007;
            }
        }
        answer = map[n - 1][m - 1] % 1000000007;
        System.out.println(answer);
        return answer;
    }
    public static void main(String[] args) {
        int[][] puddles = {{2,2}};
        solution(4,3,puddles);

    }
}
