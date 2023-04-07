import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class algo193 {
    /*
    연결된 노드 엣지만나오고, 루트노드구해서 각 노드들의 층수를 구하는문제.

     */

    static ArrayList<Integer>[] array;
    static boolean[] visited;
    static int maxDepth = 0;
    static int node = 0;
    static HashMap<Integer, Integer> map = new HashMap<>();
    static class Node{
        int end;
        int depth;
        Node(int end, int depth){
            this.end = end;
            this.depth = depth;
        }
    }
    public static void bfs(int start){
        maxDepth = 0;
        Queue<Node> queue = new LinkedList<>();
        visited[start] = true;
        map.put(start, 1);
        for(int i = 0 ; i < array[start].size(); i++){
            queue.add(new Node(array[start].get(i), 1));
            map.put(array[start].get(i), 2);
            visited[array[start].get(i)] = true;
        }
        while(!queue.isEmpty()){
            Node temp = queue.poll();
            maxDepth = Math.max(temp.depth, maxDepth);
            for(int i = 0 ; i < array[temp.end].size(); i++){
                if(visited[array[temp.end].get(i)] == true)
                    continue;

                visited[array[temp.end].get(i)] = true;
                map.put(array[temp.end].get(i), temp.depth + 1 + 1);
                queue.add(new Node(array[temp.end].get(i), temp.depth + 1));
            }
        }

    }
    public static void main(String[] args) {
        int[][] edges = {{0,4}, {4,5}, {2, 1}, {6, 1}, {4,3}, {1, 3}};
        int v = 7;
        array = new ArrayList[v];
        for(int i = 0 ; i < v; i++) {
            array[i] = new ArrayList<>();
            map.put(i, 0);
        }

        for(int i = 0 ; i < edges.length; i++){
            int start = edges[i][0];
            int end = edges[i][1];
            array[start].add(end);
            array[end].add(start);
        }

        int rootNode = 0;
        int copy = Integer.MAX_VALUE;
        //각 노드 돌면서 가장 짧은게 lootNode
        for(int i = 0 ; i < v; i++){
            visited = new boolean[v];
            bfs(i);
            int copycopy = copy;
            copy = Math.min(copy, maxDepth);
            if(copy != copycopy) {
                rootNode = i;
            }
        }

        //System.out.println(rootNode);
        for(int i = 0 ; i < v; i++) {
            map.put(i, 0);
        }
        visited = new boolean[v];
        bfs(rootNode);

        for(int i = 0; i < v; i++)
            System.out.println(map.get(i));

    }
}
