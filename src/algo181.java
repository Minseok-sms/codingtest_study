import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class algo181 {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static boolean[] visited2;
    static int MIN = Integer.MAX_VALUE;
    static HashMap<Integer, ArrayList<Node>> hashMap = new HashMap<Integer, ArrayList<Node>>();
    static int[] dx = {-1,0 , 1 , 0};
    static int[] dy = {0, 1, 0 , -1};
    static class Node{
        int row;
        int col;
        int count;
        Node(int row, int col, int count){
            this.row = row;
            this.col = col;
            this.count = count;
        }
    }

    // 숫자전환 메서드
    public static void changeInt(int row, int col, int set){

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(row, col, 0));
        visited[row][col] = true;
        // 숫자로 바꾸자
        map[row][col] = set;
        while(!queue.isEmpty()){
            Node temp = queue.poll();

            for(int i = 0; i < 4; i++){
                int nextRow = temp.row + dy[i];
                int nextCol = temp.col + dx[i];
                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N){
                    continue;
                }
                if(visited[nextRow][nextCol] == true || map[nextRow][nextCol] == 0)
                    continue;

                visited[nextRow][nextCol] = true;
                map[nextRow][nextCol] = set;
                queue.add(new Node(nextRow, nextCol, 0));

            }
        }
    }
    public static void bfs(int start, int end){
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(start, end, 0));
        int nowNum = map[start][end];
        visited[start][end] = true;


        while(!queue.isEmpty()){
            Node temp = queue.poll();

            for(int i = 0; i < 4; i++){
                int nextRow = temp.row + dy[i];
                int nextCol = temp.col + dx[i];
                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N){
                    continue;
                }
                // 이미 방문햇거나 자기 섬인경우
                if(visited[nextRow][nextCol] == true || map[nextRow][nextCol] == nowNum)
                    continue;

                visited[nextRow][nextCol] = true;
                if(map[nextRow][nextCol] == 0){
                    // 바다면 큐에넣자
                    queue.add(new Node(nextRow, nextCol, temp.count + 1));
                }else{
                    MIN = Math.min(MIN, temp.count);
                }


            }


        }

    }
    public static void dfs(int start,String str ,int count, int depth, int cnt){

        if(count == depth){
            visited = new boolean[N][N];
            bfs(str.charAt(0) - '0', str.charAt(1) - '0');
            return ;
        }
        for(int i = start ; i <= cnt; i++){
            if(visited2[i] == true)
                continue;

            visited2[i] = true;
            dfs(i, str + i, count +1, depth, cnt);
            visited2[i] = false;

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        // map 정의
        ArrayList<Node> array = new ArrayList<>();
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1 )
                     array.add(new Node(i, j, 0 ));
            }
        }

        // 일단 섬끼리 숫자를 바꾸자.
        visited = new boolean[N][N];
        int cnt = 1;
        for(int i = 0 ; i < array.size(); i++){
            int row = array.get(i).row;
            int col = array.get(i).col;
            if(visited[row][col] == true)
                continue;

            changeInt(row, col, cnt);
            hashMap.put(cnt, new ArrayList<>());
            cnt++;
        }

        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                if(map[i][j] == 0)
                    continue;
                hashMap.get(map[i][j]).add(new Node(i, j ,0));
            }
        }
        // 1 2 3 4
        for(int i : hashMap.keySet()){
            for(int j = 0; j < hashMap.get(i).size(); j++){
                visited = new boolean[N][N];
                bfs(hashMap.get(i).get(j).row, hashMap.get(i).get(j).col);
            }
        }
        System.out.println(MIN);
    }
}
