public class algo78 {

    /*
        x가 5, 3, 2로 나누어 떨어질때, -1 뺄때해서 1만들기위해 최소한의 연산은 얼마인가 (DP)
     */
    static int max = Integer.MAX_VALUE;
    static int[] dp = new int[30001];
    public static int solution(int num){

        for(int i = 2; i <= num ; i++){
            dp[i] = dp[i - 1] + 1;
            if(i % 2 == 0)
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            if(i % 3 == 0)
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            if(i % 5 == 0)
                dp[i] = Math.min(dp[i], dp[i / 5] + 1);
        }
        return dp[num];
    }
    public static void dp(int num, int count){

        if(num == 1){
            max = Math.min(max, count);
            return ;
        }
        if(num % 5 == 0)
            dp( num / 5, count + 1);
        if(num % 3 == 0)
            dp(num / 3, count + 1);
        if(num % 2 == 0)
            dp(num / 2, count + 1);

        dp(num - 1 , count + 1);
    }
    public static void main(String[] args) {
        //dp(26, 0);

        System.out.println(solution(26));
    }
}


// 0 0 1 1 2 1 2 3 3 2
// 0 1 2 3 4 5 6 7 8 9