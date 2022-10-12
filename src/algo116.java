import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class algo116 {
    /*
    백준 : 7576
    토마토
     */
    static int N, M;
    static int[][] map;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static Queue<Node> queue = new LinkedList<>();

    static class Node{
        int row;
        int col;

        Node(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    public static int bfs(){

        while(!queue.isEmpty()){
            Node node = queue.poll();
            int row = node.row;
            int col = node.col;

            // 기준점으로 주변에 익지않으면 익혀버리고, queue에 추가
            for(int i = 0 ; i < 4; i++){
                int nextRow = row + dy[i];
                int nextCol = col + dx[i];

                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
                    continue;

                if(map[nextRow][nextCol] == 0){
                    // 토마토가 안익었으면 토마토 추가
                    queue.add(new Node(nextRow, nextCol));

                    // 그전의 값에서 날짜 + 1을 해줌
                    map[nextRow][nextCol] = map[row][col] + 1;
                }
            }
        }

        // 최대 날짜가 무엇?
        int maxDate = 0;

        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                // 만약 안익은토마토가 한개라도 잇다
                if(map[i][j] == 0)
                    return -1;
                maxDate = Math.max(maxDate, map[i][j]);
            }
        }
        return  maxDate - 1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        boolean notZeroCount = false;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 0 && notZeroCount == false)
                    notZeroCount = true;
                // 만약 익은토마토면 queue에 삽입
                if(map[i][j] == 1)
                    queue.add(new Node(i, j));
            }
        }

        if(notZeroCount == false)
            System.out.println(0);
        else
            System.out.println(bfs());
    }
}
//import java.io.BufferedReader;
//        import java.io.IOException;
//        import java.io.InputStreamReader;
//        import java.util.LinkedList;
//        import java.util.PriorityQueue;
//        import java.util.Queue;
//        import java.util.StringTokenizer;
//
//public class algo116 {
//    /*
//    백준 : 7576
//    토마토
//     */
//    static int N, M;
//    static int[][] map;
//    static boolean[][] visited;
//    static int[] dx = {-1, 0, 1, 0};
//    static int[] dy = {0, -1, 0, 1};
//    static boolean cCheck = false;
//
//    public static void dfs(int row, int col){
//
//        visited[row][col] = true;
//
//        // 기준점으로 주변에 익지않으면 익혀버리고, visited = true로바꾸자
//        for(int i = 0 ; i < 4; i++){
//            int nextRow = row + dy[i];
//            int nextCol = col + dx[i];
//
//            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
//                continue;
//
//            if(map[nextRow][nextCol] == 0 && visited[nextRow][nextCol] == false){
//                cCheck = true;
//                map[nextRow][nextCol] = 1;
//                visited[nextRow][nextCol] = true;
//            }
//        }
//        //기준점에서 또 익은 토마토로 건너가자
//        for(int i = 0 ; i < 4; i++){
//            int nextRow = row + dy[i];
//            int nextCol = col + dx[i];
//
//            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
//                continue;
//
//            if(map[nextRow][nextCol] == 1 && visited[nextRow][nextCol] == false){
//
//                visited[nextRow][nextCol] = true;
//                dfs(nextRow, nextCol);
//            }
//        }
//
//    }
//    static class Node{
//        int row;
//        int col;
//
//        Node(int row, int col){
//            this.row = row;
//            this.col = col;
//        }
//    }
//    public static void bfs(int start, int end){
//
//        visited[start][end] = true;
//        Queue<Node> queue = new LinkedList<>();
//        queue.offer(new Node(start, end));
//
//        while(!queue.isEmpty()){
//            Node node = queue.poll();
//            int row = node.row;
//            int col = node.col;
//
//            // 기준점으로 주변에 익지않으면 익혀버리고, visited = true로바꾸자
//            for(int i = 0 ; i < 4; i++){
//                int nextRow = row + dy[i];
//                int nextCol = col + dx[i];
//
//                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
//                    continue;
//
//                if(map[nextRow][nextCol] == 0 && visited[nextRow][nextCol] == false){
//                    cCheck = true;
//                    map[nextRow][nextCol] = 1;
//                    visited[nextRow][nextCol] = true;
//                }
//            }
//
//            //기준점에서 또 익은 토마토로 건너가자
//            for(int i = 0 ; i < 4; i++){
//                int nextRow = row + dy[i];
//                int nextCol = col + dx[i];
//
//                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
//                    continue;
//
//                if(map[nextRow][nextCol] == 1 && visited[nextRow][nextCol] == false){
//
//                    visited[nextRow][nextCol] = true;
//                    queue.offer(new Node(nextRow, nextCol));
//                }
//            }
//
//
//        }
//
//
//
//
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        M = Integer.parseInt(st.nextToken());
//        N = Integer.parseInt(st.nextToken());
//
//        map = new int[N][M];
//        boolean notZeroCount = false;
//        for(int i = 0; i < N; i++){
//            st = new StringTokenizer(br.readLine(), " ");
//            for(int j = 0 ; j < M ; j++) {
//                map[i][j] = Integer.parseInt(st.nextToken());
//                if(map[i][j] == 0 && notZeroCount == false)
//                    notZeroCount = true;
//            }
//        }
//        if(notZeroCount == false){
//            System.out.println(0);
//            System.exit(0);
//        }
//
//        int dateCount = 0;
//        while(true) {
//
//            cCheck = false;
//            visited = new boolean[N][M];
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < M; j++) {
//                    if (map[i][j] == 1 && visited[i][j] == false){
//                        bfs(i,j);
//                    }
//                }
//            }
//            //익힌 토마토가 하나라도 있으면 날짜++ 하고 다시시작
//            if(cCheck == true) {
//                dateCount++;
//                continue;
//            }else{
//                // 익힌 토마토가 하나도없을때 만약 싹다 익혀졋으면 dateCount 출력
//                // 더이상 익힐수 없는 -1 로 둘러쌓여잇어서 0이존재하면 -1출력하고 종료
//                for(int i = 0 ; i < N; i++){
//                    for(int j = 0 ; j < M; j++){
//                        if(map[i][j] == 0){
//                            System.out.println(-1);
//                            System.exit(0);
//                        }
//                    }
//                }
//                break;
//            }
//        }
//
//        System.out.println(dateCount);
//    }
//}
