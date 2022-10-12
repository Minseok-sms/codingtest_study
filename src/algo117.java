import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class algo117 {
    /*
    백준 : 7569
    토마토
     */
    static int N,M,H;
    static int[][][] map;
    static int[] dx = {-1, 0, 1, 0, 0, 0};
    static int[] dy = {0, -1, 0 ,1, 0, 0};
    static int[] dz = {0,  0, 0, 0, 1, -1};
    static Queue<Node> queue = new LinkedList<>();

    static class Node{
        int row;
        int col;
        int height;
        Node(int row, int col, int height){
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }
    public static int bfs(){
        while(!queue.isEmpty()){
            Node node = queue.poll();
            int row = node.row;
            int col = node.col;
            int height = node.height;

            for(int i = 0; i < 6; i++){
                    int nextRow = row + dy[i];
                    int nextCol = col + dx[i];
                    int nextHeight = height + dz[i];

                    if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M
                    || nextHeight < 0 || nextHeight >= H){
                        continue;
                    }
                    if(map[nextRow][nextCol][nextHeight] == 0) {
                        queue.add(new Node(nextRow, nextCol, nextHeight));
                        map[nextRow][nextCol][nextHeight] = map[row][col][height] + 1;
                    }
            }
        }
        int max = 0;

        for(int i = 0 ; i < H; i++){
            for(int j = 0 ; j < N; j++){
                for(int k = 0 ; k < M; k++){
                    // 만약 다 익힌걸 거쳣는데 0이존재하면 익힐수없다
                    if(map[j][k][i] == 0)
                        return -1;
                    max = Math.max(max, map[j][k][i]);
                }
            }
        }

        return max - 1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(),  " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[N][M][H];

        boolean check = false;
        for(int i = 0 ; i < H; i++){
            for(int j = 0 ; j < N; j++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int k = 0 ; k < M; k++){
                    map[j][k][i] = Integer.parseInt(st.nextToken());
                    if(map[j][k][i] == 0 && check == false)
                        check = true;

                    // 익은토마토면 큐에넣어라.
                    if(map[j][k][i] == 1)
                        queue.add(new Node(j,k,i));
                }
            }
        }
        // 토마토가 싹다 익어있는 상태이다.
        if(check == false){
            System.out.println(0);
            System.exit(0);
        }
        System.out.println(bfs());
    }
}
