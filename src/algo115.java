import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class algo115 {

    /*
    백준 : 2636
    치즈
     */
    static int[][] array;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int N,M;
    static ArrayList<Integer> answer = new ArrayList<>();

    public static void airChange(int row, int col){
        visited[row][col] = true;
        array[row][col] = 2;


        for(int i = 0 ; i < 4; i++){
            int nextRow = row + dy[i];
            int nextCol = col + dx[i];

            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
                continue;

            if(visited[nextRow][nextCol] == true)
                continue;

            if(array[nextRow][nextCol] == 2 || array[nextRow][nextCol] == 0){
                visited[nextRow][nextCol] = true;
                airChange(nextRow, nextCol);
            }

        }
    }
    public static void dfs(int row, int col){

        visited[row][col] = true;
        // 만약 1 주변에 2가하나라도 존재할시 count++;
        boolean check = false;
        for(int i = 0 ; i < 4; i++){
            int nextRow = row + dy[i];
            int nextCol = col + dx[i];
            if(array[nextRow][nextCol] == 2 && visited[nextRow][nextCol] == false) {
                check = true;
                break;
            }
        }
        if(check == true && array[row][col] == 1)
            array[row][col] = 2;

        for(int i = 0 ; i < 4; i++){
            int nextRow = row + dy[i];
            int nextCol = col + dx[i];

            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
                continue;

            if(visited[nextRow][nextCol] == true)
                continue;

            if(array[nextRow][nextCol] == 1){
                visited[nextRow][nextCol] = true;
                dfs(nextRow, nextCol);
            }

        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        array = new int[N][M];

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < M; j++)
                array[i][j] = Integer.parseInt(st.nextToken());
        }

        int timeCount = 0;
        int cheeseCount = 0;
        while(true){
            // 공기감염시키자
            visited = new boolean[N][M];
            airChange(0, 0);
            visited = new boolean[N][M];

            // 녹이기전 치즈의 갯수
            int sum = 0;
            for(int i = 0 ; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(array[i][j] == 1)
                        sum++;
                }
            }
            // 만약 치즈를 다녹였으면 break, 아니면 저장
            if(sum == 0)
                break;
            else
                cheeseCount = sum;
            // 치즈를 녹이자
            for(int i = 0; i < N; i++){
                for(int j = 0 ; j < M; j++){
                    if(array[i][j] == 1 && visited[i][j] == false){
                        dfs(i,j);
                    }
                }
            }
            timeCount++;
        }
        System.out.println(timeCount);
        System.out.println(cheeseCount);
    }
}
