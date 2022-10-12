import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class algo156 {

    /*
    백준 : 14890
    경사로
     */
    static int N, L;
    static int[][] map;
    static int globalCount = 0;

    public static boolean checkPath(int x, int y, boolean flag){

        int[] height = new int[N];
        boolean[] visited = new boolean[N];

        for(int i = 0 ; i < N; i++){
            if(flag == true)
                height[i] = map[x][i];
            else
                height[i] = map[i][y];
        }

        for(int i = 0 ; i < N - 1; i++){

            if(height[i] == height[i + 1])
                continue;

            // 내리막 경사일때
            else if(height[i] - height[i + 1] == 1){
                for(int j = i + 1 ; j <= i + L; j++){
                    if(j >= N || height[i + 1] != height[j] || visited[j] == true)
                        return false;
                    visited[j] = true;
                }
            }else if(height[i] - height[i + 1] == -1){
                // 오르막일때
                for(int j = i ; j > i - L; j--){
                    if(j < 0 || height[i] != height[j] || visited[j] == true)
                        return false;
                    visited[j] = true;
                }
            }
            else
                return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++){
            if(checkPath(i, 0, true)) // 행
                globalCount++;
            if(checkPath(0, i, false)) // 열
                globalCount++;
        }

        System.out.println(globalCount);

    }
}
