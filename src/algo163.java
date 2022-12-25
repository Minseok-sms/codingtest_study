import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class algo163 {
    /*
    백준 : 18428
    감시피하기
     */
    static int[][] map;
    static int N;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static ArrayList<Node> array = new ArrayList<>();
    static boolean check = false;
    static class Node{
        int row;
        int col;
        Node(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    public static void dfs(int row, int col, int dir, int count){


        int nextRow = dy[dir] + row;
        int nextCol = dx[dir] + col;
        if(nextRow >= N || nextRow < 0 || nextCol >=N || nextCol < 0)
            return ;

        // 가는곳에 학생이있거나 obstacle이 있는경우
        if(map[nextRow][nextCol] == 1 || visited[nextRow][nextCol] == true){
            // 선생님 방향에 학생이 발견되면 true로 바꾸고 끝냄
            if(map[nextRow][nextCol] == 1)
                check = true;
            return ;
        }

        dfs(nextRow, nextCol, dir , count + 1);

    }
    public static void obstacleSet(int row, int col, int count, int depth){
        // 3개가 설치가 다됐으면
        if(count == depth){
            for(int i = 0; i < array.size(); i++){
                int tRow = array.get(i).row;
                int tCol = array.get(i).col;

                dfs(tRow, tCol, 0, 0);
                dfs(tRow, tCol, 1, 0);
                dfs(tRow, tCol, 2 , 0);
                dfs(tRow, tCol, 3, 0 ) ;

                //학생이 발견되었네?
                if(check == true)
                    break;
            }
            //만약 3개설치다하고 선생님 다돌았는데 check = false다
            if(check == false){
                System.out.println("YES");
                System.exit(0);
            }
            check = false;
            return ;
        }

        // obstacle 3개설치하는 과정, 이때 선생님자리, 학생자리빼고 설치
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                if(visited[i][j] == true || map[i][j] == 1 || map[i][j] == 2)
                    continue;

                visited[i][j] = true;
                obstacleSet(i, j, count + 1, depth);
                visited[i][j] = false;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());


        map = new int[N][N];
        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N; j++){
                char c = st.nextToken().charAt(0);
                // 빈칸 0
                if(c == 'X')
                    map[i][j] = 0;
                // 학생 1
                else if(c  == 'S')
                    map[i][j] = 1;
                // 선생님 2
                else {
                    map[i][j] = 2;
                    //선생님을 어레이에 넣자.ㄷ
                    array.add(new Node(i,j));
                }
            }
        }
        // 장애물 3개를 겹치지 않게 놓차.
        visited = new boolean[N][N];
        obstacleSet(0,0, 0, 3);

        //다돌았는데 경우의 수가 없다
        System.out.println("NO");



    }
}
