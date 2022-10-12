//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class algo38 {
//
//    static boolean[] visited;
//    static int[] array;
//    static int global = 0;
//    public static void dfs(int start, int sum, int count,int comp){
//
//        visited[start] = true;
//        if(sum == comp && count == 2){
//            global = 1;
//            return ;
//        }
//
//        for(int i = 0 ; i < array.length; i++){
//            if(visited[i] == true)
//                continue;
//
//            visited[i] = true;
//            dfs(i, sum + array[i],count + 1, comp);
//            visited[i] = false;
//         }
//
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(br.readLine());
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        int answer = 0;
//        visited = new boolean[N];
//        array = new int[N];
//        for(int i = 0; i < N; i++)
//            array[i] = Integer.parseInt(st.nextToken());
//        for(int i = 0 ; i < N; i++){
//            visited = new boolean[N];
//            dfs(i, 0,0,array[i]);
//            answer += global;
//            global = 0;
//        }
//        System.out.println(answer);
//
//    }
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class algo38 {

    static boolean[] visited;
    static int[] array;
    static int global = 0;
    public static void dfs(int start, int sum, int count,int comp){

        visited[start] = true;
        if(sum == comp && count == 2){
            global = 1;
            return ;
        }

        for(int i = 0 ; i < array.length; i++){
            if(visited[i] == true)
                continue;

            visited[i] = true;
            dfs(i, sum + array[i],count + 1, comp);
            visited[i] = false;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int answer = 0;
        array = new int[N];

        for(int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());

        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0 ; i < N; i++)
            map.put(array[i], 0);

        for(int i = 0 ; i < N; i++) {
            for(int j = i + 1 ; j < N; j++){
                int temp = array[i] + array[j];
                if(map.containsKey(temp))
                    map.put(temp, 1);
            }
        }
        for(int i = 0 ; i < N ; i++){
            if(map.containsKey(array[i])){
                map.put(array[i], map.get(array[i]) + 1);
            }
        }
        for(int i : map.keySet()){
            if(map.get(i) >= 1) {
                answer += map.get(i) - 1;
            }
        }
        System.out.println(answer);

    }
}
