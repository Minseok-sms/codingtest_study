
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;
import java.io.*;



public class algo165
{
    // 특정집의 좌표로 기준으로 K늘려가면서 발생비용이 이득보다 더 높을시에stop; 최대의 집 저장
    static int MAX = 0;
    static int[][] map;
    static int N,M;
    static ArrayList<Node> array;
    static boolean[][] visited;
    static int[] dx = {-1,0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
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
    public static int bfs(int row, int col, int K){
        Queue<Node> queue = new LinkedList<>();
        visited = new boolean[N][N];
        visited[row][col] = true;
        queue.add(new Node(row, col, 0));

        int houseCount = 0;
        // 처음집 포함
        if(map[row][col] == 1)
            houseCount = 1;
        else
            houseCount = 0;

        while(!queue.isEmpty()){
            Node temp = queue.poll();

            //만약 칸이 K만큼 돌아버리면 continue;
            if(temp.count == K - 1)
                continue;

            for(int i = 0; i < 4; i++){
                int nextRow = temp.row + dy[i];
                int nextCol = temp.col + dx[i];

                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N)
                    continue;
                if(visited[nextRow][nextCol] == true)
                    continue;

                //만약 접근하는중에 집발견하면 +1;
                if(map[nextRow][nextCol] == 1){
                    houseCount++;
                }
                visited[nextRow][nextCol] = true;
                queue.add(new Node(nextRow, nextCol, temp.count + 1));
            }
        }

        // 전체집 * 비용 - 설치비용(K * K + (K - 1) * (K - 1))
        int check = M * houseCount - ( K * K + (K - 1) * (K - 1) );
        //손해를 보지않음
        if(check >= 0){
            MAX = Math.max(houseCount, MAX);
            return 1;
        }
        return -1;

    }
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            array = new ArrayList<>();
            MAX = 0;

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1)
                        array.add(new Node(i,j, 0));
                }
            }

            // 손님의 좌표값 array 에 저장완료
            // 손님당 좌표로 k값늘려가면서 bfs돌리면서 집만나면 비용 ++; queue가 empty됏을때 다돌았다 그때 계산을해서 
            // 만약 이득이더크면 MAX에 집수저장.
            //손해면 다음 손님좌표 시작, k = 1;

//            for(int i= 0; i < array.size(); i++){
//                int sRow = array.get(i).row;
//                int sCol  = array.get(i).col;
//                for(int k = 1; k <= 20; k++){
//                    int check = bfs(sRow, sCol, k);
//                    if(check == -1)
//                        break;
//                }
//            }
            for(int i= 0; i < N; i++){
                for(int j = 0 ; j < N; j++) {
                    int sRow = i;
                    int sCol = j;
                    for (int k = 1; k <= 20; k++) {
                        int check = bfs(sRow, sCol, k);
                    }
                }
            }


            sb.append("#" + test_case + " " + MAX + "\n");
        }
        System.out.println(sb);
    }
}