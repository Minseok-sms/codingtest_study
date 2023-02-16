import java.util.ArrayList;

public class algo185 {
    /*
    프로그래머스 : 양과늑대

     */


    static int wolf_T = 0, sheep_T = 0;
    static ArrayList<Integer>[] array;
    static boolean[][][] visited;
    static int[] globalInfo;
    static int[] infos;
    static int max_sheep = 1;
    public static void dfs(int s, int w, int now){

        if(infos[now] == 0){
            s++;
        } else if(infos[now] == 1){
            w++;
        }


        if(w >= s) return;

        max_sheep = Math.max(max_sheep, s);


        for(int i=0;i<array[now].size();i++){
            int next = array[now].get(i);
            int temp = infos[now];
            if(visited[next][s][w] == false){
                infos[now] = -1;
                visited[now][s][w] = true;
                dfs(s, w, next);
                infos[now] = temp;
                visited[now][s][w] = false;
            }
        }

    }
    public static void main(String[] args) {
        int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
        int[][] edges = {{0,1}, {1,2}, {1,4}, {0,8}, {8,7}, {9,10}, {9, 11}, {4,3}, {6,5}, {4,6}, {8,9}};
        array = new ArrayList[info.length];
        for(int i = 0 ; i < info.length; i++){
            array[i] = new ArrayList<>();
        }
        for(int i = 0; i < edges.length; i++){
            array[edges[i][0]].add(edges[i][1]);
            array[edges[i][1]].add(edges[i][0]);
        }
        infos = info;


        visited = new boolean[info.length][info.length + 1][info.length + 1];
        dfs(0,0,0);
        System.out.println(max_sheep);
    }
}


