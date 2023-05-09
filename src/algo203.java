import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class algo203 {

    /*
    백준 : 1238
    파티

    플로이드 - 와샬
     */
    static int N, M, X;
    static int[][] map;
    static int MAX_VALUE = 100_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0 ; j < N; j++)
                map[i][j] = MAX_VALUE;
        }

        // 단방향으로 연결하자.
        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            map[row][col] = w;
        }

        for(int k = 0; k < N; k++){
            for(int i = 0; i < N; i++){
                for(int j = 0 ; j < N; j++){
                    if(i == j)
                        continue;
                    if(map[i][j] > map[i][k] + map[k][j])
                        map[i][j] = map[i][k] + map[k][j];
                }
            }
        }
        int MIN = 0;
        for(int i = 0; i < N; i++) {
            if(i == X - 1)
                continue;
            MIN = Math.max(MIN, map[i][X - 1] + map[X - 1][i]);
        }
        System.out.println(MIN);


    }
}

