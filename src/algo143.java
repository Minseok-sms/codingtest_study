import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class algo143 {
    /*
    프로그래머스 : 양과늑대 (X)
     */

    static ArrayList<Integer>[] array;
    static int[] globalInfo;
    static boolean[] visited;
    static boolean[] sheepVisited;
    static int sheepCount = 1;
    static int wolfCount = 0;

//    static class Node{
//        int end;
//        int value;
//
//        Node(int end, int value){
//            this.end = end;
//            this.value = value;
//        }
//    }
    public static void stackBfs(int start){
        visited = new boolean[globalInfo.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while(!queue.isEmpty()){
            int temp = queue.poll();

            for(int i = 0; i < array[temp].size(); i++){
                if(visited[array[temp].get(i)] == true)
                    continue;
                if(globalInfo[array[temp].get(i)] >= 0){
                    globalInfo[array[temp].get(i)] += globalInfo[temp];
                }
                visited[array[temp].get(i)] = true;
                queue.add(array[temp].get(i));
            }

        }
    }
    public static void bfs(int[] info, int start){

        visited = new boolean[globalInfo.length];
        sheepVisited = new boolean[globalInfo.length];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        sheepVisited[start] = true;

        while(!queue.isEmpty()){
            int temp = queue.poll();

            for(int i = 0; i < array[temp].size(); i++){
                if(visited[array[temp].get(i)] == true )
                    continue;

                if(wolfCount >= sheepCount)
                    continue;

                visited[array[temp].get(i)] = true;
                // 양이면
                if(info[array[temp].get(i)] == 0){
                    queue.add(array[temp].get(i));

                    //양인데 새로운양이 지나갔으면
                    // 하나추가하고, 모든길 다시 초기화하고 거기서시작
                    if(sheepVisited[array[temp].get(i)] == false){
                        sheepCount++;
                        sheepVisited[array[temp].get(i)] = true;
                        visited = new boolean[globalInfo.length];
                        visited[array[temp].get(i)] = true;
                        wolfCount = 0;
                    }
                }
                // 늑대면 sheepCount > 늑대 카운트보다 더크면 지나갈수 있음
                else{
                    wolfCount++;
                    queue.add(array[temp].get(i));
                }
            }
        }
    }
    public static void solution(int[] info, int[][] edges) {
        array = new ArrayList[info.length];
        for(int i = 0; i < info.length; i++)
            array[i] = new ArrayList<>();

        for(int i = 0; i < edges.length; i++){
            int start = edges[i][0];
            int end = edges[i][1];

            array[start].add(end);
            array[end].add(start);
//            // 늑대면
//            if(info[end] == 1)
//                array[start].add(new Node(end, 1));
//            // 양일시
//            else
//                array[start].add(new Node(end, 0));
//
//            // 늑대면
//            if(info[start] == 1){
//                array[end].add(new Node(start, 1));
//                // 양일시
//            }else
//                array[end].add(new Node(start, 0));
        }

        globalInfo = new int[info.length];
        for(int i = 0 ; i < info.length; i++)
            globalInfo[i] = info[i];

        // 늑대의 수를 누적해가자.
        // globalInfo에 누적된 늑대수저장
        stackBfs(0);
        for(int i = 0 ; i < info.length; i++){
            if(info[i] == 0)
                globalInfo[i] = 0;
        }

        return ;
    }
    public static void main(String[] args) {
        int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
        int[][] edges = {{0,1}, {1,2}, {1,4}, {0,8}, {8,7}, {9,10}, {9, 11}, {4,3}, {6,5}, {4, 6}, {8,9}};
        solution(info, edges);
    }
}
