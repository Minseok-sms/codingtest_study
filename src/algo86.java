import java.util.ArrayList;

public class algo86 {


    static ArrayList<Integer>[] array;
    static boolean[] visited;
    static int globalCount = 0;
    public static void dfs(int start){

        visited[start] = true;
        for(int i = 0; i < array[start].size(); i++){
            if(visited[array[start].get(i)] == true)
                continue;
            visited[array[start].get(i)] = true;
            dfs(array[start].get(i));
        }
    }
    public static int solution(int n, int[][] computers) {
        int answer = 0;
        array = new ArrayList[n];
        for(int i = 0 ; i < computers.length; i++) {
            array[i] = new ArrayList<>();
            for (int j = 0; j < computers[0].length; j++){
                if( i == j)
                    continue;
                if(computers[i][j] == 1)
                     array[i].add(j);
            }
        }
        visited = new boolean[n];
        for(int i = 0 ; i < n; i++){
            if(visited[i] == false) {
                dfs(i);
                answer++;
            }
        }
        System.out.println(answer);
        return answer;
    }
    public static void main(String[] args) {
        int[][] computers = {{1,1,0}, {1,1,1}, {0, 1, 1}};
        solution(3, computers);
    }
}

/*
class Solution {

    static boolean[] visited;
    public int dfs(int start, int[][] computers){
        if(visited[start] == false)
            visited[start] = true;
        else
            return 1;

        for(int i = 0; i < computers.length; i++) {
            if (computers[start][i] == 1 && visited[i] == false)
                dfs(i, computers);
        }
        return 0;
    }
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        int check = 0;
        for(int i = 0; i < n ; i++){
            check = dfs(i, computers);
            if(check == 0)
                answer++;
        }
        return answer;
    }
}

// 1 1 0
// 1 1 0
// 0 0 1

// 1 1 0
// 1 1 1
// 0 1 1
 */