import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class algo40 {
    static boolean[][] visited;
    static int[][] map;
    static int N,M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int globalCount;
    static boolean check;

    static class Node{
        int x;
        int y;
        int one;
        int answer;
        Node(int y, int x, int one,int answer){
            this.x = x;
            this.y = y;
            this.one = one;
            this.answer = answer;

        }
    }
    public static void bfs(int start, int end){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, end,0, 1));
        //visited[start][end] = true;
        boolean[][][] visited2 = new boolean[N][M][2];
        while(!queue.isEmpty()){
            Node temp = queue.poll();
            int row = temp.y;
            int col = temp.x;
            int oneCount = temp.one;
            int answer = temp.answer;


            if(row == N - 1 && col == M - 1) {
                check = true;
                if(globalCount > answer)
                    globalCount = answer;
                continue;
            }


            for(int i = 0 ; i < 4; i++){
                 int nextRow = row + dy[i];
                 int nextCol = col + dx[i];
                 if(0 <= nextRow && nextRow <= N - 1 && 0 <= nextCol && nextCol <= M - 1){
                     if(map[nextRow][nextCol] == 0){
                        if(oneCount == 0 && visited2[nextRow][nextCol][0] == false){
                            visited2[nextRow][nextCol][0] = true;
                            queue.add(new Node(nextRow, nextCol, oneCount, answer + 1));
                        }else if(oneCount == 1 && visited2[nextRow][nextCol][1] == false){
                            visited2[nextRow][nextCol][1] = true;
                            queue.add(new Node(nextRow, nextCol, oneCount, answer + 1));
                        }
                     }
                     else{
                         if(oneCount == 0){
                             visited2[nextRow][nextCol][1] = true;
                             queue.add(new Node(nextRow, nextCol, oneCount + 1, answer + 1));
                         }
                     }
                 }
            }
        }


    }
    public static void dfs(int start, int end, int count, int answer){
        visited[start][end] = true;
        if(start == N - 1 && end == M - 1 && count <= 1){
            check = true;
            if(globalCount > answer)
                 globalCount = answer;
        }

        for(int i = 0; i < 4; i++){
            int nextRow = start + dy[i];
            int nextCol = end + dx[i];

            if(0 <= nextRow && nextRow <= N - 1 && 0 <= nextCol && nextCol <= M - 1){
                if(visited[nextRow][nextCol] == false){
                    if(map[nextRow][nextCol] == 1){
                        visited[nextRow][nextCol] = true;
                        dfs(nextRow, nextCol, count + 1, answer + 1);
                        visited[nextRow][nextCol] = false;
                    }else{
                        visited[nextRow][nextCol] = true;
                        dfs(nextRow, nextCol, count, answer + 1);
                        visited[nextRow][nextCol] = false;
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

        visited = new boolean[N][M];
        map = new int[N][M];
        globalCount = N*M;
        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M; j++)
                map[i][j] = str.charAt(j) - '0';
        }

       // dfs(0,0, 0,1);
        bfs(0,0);
        if(check == true)
            System.out.println(globalCount);
        else
            System.out.println(-1);
    }
}

/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class algo40 {
    static boolean[][] visited;
    static int[][] map;
    static int N,M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int globalCount;
    static boolean check;

    static class Node{
        int x;
        int y;
        int one;
        int answer;
        Node(int y, int x, int one,int answer){
            this.x = x;
            this.y = y;
            this.one = one;
            this.answer = answer;

        }
    }
    public static void bfs(int start, int end){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, end,0, 1));
        visited[start][end] = true;

        while(!queue.isEmpty()){
            Node temp = queue.poll();
            int row = temp.y;
            int col = temp.x;
            int oneCount = temp.one;
            int answer = temp.answer;
            visited[row][col] = true;

            if(row == N - 1 && col == M - 1) {
                check = true;
                if(globalCount > answer)
                    globalCount = answer;
                continue;
            }


            for(int i = 0 ; i < 4; i++){
                 int nextRow = row + dy[i];
                 int nextCol = col + dx[i];
                 if(0 <= nextRow && nextRow <= N - 1 && 0 <= nextCol && nextCol <= M - 1){
                     if(visited[nextRow][nextCol] == false){
                        if(map[nextRow][nextCol] == 1 && oneCount == 0){
                            visited[nextRow][nextCol] = true;
                            queue.add(new Node(nextRow, nextCol, oneCount + 1, answer + 1));
                        }else if(map[nextRow][nextCol] == 0) {
                            visited[nextRow][nextCol] = true;
                            queue.add(new Node(nextRow, nextCol, oneCount, answer + 1));
                        }
                     }
                 }
            }
        }


    }
    public static void dfs(int start, int end, int count, int answer){
        visited[start][end] = true;
        if(start == N - 1 && end == M - 1 && count <= 1){
            check = true;
            if(globalCount > answer)
                 globalCount = answer;
        }

        for(int i = 0; i < 4; i++){
            int nextRow = start + dy[i];
            int nextCol = end + dx[i];

            if(0 <= nextRow && nextRow <= N - 1 && 0 <= nextCol && nextCol <= M - 1){
                if(visited[nextRow][nextCol] == false){
                    if(map[nextRow][nextCol] == 1){
                        visited[nextRow][nextCol] = true;
                        dfs(nextRow, nextCol, count + 1, answer + 1);
                        visited[nextRow][nextCol] = false;
                    }else{
                        visited[nextRow][nextCol] = true;
                        dfs(nextRow, nextCol, count, answer + 1);
                        visited[nextRow][nextCol] = false;
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

        visited = new boolean[N][M];
        map = new int[N][M];
        globalCount = N*M;
        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M; j++)
                map[i][j] = str.charAt(j) - '0';
        }

       // dfs(0,0, 0,1);
        bfs(0,0);
        if(check == true)
            System.out.println(globalCount);
        else
            System.out.println(-1);
    }
}

 */