//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.HashMap;
//
//public class algo196 {
//    static class Node{
//        int total;
//        int same;
//        Node(int total, int same){
//            this.total = total;
//            this.same = same;
//        }
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int T = Integer.parseInt(br.readLine());
//        StringBuilder sb = new StringBuilder();
//        for(int i = 0 ; i < T; i++){
//            String str = br.readLine();
//            int K = Integer.parseInt(br.readLine());
//            if(K == 1) {
//                sb.append("1 1" + "\n");
//                continue;
//            }
//
//            boolean minCheck = false;
//            // 한방에 젤 짧은 연속문자 = 싹다 겹쳐잇을때. 먼저구하자
//            for(int j = 0 ; j < str.length(); j++){
//                String temp = "";
//                temp += str.charAt(j);
//                for(int k = 0 ; k < K - 1; k++)
//                    temp += str.charAt(j);
//
//                if(str.contains(temp)) {
//                    minCheck = true;
//                    break;
//                }
//            }
//
//            // 두번째 게임
//            HashMap<Character, Node> map = new HashMap<>();
//            int MAX = 0;
//            int MIN = Integer.MAX_VALUE;
//            if(minCheck == true)
//                MIN = K;
//
//
//            for(int j = 0 ; j < str.length(); j++){
//                char c = str.charAt(j);
//                if(!map.containsKey(c)){
//                    map.put(c, new Node(0, 0));
//                    // 중복되는 키가 없으면
//                    // 모든 key의 value + 1;
//                    for(Character t : map.keySet())
//                        map.get(t).total++;
//                }else{
//                    // 중복되는 키가 나왔다.
//                    // 모든 key value + 1;  + 해당 key의 same값 + 1;
//                    for(Character t : map.keySet()) {
//                        map.get(t).total++;
//                        if(t == c) {
//                            map.get(t).same++;
//                            // 만약 same == K개 달성했으면 해당 값 total = 1, same = 0으로 초기화
//                            if(map.get(t).same == K - 1) {
//                                MAX = Math.max(map.get(t).total, MAX);
//                                if(minCheck == false)
//                                      MIN = Math.min(map.get(t).total, MIN);
//                                map.get(t).total = 1;
//                                map.get(t).same = 0;
//                            }
//                        }
//                    }
//                }
//            }
//            if(MIN == Integer.MAX_VALUE || MAX == 0) {
//                 sb.append("-1" + "\n");
//            }
//            else sb.append(MIN + " " + MAX + "\n");
//        }
//        System.out.println(sb);
//
//    }
//}
import java.io.*;
import java.util.*;

public class algo196 {
    /*
    백준 : 20437
    문자열게임2
     */

    public static void main(String[] args) throws IOException {

        //입력
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bf.readLine());
        for(int i = 0; i < t; i++) {
            String str = bf.readLine();
            int k = Integer.parseInt(bf.readLine());
            //입력 끝

            if(k == 1) { //k가 1일때
                System.out.println("1 1");
                continue;
            }

            int[] alpha = new int[26];//알파벳 별 개수를 저장한다.
            for(int j = 0; j < str.length(); j++) {
                alpha[str.charAt(j) - 'a']++;
            }

            int min = Integer.MAX_VALUE;
            int max = -1;
            for(int j = 0; j < str.length(); j++) {
                if(alpha[str.charAt(j) - 'a'] < k) continue;

                int count = 1;
                for(int l = j + 1; l < str.length(); l++) {
                    if(str.charAt(j) == str.charAt(l)) count++;
                    if(count == k) {
                        min = Math.min(min, l - j + 1);
                        max = Math.max(max, l - j + 1);
                        break;
                    }
                }
            }
            if(min == Integer.MAX_VALUE || max == -1) System.out.println("-1");
            else System.out.println(min + " " + max);
        }
    }
}
