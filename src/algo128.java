import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class algo128 {
    /*
       백준 : 1707
       이분그래프
     */

    static ArrayList<Integer>[] array;
    static boolean[] visited;
    static int[] colorSelect;
    static ArrayList<Node> edge;
    static String[] result;
    static int resultCount = 0;


    static class Node{
        int start;
        int end;
        Node(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public static void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int temp = queue.poll();

            for(int i = 0; i < array[temp].size(); i++){
                if(visited[array[temp].get(i)] == true)
                    continue;

                // 자신과는 다른 색깔을 넣자
                if(colorSelect[temp] == -1){
                    colorSelect[array[temp].get(i)] = 1;
                }else
                    colorSelect[array[temp].get(i)] = -1;


                visited[array[temp].get(i)] = true;
                queue.add(array[temp].get(i));
            }

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        result = new String[T];

        for(int i = 0 ; i < T; i++){

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            array = new ArrayList[V + 1];
            colorSelect = new int[V + 1];
            visited = new boolean[V + 1];
            edge = new ArrayList<>();

            for(int j = 0 ; j < V + 1; j++)
                array[j] = new ArrayList<>();

            // 정점 삽입
            for(int j = 0 ; j < E; j++){
                st = new StringTokenizer(br.readLine(), " ");
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                array[start].add(end);
                array[end].add(start);
                edge.add(new Node(start, end));
            }

            // 서로이어진 정점은 다른색으로 칠하자
            // 이때 이어진 그래프가 안나올수 있으므로 정점을 다체크하면서 넣자
            for(int j = 0 ; j < edge.size(); j++){
                int startIdx = edge.get(j).start;
                if(visited[startIdx] == true)
                    continue;
                bfs(startIdx);
            }
            // 색칠완료


            // 정점끼리 연결된 간선돌면서 연결된 정점이 같은색이면 NO, 싹다다르면 YES
            boolean check = false;
            for(int j = 0 ; j < E; j++){
                Node temp = edge.get(j);
                int start1 = temp.start;
                int end1 = temp.end;

                if(colorSelect[start1] == colorSelect[end1]) {
                    check = true;
                    break;
                }
            }

            if(check == true)
                result[resultCount++] = "NO";
            else
                result[resultCount++] = "YES";
        }

        for(String str : result)
            System.out.println(str);
    }

}
