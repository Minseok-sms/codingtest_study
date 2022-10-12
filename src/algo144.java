import java.util.StringTokenizer;

public class algo144 {
    /*
    프로그래머스 : 문자열압축
     */
    public static int solution(String s) {
        int answer = 0;

        answer = s.length();
        // i = 2 (두개씩 잘라)
        for(int i = 1 ; i <= s.length() / 2; i++){
           int zipCount = 1;
           // ab
           String zipString = s.substring(0, i);
           StringBuilder result = new StringBuilder();
           for(int j = i; j <= s.length(); j += i){
               // ab
               String next = s.substring(j, j + i > s.length() ? s.length() : j + i);
               if(zipString.equals(next)){
                   zipCount++;
               }else{
                    result.append((zipCount == 1 ? "" : zipCount) + zipString);
                    zipString = next;
                    zipCount = 1;
               }
           }
           result.append(zipString);
           answer = Math.min(result.length(), answer);
        }
        // ab
        return answer;
    }
    public static void main(String[] args) {
        String str = "ababcdcdababcdcd";
        solution(str);
    }
}
