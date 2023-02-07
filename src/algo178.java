//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//public class algo178 {
//
//    static int V, E;
//    static ArrayList<Node>[] array;
//    static boolean[] visited;
//    static int MIN = Integer.MAX_VALUE;
//    static class Node{
//        int end;
//        int value;
//        int idx;
//        Node(int end, int value, int idx){
//            this.end = end;
//            this.value = value;
//            this.idx = idx;
//        }
//    }
//    public static void find(int start, int real, int count, int depth, int sum){
//
//        if(count != 0){
//            if(start == real){
//                MIN = Math.min(MIN, sum);
//                return ;
//            }
//        }
//        if(count == depth){
//            if(start == real){
//                MIN = Math.min(MIN, sum);
//            }else
//                return ;
//        }
//
//        for(int i = 0; i < array[start].size(); i++){
//            Node temp = array[start].get(i);
//            if(visited[temp.idx] == true)
//                continue;
//
//            visited[temp.idx] = true;
//            find(array[start].get(i).end, real, count + 1, depth, sum + array[start].get(i).value);
//            visited[temp.idx] = false;
//        }
//
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        V = Integer.parseInt(st.nextToken());
//        E = Integer.parseInt(st.nextToken());
//
//        array = new ArrayList[V + 1];
//        for(int i = 0; i <= V; i++)
//            array[i] = new ArrayList<>();
//
//        for(int i = 0; i < E; i++){
//            st = new StringTokenizer(br.readLine(), " ");
//            array[Integer.parseInt(st.nextToken())].add(new Node(
//                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
//            , i));
//        }
//        for(int i = 1 ; i <= V; i++){
//            visited = new boolean[E];
//            find(i, i, 0, E, 0);
//        }
//        System.out.println(MIN);
//
//
//    }
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class algo178 {

    /*
    백준 : 1956
    운동

    ****플로이드 와샬
     */

    static int V, E;
    static int[][] map;
    static int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        map = new int[V + 1][V + 1];

        for(int i = 1 ; i <= V; i++){
            for(int j = 1 ; j <= V ; j++){
               if(i != j)
                   map[i][j] = INF;
            }
        }

        for(int i = 0 ; i < E; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map[a][b] = cost;
        }

        for(int k = 1; k <= V; k++){
            for(int i = 1; i <= V; i++){
                for(int j = 1; j <= V; j++){
                    if(i == j)
                        continue;
                    if(map[i][j] > map[i][k] + map[k][j])
                        map[i][j] = map[i][k] + map[k][j];
                }
            }
        }

        int MIN = INF;
        for(int i = 1 ; i <= V; i++){
            for(int j = 1 ; j <= V ; j++){
                if(i == j)
                    continue;
                if(map[i][j] != INF && map[j][i] != INF){
                    MIN = Math.min(MIN, map[i][j] + map[j][i]);
                }
            }
        }
        if(MIN == INF)
            System.out.println(-1);
        else
            System.out.println(MIN);
            // 12 (12, 11 + 12)
            // 13 (13, 12 + 23)
            //

    }
}

