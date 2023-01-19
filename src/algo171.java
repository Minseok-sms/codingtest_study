import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class algo171 {
    /*
    백준 : 17780
    새로운 게임
     */
    static int N, K;
    static int[][] map;
    static ArrayList<Mal> array = new ArrayList<>();
    static Stack<Mal>[][] stack;

    // 우측, 왼쪽, 위쪽, 아래쪽
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Mal{
        int row;
        int col;
        int dir;
        int no;
        Mal(int row, int col, int dir, int no){
            this.row = row - 1;
            this.col = col - 1;
            this.dir = dir - 1;
            this.no = no;
        }
    }
    public static void move(int row, int col, int dir, int idx){


        int nextRow = row + dy[dir];
        int nextCol = col + dx[dir];

        //이동하려는 칸이 없는칸일경우 ?
        if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N){
            //이동하려는 말의 방향을 반대로 바까
           if(array.get(idx).dir == 0 || array.get(idx).dir == 1){
               array.get(idx).dir = array.get(idx).dir == 0 ? 1 : 0;
           }else
               array.get(idx).dir = array.get(idx).dir == 2 ? 3 : 2;

            // 바꾸고 다음진행칸으로 옮겨야하는데 그게 파란색일경우 or 흰색일경우 or 빨강일경우
            // 파란이일경우
            int nextnextRow = row + dy[array.get(idx).dir];
            int nextnextCol = col + dx[array.get(idx).dir];
            if(map[nextnextRow][nextnextCol] == 2)
                return ;
            else{
                // 나머지 색깔일경우 다시 접근해서 처리해라 그자리 기준으로
                move(row, col, array.get(idx).dir, idx);
            }
        }else if(map[nextRow][nextCol] == 2){
            // 파랑이를 접근했으면
            // 말의 이동 방향을 우선 바꿔라
            if(array.get(idx).dir == 0 || array.get(idx).dir == 1){
                array.get(idx).dir = array.get(idx).dir == 0 ? 1 : 0;
            }else
                array.get(idx).dir = array.get(idx).dir == 2 ? 3 : 2;

            // 이동하려는데 칸이 없거나, 또 파란색이면 가만히 둔다
            int nextnextRow = row + dy[array.get(idx).dir];
            int nextnextCol = col + dx[array.get(idx).dir];
            if(nextnextRow < 0 || nextnextRow >= N || nextnextCol < 0 || nextnextCol >= N)
                return ;
            else if(map[nextnextRow][nextnextCol] == 2)
                return ;
            else{
                //그 기준점으로 다시 접근해서 처리해라.
                move(row, col, array.get(idx).dir, idx);
            }
        }else if(map[nextRow][nextCol] == 0){
            // 이동하려는 칸이 흰색이다?
            // 해당스택을 모두 복사후 이동한다.
            // 넘어갈때 수정된 좌표로 넘겨야함.
            Stack<Mal> temp = new Stack<>();
            while(!stack[row][col].isEmpty()) {
                Mal Change = stack[row][col].pop();
                Change.row = nextRow;
                Change.col = nextCol;
                temp.push(Change);
            }

            // 쌓자
            while(!temp.isEmpty())
                stack[nextRow][nextCol].push(temp.pop());
        }else if(map[nextRow][nextCol] == 1){
            // 이동하려는 칸이 빨간색이다?
            // 전에칸을 위에꺼부터 꺼내서 넣으면댐
            while(!stack[row][col].isEmpty()){
                Mal Change = stack[row][col].pop();
                Change.row = nextRow;
                Change.col = nextCol;
                stack[nextRow][nextCol].push(Change);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        stack = new Stack[N][N];
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                stack[i][j] = new Stack<>();
            }
        }
        map = new int[N][N];


        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }


        // 말의 행,렬, 이동방향을 넣어라.
        for(int i = 0 ; i < K; i++){
            st = new StringTokenizer(br.readLine(), " ");
            array.add(new Mal(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i));
            int row = array.get(i).row;
            int col = array.get(i).col;
            stack[row][col].add(array.get(i));
        }

        int answer = 0;
        here:
        while(true){

            //만약 스택다확인했는데 말의갯수가 4이상 쌓여있으면 break;
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < N; j++){
                    if(stack[i][j].size() >= 4)
                        break here;
                }
            }

            // 말하나씩 돌자.
            for(int i = 0 ; i < K; i++){
                //만약 스택에서 젤 아래 있으면 움직이고 아니면 다음 말로 넘어가라
                int row = array.get(i).row;
                int col = array.get(i).col;
                int dir = array.get(i).dir;

                // 스택맨아래가 움직이려는 말과 같지않으면 못움직임
                if(stack[row][col].get(0).no != array.get(i).no)
                    continue;

                //같으면 움직여라
                move(row, col, dir, i);
            }
            answer++;
            if(answer > 1000)
                break;
        }

        if(answer <= 1000)
            System.out.println(answer);
        else
            System.out.println(-1);
    }
}
