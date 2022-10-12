public class algo87 {

    /*
    프로그래머스 : 피로도

     */
    static boolean[] visited;
    static int num;
    static int MAX = 0;
    public static void dfs(int start, int count, int k, int[][] dg){

        MAX = Math.max(MAX, count);

        for(int i = 0; i < num; i++){
            if(visited[i] == true)
                continue;

            if(dg[i][0] <= k){
                visited[i] = true;
                dfs(i, count + 1, k - dg[i][1], dg);
                visited[i] = false;
            }

        }
    }
    public static int solution(int k, int[][] dungeons) {
        int answer = -1;
        visited = new boolean[dungeons.length];
        num = dungeons.length;
        dfs(0, 0, k, dungeons);

        System.out.println(MAX);
        answer = MAX;
        return answer;
    }

    public static void main(String[] args) {
        int[][] dg = {{80,20}, {50,40}, {30,10}};
        solution(80, dg);
    }
}
