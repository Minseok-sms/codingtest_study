import java.util.HashMap;

public class algo149 {

    /*
    프로그래머스 : 자동완성
     */

    public static int solution(String[] words) {
        int answer = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            String temp = "";
            map.put(temp, map.getOrDefault(temp, 0) + 1);
            for(int j = 0; j < words[i].length(); j++){
                temp += words[i].charAt(j);
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
        }
        for(int i = 0 ; i < words.length; i++){
            // 2이상 == war , warror 할때 war는 3번쳐야하니까
            if(map.get(words[i]) >= 2) {
                answer += words[i].length();
                continue;
            }else{
                // world wordd
                // wordd 치면 ==1 이니가 앞에꺼 확인하면서
                // word == 1
                // wor == 2
                int count = 1;
                while(true){
                    String temp = words[i].substring(0, words[i].length() - count);
                    if(map.get(temp) == 1) {
                        count++;
                        continue;
                    }
                    else
                        break;
                }
                answer += words[i].length() - count + 1;
            }
        }
        return answer;

    }
    public static void main(String[] args) {
        String[] words = {"go","gone","guild"};
        solution(words);
    }
}
