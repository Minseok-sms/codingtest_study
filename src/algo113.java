import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class algo113 {

    /*
    백준 : 2293
    동전 1
     */

    static int[] array;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        array = new int[N + 1];

        for(int i = 1 ; i <= N; i++)
            array[i] = Integer.parseInt(br.readLine());


        dp = new int[K + 1];

        dp[0] = 1;
        for(int i = 1 ; i <= N; i++){
            for(int  j = array[i]; j <= K; j++){
                dp[j] += dp[j - array[i]];
            }
        }
        System.out.println(dp[K]);

    }
//    static int[] array;
//    static int globalCount = 0;
//    public static void dfs(int start, int sum, int k){
//
//        if(sum == k){
//            globalCount++;
//            return ;
//        }
//        if(sum > k)
//            return ;
//        for(int i = start ; i < array.length; i++){
//            dfs(i, sum + array[i], k);
//        }
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        int N = Integer.parseInt(st.nextToken());
//        int K = Integer.parseInt(st.nextToken());
//
//        array = new int[N];
//
//        for(int i = 0 ; i < N; i++)
//            array[i] = Integer.parseInt(br.readLine());
//
//        dfs(0, 0, K);
//
//        System.out.println(globalCount);
//
//    }
}
