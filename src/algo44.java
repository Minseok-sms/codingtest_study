import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class algo44 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0 , -1, 0, 1};
    static int globalCount = 0;
    static int globalRainbow = 0;
    static class Node {
        int x;
        int y;
        int blockSize;
        int blockRain;
        Node(int x, int y, int blockSize, int blockRain){
            this.x = x;
            this.y = y;
            this.blockSize = blockSize;
            this.blockRain = blockRain;
        }
    }
    public static void dfs(int start, int end, int value, int count){

        visited[start][end] = true;
        if(globalCount < count){
            globalCount = count;
        }
        for(int i = 0 ; i < 4; i++){
            int nextRow = start + dy[i];
            int nextCol = end + dx[i];
            if(0 <= nextRow && nextRow <= N - 1 && 0 <= nextCol && nextCol <= N - 1){
                if(visited[nextRow][nextCol] == false && map[nextRow][nextCol] == value || map[nextRow][nextCol] == 0){
                    visited[nextRow][nextCol] = true;
                    if(map[nextRow][nextCol] == 0)
                        globalRainbow++;
                    dfs(nextRow, nextCol, value, count + 1);
                }
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N][N];
        map = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N ; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Node> array = new ArrayList<>();
        while(true) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0 || map[i][j] == -1)
                        continue;
                    dfs(i, j, map[i][j], 1);
                    if(globalCount >= 2){
                        array.add(new Node(i,j, globalCount, globalRainbow));
                    }
                    globalCount = 0;
                    globalRainbow = 0;
                }
            }
            //Node temp = new Node(0,0,0,0);
            int tempSize = 0;
            int sameBlockSize = 0;
            for(int i = 0; i < array.size(); i++){
                Node copy = array.get(i);
                if(tempSize < copy.blockSize){

                }

            }



        }


    }
}
