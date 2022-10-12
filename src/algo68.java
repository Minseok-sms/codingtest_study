import java.util.*;

public class algo68 {

    static HashMap<String, Integer> map = new HashMap<>();
    static Set<String> set = new HashSet<>(); //보석의 종류를 기록
    public static int[] solution(String[] gems) {
        for(int i = 0; i < gems.length; i++) {
            set.add(gems[i]);
        }

        int start = 0;
        int tempStart = 0;
        int len = gems.length;
        Queue<String> q = new LinkedList<>();
        for(int i = 0; i < gems.length; i++) {
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);

            q.add(gems[i]);
            while(true) {
                String gem = q.peek();
                if(map.get(gem) > 1) {
                    map.put(gem, map.get(gem) - 1);
                    q.poll();
                    tempStart++;
                } else break;
            }

            if(map.size() == set.size()) {
                if(len > q.size()) {
                    len = q.size();
                    start = tempStart;
                }
            }
        }
        return new int[] {start + 1, start + len};
    }

    public static void main(String[] args) {
        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        solution(gems);
    }
}
//import java.util.HashMap;
//
//public class algo68 {
//
//    static HashMap<String, Integer> map = new HashMap<>();
//    public static int[] solution(String[] gems) {
//        int[] answer = {};
//        answer = new int[2];
//        for(String str : gems)
//            map.put(str, 1);
//
//        int gemsCount = map.size();
//        int start, end;
//        int min = 100001;
//        for(int i = 0; i < gems.length - (gemsCount - 1); i++){
//            map = new HashMap<>();
//            start = i + 1;
//            end = 0;
//            for(int j = i; j < gems.length; j++){
//                map.put(gems[j], 1);
//                if(map.size() == gemsCount){
//                    end = j + 1;
//                    break;
//                }
//            }
//            if(end != 0){
//                if(min > end - start) {
//                    min = end - start;
//                    answer[0] = start;
//                    answer[1] = end;
//                }
//            }
//        }
//        System.out.println(answer[0] + " " +  answer[1]);
//        return answer;
//    }
//
//    public static void main(String[] args) {
//        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
//        solution(gems);
//    }
//}
