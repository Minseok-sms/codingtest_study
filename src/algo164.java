/////////////////////////////////////////////////////////////////////////////////////////////


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.io.FileInputStream;
import java.util.StringTokenizer;



public class algo164
{

    static int N;
    static int cRow = 0;
    static int cCol = 0;
    static int hRow = 0;
    static int hCol = 0;
    static ArrayList<Node> array = new ArrayList<>();
    static boolean[] visited;
    static int MIN = Integer.MAX_VALUE;
    static class Node{
        int row;
        int col;
        Node(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    public static void dfs(int start, int count, int depth, int distance){

        if(count == depth){
            // 자 집다돌았을때.
            // 마지막집 + 집까지 거리 측정더해라
            distance += (int)Math.abs(array.get(start).row - hRow) + (int)Math.abs(array.get(start).col - hCol);
            MIN = Math.min(MIN, distance);
            return ;

        }

        for(int i = 0; i < N; i++){
            int row = array.get(i).row;
            int col = array.get(i).col;
            if(visited[i] == true)
                continue;

            visited[i] = true;
            // 만약 집에서 처음출발해서 어느특정집에 도착햇을시
            if(count == 0){
                dfs(i, count + 1, depth, distance + (int)Math.abs(cRow - row) + (int)Math.abs(cCol - col));
            }else{
                //distance = (int)Math.abs(array.get(start).row - row) + (int)Math.abs(array.get(start).col - col);
                dfs(i, count + 1, depth, distance + (int)Math.abs(array.get(start).row - row) + (int)Math.abs(array.get(start).col - col));
            }
            visited[i] = false;
        }

    }
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int test_case = 1; test_case <= T; test_case++)
        {

            // 고객의 수
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            visited = new boolean[N];
            array = new ArrayList<>();

            cCol = Integer.parseInt(st.nextToken());
            cRow = Integer.parseInt(st.nextToken());

            hCol = Integer.parseInt(st.nextToken());
            hRow = Integer.parseInt(st.nextToken());


            for(int i = 0 ; i < N; i++){
                int col = Integer.parseInt(st.nextToken());
                int row = Integer.parseInt(st.nextToken());
                array.add(new Node(row, col));
            }
            MIN = Integer.MAX_VALUE;
            dfs(0, 0, N, 0);
            sb.append("#" + test_case + " " + MIN +"\n");
        }
        System.out.println(sb);
    }
}

/////////////////////////////////////////////////////////////////////////////////////////////

//
//import java.io.BufferedReader;
//        import java.io.IOException;
//        import java.io.InputStreamReader;
//        import java.util.*;
//        import java.io.FileInputStream;
//        import java.util.StringTokenizer;
//
//
//
//public class Solution
//{
//
//    static int N;
//    static int cRow = 0;
//    static int cCol = 0;
//    static int hRow = 0;
//    static int hCol = 0;
//    static ArrayList<Node> array = new ArrayList<>();
//    static boolean[] visited;
//    static int MIN = Integer.MAX_VALUE;
//    static class Node{
//        int row;
//        int col;
//        Node(int row, int col){
//            this.row = row;
//            this.col = col;
//        }
//    }
//    public static void dfs(int start, int count, int depth, int distance){
//
//        if(count == depth){
//            // 자 집다돌았을때.
//            // 마지막집 + 집까지 거리 측정더해라
//            distance += (int)Math.abs(array.get(start).row - hRow) + (int)Math.abs(array.get(start).col - hCol);
//            MIN = Math.min(MIN, distance);
//            return ;
//
//        }
//
//        for(int i = 0; i < N; i++){
//            int row = array.get(i).row;
//            int col = array.get(i).col;
//            if(visited[i] == true)
//                continue;
//
//            visited[i] = true;
//            // 만약 집에서 처음출발해서 어느특정집에 도착햇을시
//            if(count == 0){
//                dfs(i, count + 1, depth, distance + (int)Math.abs(cRow - row) + (int)Math.abs(cCol - col));
//            }else{
//                //distance = (int)Math.abs(array.get(start).row - row) + (int)Math.abs(array.get(start).col - col);
//                dfs(i, count + 1, depth, distance + (int)Math.abs(array.get(start).row - row) + (int)Math.abs(array.get(start).col - col));
//            }
//            visited[i] = false;
//        }
//
//    }
//    public static void main(String args[]) throws IOException
//    {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int T = Integer.parseInt(br.readLine());
//        StringBuilder sb = new StringBuilder();
//
//        for(int test_case = 1; test_case <= T; test_case++)
//        {
//
//            // 고객의 수
//            N = Integer.parseInt(br.readLine());
//            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//            visited = new boolean[N];
//            array = new ArrayList<>();
//
//            cCol = Integer.parseInt(st.nextToken());
//            cRow = Integer.parseInt(st.nextToken());
//
//            hCol = Integer.parseInt(st.nextToken());
//            hRow = Integer.parseInt(st.nextToken());
//
//
//            for(int i = 0 ; i < N; i++){
//                int col = Integer.parseInt(st.nextToken());
//                int row = Integer.parseInt(st.nextToken());
//                array.add(new Node(row, col));
//            }
//            MIN = Integer.MAX_VALUE;
//            dfs(0, 0, N, 0);
//            sb.append("#" + test_case + " " + MIN +"\n");
//        }
//        System.out.println(sb);
//    }
//}