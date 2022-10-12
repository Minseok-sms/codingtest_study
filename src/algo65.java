import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class algo65 {
    /*
        프로그래머스 양과늑대 x
     */
    static ArrayList<Node>[] array;
    static boolean[][][] visited;

    static int max = 0;
   static class Node{
        int end;
        int value;

        Node(int end, int value){
            this.end = end;
            this.value = value;
        }

        void setEnd(int end){
            this.end = end;
        }
        void setValue(int value){
            this.value = value;
        }
    }
    static int totalSheep = 1;
    static boolean[] sheepVisited;
    static boolean[] wolfVisited;
    public static void dfs(int start,int sheep,int dev){

        max = Math.max(max, sheep);

       for(int i = 0; i < array[start].size(); i++){
           int next = array[start].get(i).end;
           if(visited[next][sheep][dev] == true)
               continue;

           if(sheep <= array[start].get(i).value + dev)
               continue;

           visited[next][sheep][dev] = true;
           //만약 다음 접근하려는 노드가 양이면 sum에다가 1 추가해서 넘김
           if(array[start].get(i).value == 0) {
               if(sheepVisited[array[start].get(i).end] == false){
                   sheepVisited[array[start].get(i).end] = true;
                   sheep += 1;
               }
               dfs(array[start].get(i).end, sheep, dev + 0);
           }
           // 만약 다음 접근하려는 노드가 늑대이면 sum값(양의수)와 비교해서 양의수가 더 많으면 진행
           else{
               if(sheep > dev + 1) {
                   dfs(array[start].get(i).end, sheep, dev + 1);
               }
           }
           visited[next][sheep][dev] = false;
       }


    }
//    public static void bfs(int start){
//        Queue<Node> queue = new LinkedList<>();
//        queue.add(new Node(start, 0));
//
//        while(!queue.isEmpty()){
//            Node temp = queue.poll();
//            int starts = temp.end;
//            int value = temp.value;
//            visited[starts] = true;
//
//            for(int i = 0 ; i < array[starts].size(); i++){
//                if(visited[array[starts].get(i).end] == true)
//                    continue;
//
//                if(array[starts].get(i).value == 0) {
//                    queue.add(new Node(array[starts].get(i).end, 0));
//                    totalSheep++;
//                }else{
//                    if(totalSheep > value + 1){
//                        queue.add(new Node(array[starts].get(i).end, value + 1));
//                    }
//
//                }
//            }
//        }
//
//    }
    public static int solution(int[] info, int[][] edges) {
        int answer = 0;
        array = new ArrayList[info.length];
        visited = new boolean[info.length][info.length + 1][info.length + 1];
        sheepVisited = new boolean[info.length];
        wolfVisited = new boolean[info.length];

        // node[0] = 1, 8    //
        //           0  0

        for(int i = 0 ; i < info.length; i++)
            array[i] = new ArrayList<>();

        for(int i = 0; i < edges.length; i++){
            array[edges[i][0]].add(new Node(edges[i][1], info[edges[i][1]]));
            array[edges[i][1]].add(new Node(edges[i][0], info[edges[i][0]]));
        }

        visited[0][1][0] = true;
        sheepVisited[0] = true;
       dfs(0,1,0);
        System.out.println(max);
        answer = max;
        return answer;
    }
    public static void main(String[] args) {
       int[] info = {0,1,0,1,1,0,1,0,0,1,0};
       //int[][] edges = {{0,1}, {1,2}, {1,4}, {0,8}, {8,7}, {9,10}, {9,11}, {4,3}, {6,5},
               //{4,6}, {8,9}};
        int[][] edges = {{0,1}, {0,2}, {1,3}, {1,4}, {2,5}, {2,6}, {3,7}, {4,8}, {6,9}, {9,10}};

       solution(info,edges);
    }
}
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Queue;
//
//public class algo65 {
//    static ArrayList<Integer>[] graphs;
//    static boolean[][][] visited;
//    static int answer = 1;
//    static int[] infos;
//
//    public static int solution(int[] info, int[][] edges) {
//        graphs = new ArrayList[info.length];
//
//        for(int i = 0; i < info.length; i++){
//            graphs[i] = new ArrayList<>();
//        }
//
//        for(int i = 0; i < edges.length; i++){
//            int[] edge = edges[i];
//            // 양방향 이동 가능
//            graphs[edge[0]].add(edge[1]);
//            graphs[edge[1]].add(edge[0]);
//        }
//
//        visited = new boolean[info.length][info.length+1][info.length+1];
//        infos = info;
//        visited[0][0][0] = true;
//        dfs(0,0,0);
//        return answer;
//    }
//
//    public static void dfs(int idx, int sheep, int wolf){
//
//        int temp = -1;
//        // 현재 온곳이 빈곳이 아니면
//        if(infos[idx] != -1){
//            if(infos[idx] == 0){
//                temp = 0;
//                sheep++;
//            }else{
//                temp = 1;
//                wolf++;
//            }
//        }
//
//        // 늑대가 더많거나 같으면 종료
//        if(sheep <= wolf){
//            return;
//        }else{
//            answer = Math.max(answer,sheep);
//        }
//
//        // 다음 곳 가보기
//        for(int i = 0; i < graphs[idx].size(); i++){
//            int next = graphs[idx].get(i);
//            // 이상태로 가본적 있으면 패스
//            if(visited[next][sheep][wolf] == true){
//                continue;
//            }
//            // 현재 있던곳은 빈곳으로 처리
//            infos[idx] = -1;
//            // 이상태로 한번 온것으로 처리
//            visited[idx][sheep][wolf] = true;
//            dfs(next,sheep,wolf);
//            // 다시 되돌리기
//            infos[idx] = temp;
//            visited[idx][sheep][wolf] = false;
//        }
//    }
//
//    public static void main(String[] args) {
//        int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
//        int[][] edges = {{0,1}, {1,2}, {1,4}, {0,8}, {8,7}, {9,10}, {9,11}, {4,3}, {6,5},
//                {4,6}, {8,9}};
//
//        solution(info,edges);
//    }
//}
