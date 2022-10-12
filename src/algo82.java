import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class algo82 {


    // 백준 17144번

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static int[][] tempmap;
    static int row, col, T;
    public static int check(int row1, int col1,int count){
        int result = 0;
        for(int i = 0 ; i < 4 ; i++){
            int nextRow = row1 + dy[i];
            int nextCol = col1 + dx[i];

            if(nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col)
                continue;
            if(tempmap[nextRow][nextCol] == -1)
                continue;

            int plus = count / 5;
            tempmap[nextRow][nextCol] += plus;
            result++;
        }
        return result;
    }
    static class Node{
        int x;
        int y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        tempmap = new int[row][col];

        ArrayList<Node> array = new ArrayList<>();


        for(int i = 0 ; i < row; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1)
                    array.add(new Node(j, i));

            }
        }

        for(int k = 0 ; k < T; k++){
           for(int i = 0 ; i < row ; i++)
               System.arraycopy(map[i], 0, tempmap[i], 0, map[i].length);


            for(int i = 0 ; i < row; i++){
                for(int j = 0 ; j < col; j++){
                    if(map[i][j] > 0) {
                        tempmap[i][j] -= (map[i][j] / 5) * check(i, j, map[i][j]);
                    }
                }
            }

            // 상부 공기청정기 순환
            int filterRow = array.get(0).y;

            // 상부 아래방향
            for(int i = filterRow - 1; i > 0 ; i--)
                tempmap[i][0] = map[i - 1][0];
            // 상부 왼졲
            for(int i = 0; i < col - 1; i++)
                tempmap[0][i] = tempmap[0][i + 1];

            // 상부 위쪽
            for(int i = 0; i < filterRow; i++)
                tempmap[i][col - 1] = tempmap[i + 1][col - 1];

            // 상부 오른쪽
            for(int i = col - 1; i > 1; i--)
                tempmap[filterRow][i] = tempmap[filterRow][i - 1];
            tempmap[filterRow][1] = 0;


            //하부 공기청정기
            filterRow = array.get(1).y;

            // 하부 필터 우측

            // 하부 위로
            for(int i = filterRow + 1; i < row - 1; i++)
                tempmap[i][0] = tempmap[i + 1][0];

            // 하부 왼쪽
            for(int i = 0; i < col - 1; i++)
                tempmap[row - 1][i] = tempmap[row - 1][i + 1];

            // 하부 아래
            for(int i = row - 1; i > filterRow; i--)
                tempmap[i][col - 1] = tempmap[i - 1][col - 1];

            // 하부 우측
            for(int i = col - 1 ; i > 1; i--)
                tempmap[filterRow][i] = tempmap[filterRow][i - 1];

            tempmap[filterRow][1] = 0;

            for(int i = 0 ; i < row ; i++)
                System.arraycopy(tempmap[i], 0, map[i], 0, tempmap[i].length);
        }

        map[array.get(0).y][array.get(0).x] = -1;
        map[array.get(1).y][array.get(1).x] = -1;

        // 갯수 카운트
        int answer = 0;
        for(int i = 0 ; i < row ; i++){
            for(int j = 0 ; j < col; j++) {
                if(map[i][j] == -1)
                    continue;
                answer += map[i][j];
            }
        }
        System.out.println(answer);
    }
}
