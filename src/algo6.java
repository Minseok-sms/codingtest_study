//import java.util.HashMap;
//
//public class algo6 {
//    public int solution(String[][] clothes) {
//        int answer = clothes.length;
//        HashMap<String, Integer> map = new HashMap<>();
//        for(int i = 0; i < clothes.length; i++)
//            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1);
//
//        if(map.size() > 1){ // key의 종류가 2가지 이상일시
//            int mul = 1;
//            for(String key : map.keySet())
//                mul *= map.get(key) + 1;  // 2, 1, 1 일시 각각 안입엇을때랑 + 종류  3 * 2 * 2
//            answer = mul - 1; // 싹다 안입엇을시 1개 뺌.
//        }
//        return answer;
//    }
//    public static void main(String[] args) {
//
//    }
//}

import java.util.HashMap;

public class algo6 {
    public int solution(String[][] clothes) {
        int answer = clothes.length;
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < clothes.length; i++)
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1);

        int multi = 1;
        if(map.size() > 1){
            for(String key : map.keySet())
                multi *= map.get(key) + 1;
            answer = multi - 1;
        }
        return answer;
    }
    public static void main(String[] args) {

    }
}
