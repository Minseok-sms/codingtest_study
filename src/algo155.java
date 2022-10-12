import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class algo155 {
    /*
    백준 : 14889
    스타트와 링크
     */
    static int[][] map;
    static boolean[] visited;
    static int N;
    static ArrayList<Integer> startT;
    static ArrayList<Integer> linkT;
    static int MIN = Integer.MAX_VALUE;
    public static void dfs(int start, int count, int depth){

        if(count == depth){
            startT = new ArrayList<>();
            linkT = new ArrayList<>();
            int startCount = 0;
            int linkCount = 0;
            for(int i = 0 ; i < N; i++){
                if(visited[i] == true)
                    startT.add(i);
                else
                    linkT.add(i);
            }

            for(int i = 0; i < startT.size(); i++){
                for(int j = 0 ; j < startT.size(); j++){
                    if(i == j)
                        continue;
                    startCount += map[startT.get(i)][startT.get(j)];
                }
            }

            for(int i = 0; i < linkT.size(); i++){
                for(int j = 0 ; j < linkT.size(); j++){
                    if(i == j)
                        continue;
                    linkCount += map[linkT.get(i)][linkT.get(j)];
                }
            }
            MIN = Math.min(MIN, Math.abs(startCount - linkCount));
            return ;
        }
        for(int i = start ; i < N; i++){
            if(visited[i] == true)
                continue;

            visited[i] = true;
            dfs(i, count + 1, depth);
            visited[i] = false;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N];

        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 0, N / 2);
        System.out.println(MIN);
    }
}
