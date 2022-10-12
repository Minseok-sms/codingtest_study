import java.io.*;
import java.util.*;

public class algo21 {


    static boolean[][] visited;
    static int N;
    static ArrayList<Integer> array = new ArrayList<>();
    static int globalCount = 0;
    public static void dfs(int[][] map, int row, int col){

        if(map[row][col] == 1 && visited[row][col] == false){
            globalCount++;
            visited[row][col] = true;
        }

        if(row - 1 >= 0 && map[row][col] == 1 && map[row-1][col] == 1 && visited[row - 1][col] == false) {
            visited[row - 1][col] = true;
            globalCount++;
            dfs(map, row - 1, col);
        }
        if(row + 1 <= N-1 && map[row][col] == 1 && map[row+1][col] == 1 && visited[row + 1][col] == false) {
            visited[row + 1][col] = true;
            globalCount++;
            dfs(map, row + 1, col);
        }
        if(col - 1 >= 0 && map[row][col] == 1 && map[row][col-1] == 1 && visited[row][col - 1] == false){
            visited[row][col - 1] = true;
            globalCount++;
            dfs(map, row, col - 1);
        }
        if(col + 1 <= N-1 && map[row][col] == 1 && map[row][col + 1] == 1 && visited[row][col + 1] == false){
            visited[row][col + 1] = true;
            globalCount++;
            dfs(map, row, col + 1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < N; j++)
                map[i][j] = str.charAt(j) - 48;
        }
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N ;j++){
                dfs(map, i, j);
                if(globalCount != 0)
                  array.add(globalCount);
                globalCount = 0;
            }
        }
        Collections.sort(array);
        System.out.println(array.size());
        for(int i = 0 ; i < array.size(); i++) {
            System.out.print(array.get(i));
            if(i != array.size() - 1)
                System.out.println();
        }
    }
}
