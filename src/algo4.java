import java.util.HashMap;

public class algo4 {
    public static String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < participant.length; i++) // map에넣자. map에 중복된 key가 있으면 last가 저자됨
            map.put(participant[i], map.getOrDefault(participant[i], 0) + 1);

        for(int i = 0; i < completion.length; i++)
            map.put(completion[i], map.getOrDefault(participant[i], 0) - 1);

        for(String key : map.keySet()){
            if(map.get(key) > 0){
                answer = key;
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] participant = new String[]{"A", "B", "C"};
        String[] completion = new String[]{"B", "C"};
        
        String answer = solution(participant, completion);
        System.out.println("answer = " + answer);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("typedef", 0);
        map.put("hello", 1);
        map.put("hello", 2);
        map.put("hello", 3);
        map.put("typedef", 4);
        for(String key : map.keySet()){
            System.out.println("map.get(key) = " + map.get(key));
        }

    }
}
