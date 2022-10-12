import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class algo96 {
    /*
    백준 : 1967
    트리의 지름
     */

    static ArrayList<Node>[] array;
    static class Node{
        int end;
        int weight;
        Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }
    }
    static boolean[] visited;
    static ArrayList<Integer> maxLength;
    static int firstMaxNode = 0;
    static int firstMaxWeight = 0;

    public static void dfs(int start, int sum){

        if(firstMaxWeight <= sum){
            firstMaxWeight = sum;
            firstMaxNode = start;
        }
        visited[start] = true;


        for(int i = 0; i < array[start].size(); i++){
            if(visited[array[start].get(i).end] == true)
                continue ;

            visited[array[start].get(i).end] = true;
            dfs(array[start].get(i).end, sum + array[start].get(i).weight);
            visited[array[start].get(i).end] = false;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        array = new ArrayList[N + 1];
        // 1 2 .... .12

        for(int i = 0; i < N + 1; i++)
            array[i] = new ArrayList<>();

        for(int i = 0 ; i < N - 1; i++){

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            array[start].add(new Node(end, weight));
            array[end].add(new Node(start, weight));
        }
        visited = new boolean[N + 1];
        dfs(1, 0);

        visited = new boolean[N + 1];
        firstMaxWeight = 0;
        dfs(firstMaxNode, 0);

        System.out.println(firstMaxWeight);
    }
}
