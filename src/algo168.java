import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class algo168 {

    /*
    백준 : 2589
    보물섬
     */

    static String[][] map;
    static int N,M;
    static ArrayList<Node> array = new ArrayList<>();
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int globalMax = 0;
    static class Node{
        int row;
        int col;
        Node(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    static class Node2{
        int row;
        int col;
        int count;
        Node2(int row, int col, int count){
            this.row = row;
            this.col = col;
            this.count = count;
        }
    }

    public static void bfs(int row, int col){

        Queue<Node2> queue = new LinkedList<>();
        queue.add(new Node2(row, col, 0));
        visited[row][col] = true;

        while(!queue.isEmpty()){
            Node2 temp = queue.poll();


            for(int i = 0; i < 4; i++){
                int nextRow = temp.row + dy[i];
                int nextCol = temp.col + dx[i];
                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M )
                    continue;
                if(map[nextRow][nextCol].equals("W") || visited[nextRow][nextCol] == true)
                    continue;

                visited[nextRow][nextCol] = true;
                queue.add(new Node2(nextRow, nextCol, temp.count + 1));
                globalMax = Math.max(globalMax, temp.count + 1);
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new String[N][M];

        for(int i = 0 ; i < N; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M; j++) {
                map[i][j] = Character.toString(str.charAt(j));
                if(str.charAt(j) == 'L')
                    array.add(new Node(i, j));
            }
        }
        for(int i = 0; i < array.size(); i++){
            int row = array.get(i).row;
            int col = array.get(i).col;
            visited = new boolean[N][M];
            bfs(row, col);
        }
        System.out.println(globalMax);

    }
}
