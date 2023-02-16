import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class algo183 {
    static int N, M;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int MIN = Integer.MAX_VALUE;
    static class Node{
        int row;
        int col;
        int cnt;
        int key;
        Node(int row,int col, int cnt, int key){
            this.row = row;
            this.col = col;
            this.cnt = cnt;
            this.key = key;
        }
    }
    public static void bfs(int row, int col){
        Queue<Node> queue = new LinkedList<>();
        Node start = new Node(row, col, 0, 0);
        queue.add(start);
        visited[start.key][start.row][start.col] = true;

        while(!queue.isEmpty()){
            Node temp = queue.poll();
            if(map[temp.row][temp.col] == '1'){
                MIN = Math.min(MIN, temp.cnt);
                continue;
            }
            for(int i = 0 ; i < 4;i++){
                int nextRow = temp.row + dy[i];
                int nextCol = temp.col + dx[i];

                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
                    continue;

                if(visited[temp.key][nextRow][nextCol] == true ||
                   map[nextRow][nextCol] == '#')
                    continue;

                // 만약 방문하려는 곳이 . 이다
                if(map[nextRow][nextCol] == '.' || map[nextRow][nextCol] == '0' || map[nextRow][nextCol] == '1') {
                    queue.add(new Node(nextRow, nextCol, temp.cnt + 1, temp.key));
                    visited[temp.key][nextRow][nextCol] = true;
                }
                // 만약방문하려는 곳이 소문자 a b c d e f이다?
                else if(0 <= map[nextRow][nextCol] - 'a' &&  map[nextRow][nextCol] - 'a' <= 5){
                    int key = temp.key | (1 << map[nextRow][nextCol] - 'a'); // 만약 a이다? // 0 0 0 0 0 1
                    visited[temp.key][nextRow][nextCol] = true;
                    visited[key][nextRow][nextCol] = true;
                    queue.add(new Node(nextRow, nextCol, temp.cnt + 1, key));
                }else{
                    // 방문하려는 곳이 대문자이다.
                    int check = temp.key & (1 << map[nextRow][nextCol] - 'A');

                    // key =     0 0 0 1 1 1
                    // nextkey = 0 0 0 0 0 1
                    if(check > 0){
                        // 해당키가 존재한다.
                        visited[temp.key][nextRow][nextCol] = true;
                        visited[1 << map[nextRow][nextCol] - 'A'][nextRow][nextCol] = true;
                        queue.add(new Node(nextRow, nextCol, temp.cnt + 1, temp.key));
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
        int startRow = 0, startCol = 0;
        map = new char[N][M];
        for(int i = 0 ; i < N; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == '0') {
                    startRow = i;
                    startCol = j;
                }
            }
        }
        visited = new boolean[64][N][M];
        bfs(startRow, startCol);
        if(MIN == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(MIN);

    }
}
