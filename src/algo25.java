import java.io.*;
import java.util.*;
/*
    dfs 1520번
 */
public class algo25 {


    static int[][] map;
    static int[][] dp;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int N,M;
    static int globalCount = 0;
    public static void dfs(int first, int last, int value){

        visited[first][last] = true;
        if(first == N - 1 && last == M - 1){
            globalCount++;
            return ;
        }
        if(map[first][last] < map[N-1][M-1])
            return;

        for(int i = 0; i < 4; i++){
            int nextRow = first + dy[i];
            int nextCol = last + dx[i];

            if(nextRow >= 0 && nextRow <= map.length - 1 && nextCol >= 0 && nextCol <= map[0].length - 1){
                int nextValue = map[nextRow][nextCol];
                if(nextValue < value && visited[nextRow][nextCol] == false){
                    visited[nextRow][nextCol] = true;
                    dfs(nextRow, nextCol,nextValue);
                    visited[nextRow][nextCol] = false;
                    dp[first][last] += dp[nextRow][nextCol];
                }
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        dp =  new int[N][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M ; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                dp[i][j] = -1;
            }
        }

        dp[N-1][M-1] = 1;

        if(map[0][0] < map[N-1][M-1])
            System.out.println("0");
        else{
            dfs(0,0,map[0][0]);
            System.out.println(globalCount);
        }
        System.out.println(dp[0][0]);

    }

}
//
//
//import java.io.*;
//        import java.util.*;
///*
//    dfs 1520번
// */
//public class algo25 {
//
//
//    static int[][] map;
//    static int[][] dp;
//    static boolean[][] visited;
//    static int[] dx = {-1, 0, 1, 0};
//    static int[] dy = {0, -1, 0, 1};
//    static int N,M;
//    static int globalCount = 0;
//    public static void dfs(int first, int last, int value){
//
//        visited[first][last] = true;
//        if(first == N - 1 && last == M - 1){
//            globalCount++;
//            return ;
//        }
//        if(map[first][last] < map[N-1][M-1])
//            return;
//
//        for(int i = 0; i < 4; i++){
//            int nextRow = first + dy[i];
//            int nextCol = last + dx[i];
//
//            if(nextRow >= 0 && nextRow <= map.length - 1 && nextCol >= 0 && nextCol <= map[0].length - 1){
//                int nextValue = map[nextRow][nextCol];
//                if(nextValue < value && visited[nextRow][nextCol] == false){
//                    visited[nextRow][nextCol] = true;
//                    dfs(nextRow, nextCol,nextValue);
//                    visited[nextRow][nextCol] = false;
//                }
//            }
//        }
//    }
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        map = new int[N][M];
//        visited = new boolean[N][M];
//        dp =  new int[N][M];
//
//        for(int i = 0 ; i < N ; i++){
//            st = new StringTokenizer(br.readLine(), " ");
//            for(int j = 0; j < M ; j++)
//                map[i][j] = Integer.parseInt(st.nextToken());
//        }
//        for(int i = 0; i < N; i++){
//            for(int j = 0; j < M; j++){
//                dp[i][j] = -1;
//            }
//        }
//
//        dp[N-1][M-1] = 1;
//
//        if(map[0][0] < map[N-1][M-1])
//            System.out.println("0");
//        else{
//            dfs(0,0,map[0][0]);
//            System.out.println(globalCount);
//        }
//
//
//    }
//
//}
//
//
