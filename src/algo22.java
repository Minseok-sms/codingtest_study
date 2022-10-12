import java.io.*;
import java.util.*;

public class algo22 {

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static boolean[][] visited;
    static int globalCount = 0;
    static ArrayList<Integer> array = new ArrayList<>();
    public static void dfs(int[][] map, int row, int col){

        if(visited[row][col] == false && map[row][col] == 1){
            visited[row][col] = true;
            globalCount++;
        }

        for(int i = 0; i < 4 ; i++){
            int x = col + dx[i];
            int y = row + dy[i];


            if(x < map[0].length && x >= 0 && y < map.length && y >= 0){
                if(map[y][x] == 1 && visited[y][x] == false) {
                    globalCount++;
                    visited[y][x] = true;
                    dfs(map, y, x);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());


        for(int k = 0; k < testCase; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int answer = 0;

            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[][] map = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    map[i][j] = 0;
                }
            }



            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int col1 = Integer.parseInt(st.nextToken());
                int row1 = Integer.parseInt(st.nextToken());
                map[row1][col1] = 1;
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    System.out.print( map[i][j]);
                }
                System.out.println();
            }

                                // 8 10
            visited = new boolean[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    dfs(map, i, j);
                    if (globalCount != 0)
                        answer++;
                    globalCount = 0;
                }
            }
            array.add(answer);
        }
        for(int i = 0; i < array.size(); i++){
            if(i == array.size() - 1) {
                System.out.print(array.get(i));
            }else
                 System.out.println(array.get(i));
        }
    }
}
