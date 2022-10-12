import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class algo26 {

    static int[] array;
    static boolean[] visited;
    static ArrayList<Integer> answer = new ArrayList<>();
    static int total = 0;
    static int M;
    public static void dfs(int start, int depth, int count, int sum){

        if(count == depth){
            if(sum % M == 0)
                total++;
            return ;
        }
        for(int i = start; i < array.length; i++){
            if(visited[i] == true)
                continue;
            visited[i] = true;
            dfs(i, depth, count + 1, sum + array[i]);
            visited[i] = false;

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        array = new int[N];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N ; i++)
            array[i] = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());

        dfs(0, K, 0, 0);
        System.out.println(total);

    }
}
