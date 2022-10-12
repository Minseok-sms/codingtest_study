import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class algo71 {
    /*
    프로그래머스 : 섬연결하기
    크루스칼 알고리즘
    다시확인
     */
    public static int findParent(int[] parent, int node) {
        if (parent[node] == node)
            return node;
        return findParent(parent, parent[node]);
    }
    public static void union(int[] parent, int node1, int node2) {
        int p1 = findParent(parent, node1);
        int p2 = findParent(parent, node2);

        if (p1 < p2)
            parent[p2] = p1;
        else
            parent[p1] = p2;
    }
    // 0 1 2 3
    // 0 0 2 0
    public static int solution(int n, int[][] costs) {
        int answer = 0;
        int[] parent = new int[Math.max(n, costs.length)];

        for(int i = 0 ; i < parent.length; i++)
            parent[i] = i;
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        for(int i = 0; i < costs.length; i++){
            if(findParent(parent, costs[i][0]) != findParent(parent, costs[i][1])){
                answer += costs[i][2];
                union(parent, costs[i][0], costs[i][1]);
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        int[][] costs = {{0,1,1}, {0,2,2},{1,2,5}, {1,3,1}, {2,3,8}};
        solution(4, costs);
    }
}
