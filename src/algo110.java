import java.util.ArrayList;

public class algo110 {

    /*
    프로그래머스 : 전력망을 둘로 나누기
     */
    static ArrayList<Integer>[] array;
    static boolean[] visited;
    static int nodeCount = 1;
    static int min = Integer.MAX_VALUE;
    public static void dfs(int start, int banStart, int banEnd){

        visited[start] = true;

        for(int i = 0; i < array[start].size(); i++){
            if(visited[array[start].get(i)] == true)
                continue;

            if(start == banStart && array[start].get(i) == banEnd)
                continue;
            if(start == banEnd && array[start].get(i) == banStart)
                continue;

            nodeCount++;
            visited[array[start].get(i)] = true;
            dfs(array[start].get(i), banStart, banEnd);
        }
    }
    public static int solution(int n, int[][] wires) {
        int answer = -1;
        ArrayList<Integer> temp = new ArrayList<>();
        array = new ArrayList[n + 1];

        for(int i = 1 ; i < n + 1; i++)
            array[i] = new ArrayList<>();

        for(int i = 0 ; i < wires.length; i++){
            int start = wires[i][0];
            int end = wires[i][1];
            array[start].add(end);
            array[end].add(start);
        }
        for(int i = 0 ; i < wires.length ; i++){
            int banStart = wires[i][0];
            int banEnd = wires[i][1];

            visited = new boolean[n + 1];
            for(int j = 1 ; j <= n; j++ ){
                nodeCount = 1;
                if(visited[j] == false){
                    dfs(j, banStart, banEnd);
                }else
                    continue;
                temp.add(nodeCount);
            }
            int num1 = temp.get(0);
            int num2 = temp.get(1);
            temp.clear();
            min = Math.min(Math.abs(num1 - num2), min);
        }
        answer = min;
        System.out.println(answer);
        return answer;
    }
    public static void main(String[] args) {
        int[][] wires = {{1,3}, {2,3}, {3,4}, {4,5}, {4,6}, {4,7}, {7,8}, {7,9}};
        solution(9, wires);
    }
}
