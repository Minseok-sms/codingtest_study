//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//public class algo169 {
//
//
//    /*
//    백준 : 1826
//    연료 채우기
//     */
//
//    static int[][] map;
//    static int N;
//    static int L, P;
//    static ArrayList<Node> array = new ArrayList<>();
//    static boolean[] visited;
//    static int maxVisited = -1;
//    static class Node{
//        int num1;
//        int num2;
//        Node(int num1, int num2){
//            this.num1 = num1;
//            this.num2 = num2;
//        }
//    }
//
//    public static void choicePoint(int start, int count, int depth, int now){
//        if(count == depth){
//            if(L - array.get(start).num1 > now)
//                return ;
//            maxVisited = depth;
//            return ;
//        }
//
//        for(int i = start ; i < array.size(); i++){
//            if(visited[i] == true)
//                continue;
//            if(count == 0){
//                if(array.get(i).num1 > now)
//                    continue;
//            }else {
//                if (array.get(i).num1 - array.get(start).num1 > now)
//                    continue;
//            }
//
//            visited[i] = true;
//            if(count == 0)
//                choicePoint(i,count + 1, depth, now - array.get(i).num1 + array.get(i).num2);
//            else
//                choicePoint(i, count + 1, depth, now - (array.get(i).num1 - array.get(start).num1) + array.get(i).num2 );
//            visited[i] = false;
//        }
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//
//
//        for(int i = 0 ; i < N; i++){
//            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//            int num1 = Integer.parseInt(st.nextToken());
//            int num2 = Integer.parseInt(st.nextToken());
//            array.add(new Node(num1, num2));
//        }
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        L = Integer.parseInt(st.nextToken());
//        P = Integer.parseInt(st.nextToken());
//
//        Collections.sort(array, new Comparator<Node>() {
//            @Override
//            public int compare(Node o1, Node o2) {
//                if(o1.num1 < o2.num1)
//                    return -1;
//                return 0;
//            }
//        });
//
//        for(int i = 1 ; i <= N; i++){
//            //maxVisited가 음수가 아닐때까지 돌자
//            visited = new boolean[N];
//            choicePoint(0, 0, i, P);
//            if(maxVisited >= 0)
//                break;
//        }
//        System.out.println(maxVisited);
//
//    }
//}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class algo169 {


    /*
    백준 : 1826
    연료 채우기
     */

    static int[][] map;
    static int N;
    static int L, P;
    static ArrayList<Node> array = new ArrayList<>();
    static boolean[] visited;
    static int maxVisited = 0;
    static class Node implements Comparable<Node>{
        int num1;
        int num2;
        Node(int num1, int num2){
            this.num1 = num1;
            this.num2 = num2;
        }
        @Override
        public int compareTo(Node o) {
            return this.num1 - o.num1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Node> distance = new PriorityQueue<>();

        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            distance.offer(new Node(num1, num2));
        }
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());


       PriorityQueue<Integer> fuels = new PriorityQueue<>(Collections.reverseOrder());

        while (P < L) {
            //처음 기름 10으로 갈수 있는 곳중에 기름량을 다넣어봐
            while(!distance.isEmpty() && P >= distance.peek().num1){
                Node temp = distance.poll();
                fuels.offer(temp.num2);
            }
            if(fuels.isEmpty()){
                maxVisited = -1;
                break;
            }

            P = P + fuels.poll();
            maxVisited++;
        }
        System.out.println(maxVisited);

    }
}
