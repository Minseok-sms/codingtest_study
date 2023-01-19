//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//
//public class algo174 {
//
//    static ArrayList<Integer> answer;
//    static int N;
//    static int[] array;
//    static int MAX = 0;
//    static int find = 0;
//    static boolean[] visited;
//    static HashMap<Integer, Integer> map;
//    public static void dfs(String str,int start, int count, int depth){
//        if(count == depth){
//            map = new HashMap<>();
//            //맵에 넣고
//            for(int j = 0 ; j < str.length(); j++)
//                map.put(str.charAt(j) - '0' + 1, 0);
//
//            //인덱스에 해당되는 배열값 비교해보자
//            for(int j = 0 ; j < str.length(); j++)
//                map.put(array[str.charAt(j) - '0'], map.getOrDefault(array[str.charAt(j) - '0'], 0) + 1);
//
//            boolean check = true;
//            for(int key : map.keySet()){
//                if(map.get(key) == 0) {
//                    check = false;
//                    break;
//                }
//            }
//            //체크가 true이면 배열끼리 일치하다.
//            if(check == true) {
//                answer = new ArrayList<>();
//                for(int key : map.keySet())
//                    answer.add(key);
//                find = depth;
//            }
//            return ;
//        }
//        for(int i = start; i < N; i++){
//            if(visited[i] == true)
//                continue;
//
//            visited[i] = true;
//            dfs(str + i, i, count + 1, depth);
//            visited[i] = false;
//        }
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//        array = new int[N];
//        for(int i = 0 ; i < N; i++)
//            array[i] = Integer.parseInt(br.readLine());
//
//        // 깊이 늘려가면서 뽑자
//        for(int i = 1; i <= N; i++){
//            find = 0;
//            visited = new boolean[N];
//            dfs("",0, 0, i);
//            // 만약 더큰집합찾으면 저장하고 아니면 끝내면댐
//            if(find > MAX)
//                MAX = find;
//        }
//        Collections.sort(answer);
//        System.out.println(MAX);
//        for(int i = 0; i < answer.size(); i++)
//            System.out.println(answer.get(i));
//
//
//    }
//}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class algo174 {
    /*
        백준 : 2668
        숫자고르기
    */

    static ArrayList<Integer> answer;
    static int N;
    static int[] array;
    static int MAX = 0;
    static int find = 0;
    static boolean[] visited;
    public static void dfs(String str,int start, int count, int depth){
        if(count == depth){
            int[] comp1 = new int[str.length()];
            int[] comp2 = new int[str.length()];
            for(int j = 0 ; j < str.length(); j++) {
                comp1[j] = str.charAt(j) - '0' + 1;
                comp2[j] = array[str.charAt(j) - '0'];
            }
            // 두번째는 정렬해주자
            Arrays.sort(comp2);
            boolean check = true;
            for(int j = 0; j < str.length(); j++){
                if(comp1[j] != comp2[j])
                    check = false;
            }
            //체크가 true이면 배열끼리 일치하다.
            if(check == true) {
                answer = new ArrayList<>();
                for(int j : comp2)
                    answer.add(j);
                find = depth;
            }
            return ;
        }
        for(int i = start; i < N; i++){
            if(visited[i] == true)
                continue;

            visited[i] = true;
            dfs(str + i, i, count + 1, depth);
            visited[i] = false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N];
        for(int i = 0 ; i < N; i++)
            array[i] = Integer.parseInt(br.readLine());

        // 깊이 늘려가면서 뽑자
        for(int i = 1; i <= N; i++){
            find = 0;
            visited = new boolean[N];
            dfs("",0, 0, i);
            // 만약 더큰집합찾으면 저장하고 아니면 끝내면댐
            if(find > MAX)
                MAX = find;
        }
        Collections.sort(answer);
        System.out.println(MAX);
        for(int i = 0; i < answer.size(); i++)
            System.out.println(answer.get(i));




    }
}
