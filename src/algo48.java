import java.util.*;

public class algo48 {
    public static String[] solution(String[] logs) {
        String[] answer = {};
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<String, String> check = new HashMap<>();
        Arrays.sort(logs);
        ArrayList<String> log = new ArrayList<>();
        if(logs.length == 1)
            log.add(logs[0]);
        else {
            for (int j = 1; j < logs.length; j++) {
                if (logs[j].equals(logs[j - 1])) {
                    log.add(logs[j - 1]);
                    j++;
                } else
                    log.add(logs[j - 1]);
            }
        }


        for(int i = 0 ; i < log.size(); i++){
            StringTokenizer st = new StringTokenizer(log.get(i)," ");
            String name = st.nextToken();
            String question = st.nextToken();
            map.put(name, map.getOrDefault(name, 0) + 1);

        }
        double oneQuestion = 0;
        for(String key : map.keySet()){
            if(map.get(key) >= 1)
                oneQuestion++;
        }
        // 한문제 이상 푼사람 구함.
        map = new HashMap<>();
        for(int i = 0; i < log.size();i++){
            StringTokenizer st = new StringTokenizer(log.get(i)," ");
            st.nextToken();
            String question = st.nextToken();
            map.put(question, map.getOrDefault(question, 0) + 1);
        }
        double temp = 0;
        if(oneQuestion == 1){
            temp = oneQuestion;
        }else if(oneQuestion >= 2) {
            temp = (oneQuestion / 2);
        }

        ArrayList<String> array = new ArrayList<>();
        for(String key : map.keySet()){
            if(map.get(key) >= temp)
                array.add(key);
        }
        Collections.sort(array);
        answer = new String[array.size()];
        int count = 0;
        for(String str : array){
            answer[count++] = str;
            System.out.println(str);
        }
        return answer;
    }
    public static void main(String[] args) {
        String[] logs = {"kate sqrt"};
        solution(logs);
    }
}
