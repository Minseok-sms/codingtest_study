import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class algo106 {
    /*
    백준 : 2579
    계단 오르기

     */
    static int[] dp;
    static int[] array;
    public static void dp(int count){

        if(count == 1) {
            dp[count] = array[0];
            return ;
        }
        else if(count == 2){
            dp[count] = array[0] + array[1];
            return ;
        }
        dp[count] = Math.max(dp[count - 2], dp[count - 3] + array[count - 2]) + array[count - 1];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        array = new int[N];
        dp = new int[N + 1];

        for(int i = 0 ; i < N; i++)
            array[i] = Integer.parseInt(br.readLine());


        for(int i = 1; i <= N; i++)
            dp(i);

        System.out.println(dp[N]);

    }
}
