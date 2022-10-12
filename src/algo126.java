import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class algo126 {
    /*
       백준 : 11057
       오르막수
     */

    static final int MOD = 10007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N + 1][10];

        for(int i = 0; i < 10; i++)
            dp[1][i] = 1;

        for(int i = 2; i <= N; i++){
            for(int j = 0; j < 10; j++) {
                for (int k = 0; k <= j; k++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][k] % MOD);
                }
            }
        }
        long answer = 0;
        for(int i = 0 ; i < 10; i++)
            answer = (answer + dp[N][i]) % MOD;

        System.out.println(answer);
    }
}



//         0 1 2 3 4 5 6 7 8 9
// 0자리 -> 0 0 0 0 0 0 0 0 0 0
// 1자리 -> 1 1 1 1 1 1 1 1 1 1
// 2자리 -> 10 9 8 7 6 5 4 3 2 1
// 3자리 -> 55


// 01 02 03 04 05 06 07 08 09


// 9 +
// 01 23456789
// 02 3456789
// 03 456789
// 04 56789
// 05 6789
// 06
// 07
// 08
// 09