import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
    카카오 : 오픈채팅방
 */
public class algo14 {
    public static String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<>();
        ArrayList<String> array = new ArrayList<>();
        for(String log : record){
            StringTokenizer st = new StringTokenizer(log, " ");
            String option = st.nextToken();
            String userId = st.nextToken();
            String nickname = "";

            if(!option.equals("Leave"))
                nickname = st.nextToken();

            switch(option){
                case "Enter":
                    map.put(userId, nickname);
                    array.add(nickname + "님이 들어왔습니다.");
                    break;
                case "Leave":
                    array.add(nickname + "님이 나갔습니다.");
                    break;
                case "Change":
                    map.put(userId, nickname);
                    break;
            }
        }
        String[] answer = new String[array.size()];
        int count = 0;
        for(String str : array){
            int endOfIdx = str.indexOf("님");
            String userId = str.substring(0, endOfIdx);
            answer[count++] = str.replace(userId, map.get(userId));
        }
        return answer;


//        String[] answer = {};
//        String[] option = {};
//        String[] uid = {};
//
//        option = new String[record.length];
//        uid = new String[record.length];
//
//        ArrayList<String> array = new ArrayList<>();
//        Map<String, ArrayList<String>> map = new HashMap<>();
//        int answerCount = 0;
//        for(int i = 0; i < record.length; i++){
//            StringTokenizer st = new StringTokenizer(record[i]," ");
//            String check = "";
//            String userId = "";
//            String nickname = "";
//;           if(st.countTokens() == 3) {
//                check = st.nextToken(); // enter, change, leave
//                userId = st.nextToken(); // 유저 아이디
//                nickname = st.nextToken(); // 닉네임
//            }
//            else{
//                check = st.nextToken(); // enter, change, leave
//                userId = st.nextToken(); // 유저 아이디
//            }
//
//
//            option[i] = check;
//            uid[i] = userId;
//            if(!check.equals("Change"))
//                answerCount++;
//            if(nickname == null){
//                continue;
//            }
//            if(map.get(userId) == null)
//                map.put(userId, new ArrayList<>());
//            map.get(userId).add(nickname);
//        }
//
//        answer = new String[answerCount];
//        answerCount = 0;
//        for(int i = 0; i < record.length; i++){
//            int arrayLength = map.get(uid[i]).size();
//            if(option[i].equals("Enter"))
//                array.add(map.get(uid[i]).get(arrayLength - 1) + "님이 들어왔습니다.");
//            else if(option[i].equals("Leave"))
//                array.add(map.get(uid[i]).get(arrayLength - 1) + "님이 나갔습니다.");
//        }
//        array.toArray(answer);
//        return answer;
    }

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};

        solution(record);
    }
}
