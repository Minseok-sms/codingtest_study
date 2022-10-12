import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class algo124 {
    /*
    백준 : 2156
    포도주 시식
     */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N + 1];
        int[] dp = new int[N + 1];
        for(int i = 1 ; i <= N; i++)
            array[i] = Integer.parseInt(br.readLine());

        dp[1] = array[1];
        if(N > 1)
            dp[2] = array[1] + array[2];


        int min = 0;
        for(int i = 3; i <= N; i++){
            min = dp[i - 1];
            dp[i] = Math.max(dp[i - 2]  + array[i], dp[i - 3] + array[i - 1]  + array[i]);
            dp[i] = Math.max(dp[i], min);
        }
        System.out.println(dp[N]);
    }
}
