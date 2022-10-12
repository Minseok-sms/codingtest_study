import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class algo58 {

    static boolean[] visited;
    static ArrayList<Integer> array = new ArrayList<>();
    static int globalCount = 0;
    public static void dfs(int start,int sum, int[] T, int[] P){


        if(start == T.length ) {
            globalCount = Math.max(sum, globalCount);
            return ;
        }
        if(start + T[start] <= P.length){
            dfs(start + T[start], sum + P[start], T, P);
        }
        dfs(start + 1, sum , T, P);

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N];
        int[] P = new int[N];
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0,0,T,P);
        System.out.println(globalCount);
    }
}
