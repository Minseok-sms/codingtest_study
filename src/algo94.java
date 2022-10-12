import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class algo94 {

    /*
    백준 : 1012 유기농배추
     */

    static int[][] map;
    static ArrayList<Integer> answer = new ArrayList<>();
    static ArrayList<Node> node;

    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Node{
        int y;
        int x;
        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    public static void bfs(int y, int x,int N, int M){

        Queue<Node> queue =  new LinkedList<>();
        queue.add(new Node(y, x));
        visited[y][x] = true;

        while(!queue.isEmpty()){
            Node node = queue.poll();

            for(int i = 0; i < 4; i++){
                int nextCol = node.x + dx[i];
                int nextRow = node.y + dy[i];

                if(nextCol < 0 || nextCol >= M || nextRow < 0 || nextRow >= N)
                    continue;
                if(visited[nextRow][nextCol] == true || map[nextRow][nextCol] == 0)
                    continue;

                visited[nextRow][nextCol] = true;
                queue.add(new Node(nextRow, nextCol));
            }

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < testCase; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            visited = new boolean[N][M];
            node = new ArrayList<>();

            for(int j = 0; j < count ; j++){
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = 1;
                node.add(new Node(y, x));
            }
            int answerCount = 0;
            for(int j = 0; j < node.size(); j++){
                int y = node.get(j).y;
                int x = node.get(j).x;
                if(visited[y][x] == true)
                    continue;
                bfs(y, x, N, M);
                answerCount++;
            }
            answer.add(answerCount);
        }
        for(int n : answer)
            System.out.println(n);
    }

}
