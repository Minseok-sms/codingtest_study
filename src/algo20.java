import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;

public class algo20 {

    static boolean[] visited;
    static ArrayList<Integer> answer;
    public static void dfs(ArrayList<Integer>[] arr, int start){
        visited[start] = true;
        answer.add(start);
        for(int i = 0 ; i < arr[start].size(); i++){
            if(visited[arr[start].get(i)] == false)
                dfs(arr, arr[start].get(i));
        }
    }
    public static void bfs(ArrayList<Integer>[] arr, int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        answer.add(start);
        // 3 1 4 2 5
        //  2
        visited[start] = true;
        while(!queue.isEmpty()){
            int num = queue.poll();
            for(int i = 0; i < arr[num].size(); i++)
                if(visited[arr[num].get(i)] == false) {
                    visited[arr[num].get(i)] = true;
                    answer.add(arr[num].get(i));
                    queue.add(arr[num].get(i));
                }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N, M, V;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        ArrayList<Integer>[] arr = new ArrayList[N + 1];
        for(int i = 0 ; i < N + 1 ; i++)
            arr[i] = new ArrayList<>();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            arr[first].add(second);
            arr[second].add(first);
        }
        for(int i = 0; i < N + 1; i++){
            if(arr[i] != null)
                Collections.sort(arr[i]);
        }
        answer = new ArrayList<>();
        dfs(arr,V);

        for(int i = 0; i < answer.size(); i++) {
            if(i == answer.size() - 1)
                System.out.print(answer.get(i));
            else
                System.out.print(answer.get(i) + " ");
        }
        answer.clear();
        visited = new boolean[N + 1];

        bfs(arr, V);

        System.out.println();
        for(int i = 0; i < answer.size(); i++) {
            if(i == answer.size() - 1)
                System.out.print(answer.get(i));
            else
                System.out.print(answer.get(i) + " ");
        }
    }
}
