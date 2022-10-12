import java.util.*;

public class algo54 {

    //프로그래머스 : 순위
    //플로이드 와샬 알고리즘
    static int[][] temp;
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    static boolean[] visited;
    static class Node{
        int start;
        int depth;
        Node(int start, int depth){
            this.start = start;
            this.depth = depth;
        }
    }
    public static void dfs(int lock,int[][] results, int start, int depth){
        visited[start] = true;

        if(depth >= 2){
            temp[lock][start] = 1;
            temp[start][lock] = -1;
        }
        for(int i = 0; i < results.length; i++){
            if(results[i][0] == start && visited[results[i][1]] == false){
                visited[results[i][1]] = true;
                dfs(lock, results, results[i][1], depth + 1);
                visited[results[i][1]] = false;
            }
        }
    }
    public static int solution(int n, int[][] results) {
        int answer = 0;
        temp = new int[n + 1][n + 1];

        for(int i = 0 ; i < results.length; i++){
            temp[results[i][0]][results[i][1]] = 1;
            temp[results[i][1]][results[i][0]] = -1;
        }
        // 4-> 3  3-> 2 ==> 4 -> 2
        for(int i = 1 ; i <= n; i++){
            for(int j = 1; j <= n; j++){
                for(int k = 1; k <= n; k++){
                    if(temp[i][k] == 1 && temp[k][j] == 1){
                        temp[i][j] = 1;
                        temp[j][i] = -1;
                    }
                    if(temp[i][k] == -1 && temp[k][j] == -1){
                        temp[i][j] = -1;
                        temp[j][i] = 1;
                    }
                }
            }

        }

//        for(int i = 1 ; i <= n; i++) {
//            visited = new boolean[n + 1];
//            dfs(i, results, i, 0);
//        }



        // 플로이드와샬 완성후 돌면서 -1,1 이 n-1번 잇으면 순위 확정
        for(int i = 1 ; i <= n; i++){
            int count = 0;
            for(int j = 1 ; j <= n; j++){
                if(temp[i][j] == 1 || temp[i][j] == -1)
                    count++;
            }
            if(count == n - 1)
                answer++;
        }

        System.out.println(answer);
        return answer;
    }
    public static void main(String[] args) {
        int[][] result = {{4,3}, {4,2}, {3,2}, {1,2}, {2,5}};
        System.out.println(solution(5, result));
    }
}


// 4 -> 3
// 4 -> 2