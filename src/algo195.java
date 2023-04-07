import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class algo195 {
    static int N, M;
    static ArrayList<Integer>[] array;
    static boolean[] visited;
    //static int[][] map;
    static String plan = "";
    static boolean answer = true;

    public static void dfs(int start, int count, int depth, String str) {

        if (str.length() > plan.length())
            return;

        if (answer == true)
            return;

        if (count == depth) {
            if (plan.equals(str))
                answer = true;
            return;
        }

        for (int i = 0; i < array[start].size(); i++) {
            dfs(array[start].get(i), count + 1, depth, str + array[start].get(i));
        }
//        for(int i = 1 ; i < N + 1; i++){
//            if(map[start][i] == 1){
//                dfs(i, count + 1, depth, str + i);
//            }
 //       }
    }
    public static void bfs(int start){
        visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        while(!queue.isEmpty()){
            start = queue.poll();

            for(int i = 0 ; i < array[start].size(); i++){
                if(visited[array[start].get(i)] == true)
                    continue;
                visited[array[start].get(i)] = true;
                queue.add(array[start].get(i));
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        array = new ArrayList[N + 1];
        for(int i = 0; i < N + 1; i++)
            array[i] = new ArrayList<>();

        //map = new int[N + 1][N + 1];


        // 연결하자.
        for(int i = 1 ; i < N + 1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1 ; j < N + 1; j++){
                int num = Integer.parseInt(st.nextToken());
                if(num == 1){
                    array[i].add(j);
                    array[j].add(i);
                }
            }
        }
        // M개의 계획도시

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < M; i++)
            plan += st.nextToken();

        int startTemp = plan.charAt(0) - '0';
        String str = "";
        str += startTemp;
        //dfs(startTemp, 1, M, str);
        bfs(startTemp);
        for(int i = 1 ; i < N + 1; i++){
            if(visited[i] == false)
                answer = false;
        }
        if(answer == true)
            System.out.println("YES");
        else
            System.out.println("NO");


    }
}
