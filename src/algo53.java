import java.util.*;
public class algo53 {

    /*
    프로그래머스 : 가장먼노드
     */

    static boolean[] visited;
    static int min = Integer.MAX_VALUE;
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    static ArrayList<Integer> array = new ArrayList<>();
    static int globalCount = 0;
    static class Node{
        int start;
        int depth;
        Node(int start, int depth){
            this.start = start;
            this.depth = depth;
        }
    }
    public static void depthFind(int start){
        visited[start] = true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));
        while(!queue.isEmpty()){
            Node temp = queue.poll();
            int depthTemp = temp.depth;
            globalCount = Math.max(depthTemp, globalCount);
            for(int i = 0 ; i < map.get(temp.start).size(); i++){
                if(visited[map.get(temp.start).get(i)] == false) {
                    queue.add(new Node(map.get(temp.start).get(i), depthTemp + 1));
                    visited[map.get(temp.start).get(i)] = true;
                }
            }
        }
    }
    public static void nodeFind(int start){
        visited[start] = true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));
        while(!queue.isEmpty()){
            Node temp = queue.poll();
            int depthTemp = temp.depth;
            if(depthTemp == globalCount){
                array.add(start);
            }
            for(int i = 0 ; i < map.get(temp.start).size(); i++){
                if(visited[map.get(temp.start).get(i)] == false) {
                    queue.add(new Node(map.get(temp.start).get(i), depthTemp + 1));
                    visited[map.get(temp.start).get(i)] = true;
                }
            }
        }
    }
//    public static void dfs(int start, int count){
//
//        visited[start] = true;
//        if(count == globalCount){
//            array.add(start);
//            return ;
//        }
//        for(int i = 0 ; i < map.get(start).size(); i++){
//            if(visited[map.get(start).get(i)] == true)
//                continue;
//
//            visited[map.get(start).get(i)] = true;
//            dfs(map.get(start).get(i), count + 1);
//            visited[map.get(start).get(i)] = false;
//        }
//    }
    public static int solution(int n, int[][] edge) {
        int answer = 0;
        visited = new boolean[n + 1];
        // 해쉬맵에 해당되는 edge의 arraylist 추가
        for(int i = 1; i < n + 1; i++)
            map.put(i, new ArrayList<>());

        // 해쉬맵에 해당 edge의 간선 정보 삽입
        for(int i = 0; i < edge.length; i++) {
            map.get(edge[i][0]).add(edge[i][1]);
            map.get(edge[i][1]).add(edge[i][0]);
        }
        depthFind(1);
        visited = new boolean[n + 1];
        //dfs(1, 0);
        nodeFind(1);
        answer = array.size();
        System.out.println(answer);
        return answer;
    }
    public static void main(String[] args) {
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        solution(6,edge);
    }
}
