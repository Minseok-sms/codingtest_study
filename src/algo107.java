import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class algo107 {
    /*
    백준 : 9465
    스티커
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i = 0 ; i  < T ; i++){
            int row = Integer.parseInt(br.readLine());
            int[][] array = new int[2][row];
            int[][] dp = new int[2][row + 1];
            for(int j = 0; j < 2; j++){
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for(int k = 0; k < row; k++)
                    array[j][k] = Integer.parseInt(st.nextToken());
            }
            dp[0][1] = array[0][0];
            dp[1][1] = array[1][0];

            for(int j = 2; j <= row ; j++){
                dp[0][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + array[0][j - 1];
                dp[1][j] = Math.max(dp[0][j - 1], dp[0][j - 2]) + array[1][j - 1];
            }
            // 50 50
            // 50 100
            System.out.println(Math.max(dp[0][row], dp[1][row]));
        }
    }
}
// 0  1  2    3   4  5
// 0 50 40   200 140 250
// 0 30 100  120 210 260