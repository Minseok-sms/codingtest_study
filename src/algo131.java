//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
//public class algo131 {
//    /*
//    백준 : 16946
//    벽부수고 이동하기 4
//     */
//    static int[] dx = {-1, 0, 1, 0};
//    static int[] dy = {0, 1, 0, -1};
//    static boolean[][] visited;
//    static int N,M;
//    static int[][] map;
//    static int[][] answerMap;
//
//    static class Node{
//
//        int y;
//        int x;
//        Node(int y, int x){
//            this.y = y;
//            this.x = x;
//        }
//    }
//    static ArrayList<Node> co = new ArrayList<>();
//    public static int bfs(int row, int col){
//
//        visited[row][col] = true;
//        Queue<Node> queue = new LinkedList<>();
//        queue.add(new Node(row, col));
//        int count = 1;
//
//        while(!queue.isEmpty()){
//            Node node = queue.poll();
//            row = node.y;
//            col = node.x;
//
//            for(int i = 0 ; i < 4; i++){
//                int nextRow = row + dy[i];
//                int nextCol = col + dx[i];
//
//                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
//                    continue;
//
//                if(visited[nextRow][nextCol] == true || map[nextRow][nextCol] == 1)
//                    continue;
//
//                queue.add(new Node(nextRow, nextCol));
//                visited[nextRow][nextCol] = true;
//                count++;
//            }
//        }
//        return count;
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        map = new int[N][M];
//        answerMap = new int[N][M];
//
//        for(int i = 0 ; i < N; i++){
//            String str = br.readLine();
//            for(int j = 0 ; j < M; j++) {
//                map[i][j] = str.charAt(j) - '0';
//                if(map[i][j] == 1)
//                    co.add(new Node(i, j));
//            }
//        }
//
//        for(int i = 0 ; i < co.size(); i++){
//            int y = co.get(i).y;
//            int x = co.get(i).x;
//            map[y][x] = 0;
//            visited = new boolean[N][M];
//            answerMap[y][x] = bfs(y,x) % 10;
//            map[y][x] = 1;
//        }
//        for(int i = 0 ; i < N; i++){
//            for(int j = 0 ; j < M; j++) {
//                System.out.print(answerMap[i][j]);
//            }
//            System.out.println();
//        }
//
//
//
//    }
//}


import java.io.*;
import java.util.*;

public class algo131 {
    /*
    백준 : 16946
    벽부수고 이동하기 4
     */
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;
    static int N,M;
    static int[][] map;
    static int[][] answerMap;
    static HashMap<Integer, Integer> group = new HashMap<>();

    static class Node{

        int y;
        int x;
        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static int bfs(int row, int col, int idx){

        visited[row][col] = true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(row, col));
        map[row][col] = idx;
        int count = 1;

        while(!queue.isEmpty()){
            Node node = queue.poll();
            row = node.y;
            col = node.x;

            for(int i = 0 ; i < 4; i++){
                int nextRow = row + dy[i];
                int nextCol = col + dx[i];

                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
                    continue;

                if(visited[nextRow][nextCol] == true || map[nextRow][nextCol] == 1)
                    continue;

                queue.add(new Node(nextRow, nextCol));
                map[nextRow][nextCol] = idx;
                visited[nextRow][nextCol] = true;
                count++;
            }
        }

        group.put(idx, count);
        return count;
    }
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        answerMap = new int[N][M];

        for(int i = 0 ; i < N; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        int index = 10;
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            for(int j = 0 ; j < M; j++){
                if(map[i][j] == 0 && visited[i][j] == false)
                    bfs(i, j, index++);
            }
        }

        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                if(map[i][j] == 1){
                    HashMap<Integer, Integer> temp = new HashMap<>();

                    int sum = 1;
                    for(int k = 0; k < 4; k++){
                        int nextRow = i + dy[k];
                        int nextCol = j + dx[k];

                        if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
                            continue;

                        if(map[nextRow][nextCol] == 1)
                            continue;

                        if(!temp.containsKey(map[nextRow][nextCol])){
                            temp.put(map[nextRow][nextCol], 1);
                            sum += group.get(map[nextRow][nextCol]);
                        }

                    }
                    bw.write((sum%10) + "");

                }else{

                    bw.write("0");
                }
            }
            bw.write("\n");
        }

        bw.flush();
    }
}

