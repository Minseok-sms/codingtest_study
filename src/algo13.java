import java.util.HashMap;
import java.util.Map;

/*
카카오 : 숫자 문자열과 영단어
 */
public class algo13 {

    public static int solution(String s) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();

        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);

        String start = "0";
        String end = "9";

        String answerStr = "";
        for(int i = 0 ; i < s.length(); i++){
            int startnum = (int)start.charAt(0);
            int lastnum = (int)end.charAt(0);
            int a = (int)s.charAt(i);
            // 숫자인지확인.
            if(startnum <= a && a <= lastnum)
                answerStr = answerStr.concat(Character.toString(s.charAt(i)));
            // 숫자가 아닐시.
            else{
                String answer2Str = "";
                for(int j = i; j < s.length(); j++){
                    a = (int)s.charAt(j);
                    if(startnum <= a && a <= lastnum){
                        i = j - 1;
                        break;
                    }else{
                        answer2Str = answer2Str.concat(Character.toString(s.charAt(j)));
                        if(map.get(answer2Str) != null){
                            answerStr += map.get(answer2Str);
                            answer2Str = "";
                        }
                        if(j == s.length() - 1){
                            i = j;
                        }
                    }
                }
            }

        }
        answer = Integer.parseInt(String.valueOf(answerStr));
        return answer;
    }
    public static void main(String[] args) {
        String str = "one4seveneight";
        solution(str);
    }
}
