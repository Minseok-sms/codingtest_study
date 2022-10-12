import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class algo158 {

    /*
    백준 : 2644
    촌수계산
     */
    static ArrayList<Integer>[] array;
    static boolean[] visited;
    static int N, num1, num2;
    static int M;
    public static void dfs(int start, int depart, int count){

        if(start == depart){
            System.out.println(count);
            System.exit(0);
        }
        for(int i = 0 ; i < array[start].size(); i++){
            if(visited[array[start].get(i)] == true)
                continue;

            visited[array[start].get(i)] = true;
            dfs(array[start].get(i), depart,count + 1);
            visited[array[start].get(i)] = false;

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        num1 = Integer.parseInt(st.nextToken());
        num2 = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        array = new ArrayList[N + 1];
        for(int i = 0; i < N + 1; i++)
            array[i] = new ArrayList<>();

        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int temp1 = Integer.parseInt(st.nextToken());
            int temp2 = Integer.parseInt(st.nextToken());
            array[temp1].add(temp2);
            array[temp2].add(temp1);
        }

        visited = new boolean[N + 1];
        dfs(num1, num2, 0);
        visited = new boolean[N + 1];
        dfs(num2, num1, 0);
        System.out.println(-1);
    }
}
