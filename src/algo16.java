
// DFS 네트워크
public class algo16 {

    static boolean[] visited;
    public static int dfs(int start, int[][] computers){
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
    public static int solution(int n, int[][] computers) {
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
    public static void main(String[] args) {
        int n = 3;
        int[][] computers = new int[][]{{1,1,0}, {1,1,1}, {0,1,1}};
        int ans = 0;
        ans = solution(n, computers);
        System.out.println("ans = " + ans);
    }
}
