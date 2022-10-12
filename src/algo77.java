//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class algo77 {
//
//    static boolean[] visited1;
//
//    static HashMap<String, Integer> map = new HashMap<>();
//    static int globalCount = -1;
//    public static void dp(int start, String str, int depth, String str1){
//
//        if(str.length() == depth){
//            if(!map.containsKey(str))
//                map.put(str, 1);
//            else
//                globalCount = str.length();
//            return ;
//        }
//        for(int i = start; i < str1.length(); i++){
//            if(visited1[i] == true)
//                continue;
//            visited1[i] = true;
//            dp(i, str + str1.charAt(i), depth, str1);
//            visited1[i] = false;
//         }
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String str1 = br.readLine();
//        String str2 = br.readLine();
//
//        for(int i = str1.length(); i >= 0; i--){
//            visited1 = new boolean[str1.length()];
//            dp(0, "", i, str1);
//            visited1 = new boolean[str2.length()];
//            dp(0, "", i, str2);
//            if(globalCount >= 0){
//                System.out.println(globalCount);
//                break;
//            }
//        }
//    }
//}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class algo77 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for(int i = 1; i <= str1.length(); i++){
            for(int j = 1; j <= str2.length(); j++){
                if(str1.charAt(i - 1) == str2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] +  1;
                }else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        System.out.println(dp[str1.length()][str2.length()]);
    }
}
