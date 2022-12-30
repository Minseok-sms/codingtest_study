import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class algo170 {

    /*
    백준 : 16234
    인구 이동

     */
    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int onceProc = 0;
    static int answer = 0;

    static class Node{
        int row;
        int col;
        Node(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    public static void bfs(int row, int col){
        Queue<Node> queue = new LinkedList<>();
        visited[row][col] = true;
        queue.add(new Node(row, col));

        // 지나간 나라좌표 저장.
        ArrayList<Node> array = new ArrayList<>();
        array.add(new Node(row, col));

        int totalSize = 0;
        int total = 0;
        total += map[row][col];
        totalSize++;

        while(!queue.isEmpty()){
            Node temp = queue.poll();

            for(int i = 0 ; i < 4; i++){
                int nextRow = temp.row + dy[i];
                int nextCol = temp.col + dx[i];


                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N)
                    continue;
                if(visited[nextRow][nextCol] == true)
                    continue;

                int cal = Math.abs(map[temp.row][temp.col] - map[nextRow][nextCol]);

                //인구차이가 L미만, R이상이면 continue;
                if(cal < L || cal > R)
                    continue;


                //지나갈수 있으면 나라 저장시키자
                visited[nextRow][nextCol] = true;
                // 갈수있는 나라 총 인구수 저장
                total += map[nextRow][nextCol];
                totalSize++;
                onceProc++;

                queue.add(new Node(nextRow, nextCol));
                array.add(new Node(nextRow, nextCol));
            }
        }
        //만약 지나간 국가가 자기자신뿐이면
        if(totalSize == 1){
            return ;
        }else{
           int pop = (int)Math.floor(total / totalSize);
           for(Node temp : array){
               map[temp.row][temp.col] = pop;
           }
        }




    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i = 0 ; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N ; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        while(true){
            visited = new boolean[N][N];
            onceProc = 0;
            for(int i = 0 ; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(visited[i][j] == true)
                        continue;
                    bfs(i, j);
                }
            }

            if(onceProc != 0)
                answer++;
            else
                break;
        }
        System.out.println(answer);
    }
}
