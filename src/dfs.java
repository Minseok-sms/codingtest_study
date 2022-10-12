import java.util.Scanner;

public class dfs {

    static int vertex;
    static int edge;
    static int[][] map;
    static boolean[] visited;

    public static void dfs(int start){
        visited[start] = true;
        System.out.println(start + " ");

        for(int i = 1 ; i < vertex + 1 ; i++){
            if(map[start][i] == 1 && visited[i] == false){
                dfs(i);
            }
        }
        System.out.println("start1 = " + start);

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        vertex = sc.nextInt();
        edge = sc.nextInt();
        map = new int[vertex + 1][vertex + 1];
        visited = new boolean[vertex + 1];

        for(int i = 1; i <= edge; i++){
            int start = sc.nextInt();
            int end = sc.nextInt();
            map[start][end] = 1;
            map[end][start] = 1;
        }
        dfs(1);
    }
}
