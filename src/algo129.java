import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class algo129 {

    /*
    백준 : 1240
    노드사이의 거리
     */
    static ArrayList<Node>[] array;
    static ArrayList<Node> check = new ArrayList<>();
    static int[] answer;
    static int globalCount = 0;
    static boolean[] visited;

    static class Node{
        int end;
        int weight;

        Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }
    }
    public static void dfs(int start, int target, int weight){

        visited[start] = true;
        if(start == target){
            answer[globalCount++] = weight;
            return ;
        }
        for(int i = 0; i < array[start].size(); i++){
            if(visited[array[start].get(i).end] == true)
                continue;

            visited[array[start].get(i).end] = true;
            dfs(array[start].get(i).end, target, weight + array[start].get(i).weight);

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        array = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        answer = new int[M];

        for(int i = 0; i < N + 1; i++)
            array[i] = new ArrayList<>();

        for(int i = 0 ; i < N - 1; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            array[start].add(new Node(end, weight));
            array[end].add(new Node(start, weight));
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            visited = new boolean[N + 1];
            dfs(start, end , 0);
        }
        for(int i = 0 ; i < M; i++)
            System.out.println(answer[i]);
    }
}
