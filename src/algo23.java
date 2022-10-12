import java.io.*;
import java.util.*;

public class algo23 {

    static boolean[] visited;
    static ArrayList<Integer>[] arr;
    static int globalCount = 0;
    public static void dfs(ArrayList<Integer>[] arr, int start){
        visited[start] = true;

        for(int i = 0; i < arr[start].size(); i++){

            if(visited[arr[start].get(i)] == false){
                visited[arr[start].get(i)] = true;
                globalCount++;
                dfs(arr, arr[start].get(i));
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int comp = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());


        // 0 1 2 3 4 5 6 7
        arr = new ArrayList[comp + 1];
        for(int i = 0 ; i < comp + 1; i++)
            arr[i] = new ArrayList<>();

        for(int i = 0; i < E; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int first = Integer.parseInt(st.nextToken());
            int last = Integer.parseInt(st.nextToken());
            arr[first].add(last);
            arr[last].add(first);
        }
        visited = new boolean[comp + 1];

        dfs(arr, 1);
        System.out.println(globalCount);
    }
}
