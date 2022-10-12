//import java.util.Arrays;
//import java.util.Collections;
//import java.util.HashMap;
//
//public class algo147 {
//
//
//    static int answer = 0;
//    static HashMap<Integer, Integer> map = new HashMap<>();
//    public static void Search(int n, int[] money, int count, String str){
//        if(count == 0){
//            answer++;
//            String[] temp = new String[str.length()];
//            for(int i = 0 ; i < str.length(); i++)
//                temp[i] = Character.toString(str.charAt(i));
//            Arrays.sort(temp, Collections.reverseOrder());
//            str = "";
//            for(int i = 0 ; i < temp.length; i++)
//                str += temp[i];
//            map.put(Integer.parseInt(str), map.getOrDefault(Integer.parseInt(str), 0) + 1);
//            return ;
//        }
//        if(count < 0)
//            return ;
//
//        for(int i = 0; i < money.length; i++){
//            Search(n, money, count - money[i], str += money[i]);
//            str = str.substring(0, str.length() - 1);
//        }
//    }
//    public static int solution(int n, int[] money) {
//        Search(n, money, n, "");
//        for(int i : map.keySet()){
//            if(map.get(i) > 1)
//                answer -= map.get(i) - 1;
//        }
//        return answer % 1_000_000_007;
//    }
//    public static void main(String[] args) {
//        int n = 5;
//        int[] money = {1,2,5};
////        String str = "1112";ㅌㅂ
////        String[] temp = new String[str.length()];
////        for(int i = 0 ; i < str.length(); i++)
////            temp[i] = Character.toString(str.charAt(i));
////        Arrays.sort(temp, Collections.reverseOrder());
////        str = "";
////        for(int i = 0 ; i < temp.length; i++){
////            str += temp[i];
////        }
////        System.out.println(str);
//
//
//        solution(n, money);
//    }
//}
//
//// 5C3 -> 543
////         321 * 21 = 5;
//
//// 5 4 3 2 1
//// 3 2 1 * 2 1


import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class algo147 {

    /*
    프로그래머스 : 거스름돈
     */

    static int[][] dp;

    public static int solution(int n, int[] money) {
        dp = new int[money.length+1][n+1];
        int answer = 0;
        for(int i = 1; i < money.length+1; i++){
            for(int j = 0; j < n+1; j++){
                if(j == 0)
                    dp[i][j] = 1;
                else {
                    if(j < money[i-1])
                        dp[i][j] = dp[i-1][j];
                    else
                        dp[i][j] = (dp[i-1][j] + dp[i][j-money[i-1]]) % 1000000007;
                }
            }
        }
        return dp[money.length][n];
    }
    public static void main(String[] args) {
        int n = 5;
        int[] money = {1,2,5};

        solution(n, money);
    }
}

// 5C3 -> 543
//         321 * 21 = 5;

// 5 4 3 2 1
// 3 2 1 * 2 1