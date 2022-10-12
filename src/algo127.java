import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class algo127 {

    /*
    백준 : 11726
    2xN 타일링
     */

    static int[] dp;
    static final int MOD = 10_007;
    public static int search(int num){

        if(num == 0) {
            return 0;
        }else if(num == 1)
            return 1;
        else if(dp[num] != 0){
            return dp[num];
        }

        dp[num] = search(num - 1) % MOD + search(num - 2) % MOD;
        return dp[num];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        if(N == 1){
            System.out.println("1");
            System.exit(0);
        }
        dp[2] = 2;
        System.out.println(search(N) % MOD);
    }
}


// 1 -> 1 (1)
// 2 -> 11, 2 (2)
// 3 -> 111, 12, 21 (3)
// 4 -> 1111, 112, 211, 121,22, (5)
// 5 -> 11111, 1112, 1121, 1211, 2111, 221, 212,122 (8)
// 6 -> 111111, 11112, 11121, 11211, 12111, 21111, 1122, 1212,2112, 2121, 2211, 1221, 222 (13)