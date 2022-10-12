import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class algo119 {

    /*
    백준 : 11052번
    카드구매하기
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        int[] array = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 1; i < N + 1; i++)
            array[i] = Integer.parseInt(st.nextToken());

        for(int i = 1 ; i <= N; i++){
            for(int j = 1; j <= i; j++){
                dp[i] = Math.max(dp[i], dp[i - j] + array[j]);
            }
        }
        System.out.println(dp[N]);
    }
}

