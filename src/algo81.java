//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class algo81 {
//
//    static boolean[][][] visited = new boolean[5][5][2];
//    static int[][] map = new int[5][5];
//    static int[] dx = {1, 0, -1, 0};
//    static int[] dy = {0, 1, 0, -1};
//    static int globalCount = 0;
//    public static void dfs(int row, int col, int sCount, int depth){
//
//        visited[row][col] = true;
//        // 만약 7명중 이다솜파가 4명이상이면 ++,
//        if(depth == 7){
//            if(sCount >= 4){
//                globalCount++;
//            }
//            return ;
//        }
//        for(int i = 0 ; i < 4; i++){
//            int nextCol = dx[i] + col;
//            int nextRow = dy[i] + row;
//
//            if(nextCol < 0 || nextCol > 4 || nextRow < 0 || nextRow > 4)
//                 continue;
//
//
//            visited[nextRow][nextCol][0] = true;
//
//            if(map[nextRow][nextCol] == 1 )
//                dfs(nextRow, nextCol, sCount + 1, depth + 1);
//            else
//                dfs(nextRow, nextCol, sCount, depth + 1);
//            visited[nextRow][nextCol] = false;
//        }
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        int row = 0, col = 0;
//        boolean firstCheck = false;
//        for(int i = 0 ; i < 5; i++){
//            String str = br.readLine();
//            for(int j = 0 ; j < 5; j++){
//                // Y파 = 0
//                if(str.charAt(j) == 'Y')
//                    map[i][j] = 0;
//                // S파 = 1
//                else {
//                    if(firstCheck == false) {
//                        firstCheck = true;
//                        row = i;
//                        col = j;
//                    }
//                    map[i][j] = 1;
//                }
//            }
//        }
//
//        dfs(row, col, 1, 1);
//        System.out.println(globalCount);
//    }
//}
// 5 6 7 8 9 11 16
// cur = 5
// 5 (1,0)
//
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class algo81 {

    static boolean[][] visited = new boolean[5][5];
    static int[][] map = new int[5][5];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int globalCount = 0;
    public static void dfs(int row, int col, int sCount, int depth){

        visited[row][col] = true;
        // 만약 7명중 이다솜파가 4명이상이면 ++,
        if(depth == 7){
            if(sCount >= 4){
                globalCount++;
            }
            return ;
        }
        for(int i = 0 ; i < 4; i++){
            int nextCol = dx[i] + col;
            int nextRow = dy[i] + row;

            if(nextCol < 0 || nextCol > 4 || nextRow < 0 || nextRow > 4)
                 continue;

            if(visited[nextRow][nextCol] == true)
                continue;


            visited[nextRow][nextCol] = true;
            if(map[nextRow][nextCol] == 1)
                dfs(nextRow, nextCol, sCount + 1, depth + 1);
            else
                dfs(nextRow, nextCol, sCount, depth + 1);
            visited[nextRow][nextCol] = false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int row = 0, col = 0;
        boolean firstCheck = false;
        for(int i = 0 ; i < 5; i++){
            String str = br.readLine();
            for(int j = 0 ; j < 5; j++){
                // Y파 = 0
                if(str.charAt(j) == 'Y')
                    map[i][j] = 0;
                // S파 = 1
                else {
                    if(firstCheck == false) {
                        firstCheck = true;
                        row = i;
                        col = j;
                    }
                    map[i][j] = 1;
                }
            }
        }

        dfs(row, col, 1, 1);
        System.out.println(globalCount);
    }
}

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class algo81 {
    /*
    백준 1941 (소문난 칠공주)
     */

    static boolean[] visited;
    static boolean[] adjVisited;
    static int[][] map = new int[5][5];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int globalCount = 0;
    static int[] selected;
    static int N;

    public static boolean checkAdj(){
        int cnt = 1;
        adjVisited = new boolean[N*N];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(selected[0]);
        // 5, 6, 7 8, 9, 11, 16

        while(!queue.isEmpty()){
            int temp = queue.poll();
            adjVisited[temp] = true;

            // 5 (1,0)
            for(int i = 0 ; i < 4; i++){
                // 1,1
                // 2,1
                int nextCol = dx[i] + (temp % N);
                int nextRow = dy[i] + (temp / N);

                if(nextCol < 0 || nextCol > 4 || nextRow < 0 || nextRow > 4)
                    continue;

                if(adjVisited[N * nextRow + nextCol] == true)
                    continue;
                if(visited[N * nextRow + nextCol] == false)
                    continue;

                adjVisited[N * nextRow + nextCol] = true;
                cnt++;
                queue.add(N * nextRow + nextCol);
            }
        }

        if(cnt == 7)
            return true;
        else
            return false;
    }
    public static void dfs(int start,int depth,int sCount){

        if(depth == 7){
            if(sCount >= 4){
               if(checkAdj())
                   globalCount++;
            }
            return ;
        }
        for(int i = start; i < N * N; i++ ){

            if(visited[i] == true)
                continue;

            visited[i] = true;
            selected[depth] = i;
            if(map[i / N][i % N] == 1)
                dfs(i + 1, depth + 1,sCount + 1);
            else
                dfs(i + 1, depth + 1, sCount);

            visited[i] = false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0 ; i < 5; i++){
            String str = br.readLine();
            for(int j = 0 ; j < 5; j++){
                // Y파 = 0
                if(str.charAt(j) == 'Y')
                    map[i][j] = 0;
                    // S파 = 1
                else {
                    map[i][j] = 1;
                }
            }
        }
        N = 5;
        visited = new boolean[N * N];
        selected = new int[7];

        dfs(0,0, 0);
        System.out.println(globalCount);
    }
}