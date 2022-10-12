import java.util.Arrays;

public class algo73 {

    static boolean[] visited;
    static boolean[] check;
    static int MIN = Integer.MAX_VALUE;
    public static void dfs(int call, int n, int w, int count){
        boolean same = Arrays.equals(visited, check);
        if(same == true){
            MIN = Math.min(MIN, count);
            return ;
        }

        for(int i = call; i <= n; i++){
            if(visited[i] == true)
                continue;

            visited[i] = true;
            int start = i - w;
            int end = i + w;
            if(start < 1)
                start = 1;
            if(end > n)
                end = n;
            for(int j = start; j <= end ; j++)
                visited[j] = true;

            dfs(call, n, w, count + 1);
            visited[i] = false;
            for(int j = start; j <= end ; j++)
                visited[j] = false;
        }
    }
    public static int solution(int n, int[] stations, int w) {
        int answer = 0;
        visited = new boolean[n + 1];
        check = new boolean[n + 1];
        for(int i = 0 ; i <= n ; i++)
            check[i] = true;
        visited[0] = true;

        for(int i = 0 ; i < stations.length; i++){
            int station = stations[i];
            int start = station - w;
            int end = station + w;
            if(start < 1)
                start = 0;
            if(end > n)
                end = n;
            for(int j = start; j <= end ; j++)
                visited[j] = true;
        }

        dfs(0, n,w, 0);
        answer = MIN;
        System.out.println(answer);
        return answer;
    }
    public static void main(String[] args) {
       int[] stations = {4,11};
       solution(11, stations, 1);
    }
}
