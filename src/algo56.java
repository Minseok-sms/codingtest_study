import java.util.HashMap;

public class algo56 {

    public static int[] solution(String[] words, String[] queries) {
        int[] answer = {};
        HashMap<String, Integer> map = new HashMap<>();

        for(String str : queries)
            map.put(str, 0);
        for(int i = 0; i < words.length ; i++){
            for(String str : map.keySet()){
                if(words[i].length() != str.length())
                    continue;
                String temp = "";
                temp += words[i];

                for(int j = 0 ; j < str.length(); j++){
                    if(str.charAt(j) == '?')
                        temp = temp.substring(0, j) + '?' + temp.substring(j + 1);
                }
                if(map.containsKey(temp))
                    map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
        }

        answer = new int[queries.length];
        for(int i = 0 ; i < queries.length; i++) {
            answer[i] = map.get(queries[i]);
        }
        return answer;
    }
    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        solution(words, queries);

    }
}
