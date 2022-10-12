import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class algo118 {
    /*
    백준 : 15686
    치킨 배달
     */
    static ArrayList<Node> home = new ArrayList<>();
    static ArrayList<Node> chicken = new ArrayList<>();
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;
    static int N,M;

    static int[][] map;
    static class Node{
        int y;
        int x;
        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    public static void dfs(int start, int depth){
        if(depth == M){

            int result = 0;
            for(int i = 0 ; i < home.size(); i++){
                int temp = Integer.MAX_VALUE;

                for(int j = 0 ; j < chicken.size(); j++){
                    if(visited[j] == true){
                        int distance = Math.abs(home.get(i).y - chicken.get(j).y) +
                                Math.abs(home.get(i).x - chicken.get(j).x);

                        // 치킨 두곳 돌면서 한 home과 거리중 짧은곳 선택
                        temp = Math.min(distance, temp);
                    }
                }
                result += temp;
            }
            answer = Math.min(answer, result);
            return ;
        }
        for(int i = start; i < chicken.size(); i++){
            if(visited[i] == true)
                continue;

            visited[i] = true;
            dfs(i, depth + 1);
            visited[i] = false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1)
                    home.add(new Node(i,j));
                else if(map[i][j] == 2)
                    chicken.add(new Node(i,j));
            }
        }


        visited = new boolean[chicken.size()];

        dfs(0,0);
        System.out.println(answer);



    }
}
