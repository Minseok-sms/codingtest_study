//public class algo67 {
//
//
//    static int min = 0;
//    public static int solution(int[][] triangle) {
//        int answer = 0;
//        dp(0, triangle[0][0],1, triangle);
//        answer = min;
//        System.out.println(answer);
//        return answer;
//    }
//    public static void dp(int left, int sum, int depthCount,int[][] triangle) {
//        if (depthCount == triangle.length) {
//            min = Math.max(min, sum);
//            return;
//        }
//        for (int i = left; i < triangle[depthCount].length; i++) {
//
//                dp(i ,  sum + triangle[depthCount][i], depthCount + 1, triangle);
//                dp(i + 1 ,  sum + triangle[depthCount][i + 1], depthCount + 1, triangle);
//
//
//        }
//
//    }
//    public static void main(String[] args) {
//        int[][] triangle = {{7}, {3,8}, {8,1,0}, {2,7,4,4}, {4,5,2,6,5}};
//        solution(triangle);
//    }
//}

public class algo67 {

    /*
    프로그래머스 정수삼각형
     */

    static int min = 0;
    public static int solution(int[][] triangle) {

        int[][] dp = new int[triangle.length][triangle.length];
        dp[0][0] = triangle[0][0];

        for (int i=1; i< triangle.length; i++) {

            dp[i][0] = triangle[i][0] + dp[i - 1][0];

            for (int j=1; j<i+1; j++) {
                dp[i][j] = triangle[i][j] + Math.max(dp[i -1][j - 1], dp[i -1][j]);
            }
        }

        int max = 0;
        for (int i=0; i<dp[dp.length - 1].length; i++) {
            max = Math.max(dp[dp.length - 1][i], max);
        }


        int answer = max;
        return answer;
    }
    public static void dp(int left, int right, int sum, int depthCount,int[][] triangle) {
        if (depthCount == triangle.length - 1) {
            min = Math.max(min, sum);
            return;
        }
        for (int i = left; i < triangle[depthCount].length; i++) {
            dp(left , left + 1,  sum + triangle[depthCount][i], depthCount + 1, triangle);
        }

    }
    public static void main(String[] args) {
        int[][] triangle = {{7}, {3,8}, {8,1,0}, {2,7,4,4}, {4,5,2,6,5}};
        solution(triangle);
    }
}
