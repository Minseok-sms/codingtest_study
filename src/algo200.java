//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.HashMap;
//import java.util.StringTokenizer;
//
//public class algo200 {
//
//
//    static HashMap<Integer, Node> map = new HashMap<>();
//    static Node shark;
//
//    // 위 -> 왼위 -> 왼 -> 왼아 -> 아래 -> 아오 -> 오른쪽 -> 우측위
//    // 0 1 2 3 4 5 6 7
//    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
//    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
//    static class Node {
//        int num;
//        int row;
//        int col;
//        int dir;
//
//        Node(int num, int row, int col, int dir) {
//            this.num = num;
//            this.row = row;
//            this.col = col;
//            this.dir = dir;
//        }
//    }
//
//    // 샤크 = -1,  빈칸 = 0;
//    static int[][] array;
//
//    //상어가 갈수 있는 방향중, 제일 큰것, 못가면 -1,
//    public static int findMax(){
//
//        // 한번이라도 먹을수 있는 물고기 체크
//        int cnt = 0;
//        int max = 0;
//        while(true) {
//            int nextRow = shark.row + dy[shark.dir];
//            int nextCol = shark.col + dx[shark.dir];
//
//            //  해당 영역밖으로 넘어가면 못간다.
//            if (nextRow < 0 || nextRow >= 4 || nextCol < 0 || nextCol >= 4)
//                break;
//            // 빈칸이면 패스
//            if(array[nextRow][nextCol] == 0)
//                continue;
//
//            max = Math.max(array[nextRow][nextCol], max);
//            cnt++;
//            shark.row = nextRow;
//            shark.col = nextCol;
//        }
//        if(cnt == 0)
//            return -1;
//        else
//            return max;
//    }
//    public static int findDir(int num){
//        Node temp = map.get(num);
//        for(int i = 0 ; i < 8; i++){
//            int goDir = (temp.dir + i) % 8;
//            int nextRow = temp.row + dy[goDir];
//            int nextCol = temp.col + dx[goDir];
//
//            // 범위 밖에가버리면 continue;
//            if(nextRow < 0 || nextRow >= 4 || nextCol < 0 || nextCol >= 4)
//                continue;
//
//            // 상어가있는 곳이면 continue;
//            if(array[nextRow][nextCol] == -1)
//                continue;
//
//            // 이동가능한 방향이면 return goDir;
//            return goDir;
//        }
//
//        // 못가는곳이면 -1;
//        return -1;
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        array = new int[4][4];
//
//        for(int i = 0; i < 4; i++){
//            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//            for(int j = 0 ; j < 4; j++){
//                int num = Integer.parseInt(st.nextToken());
//                int dir = Integer.parseInt(st.nextToken()) - 1;
//                map.put(num, new Node(num, i, j, dir));
//                array[i][j] = num;
//            }
//        }
//        boolean check = true;
//        int answer = 0;
//        while(true){
//            // 현재 상어자리에서 물고기를 먹자.
//            // 처음일경우 0,0을 먹고, 해당자리 -1로바꾸고, 상어 방향 물고기방향으로
//            if(check == true){
//                shark = new Node(-1, 0, 0, map.get(array[0][0]).dir);
//                map.remove(array[0][0]);
//                array[0][0] = -1;
//                check = false;
//                answer += array[0][0];
//
//            }else{
//                // 상어가 먹는 턴이 처음이 아니면.
//                // 해당방향 전체중 제일 큰곳을 찾고, 값을 + 하고, 그 큰곳의 좌표를 샤크 좌표 및 dir바꾸고
//                // 해당자리 -1, 해당 물고기 map에서 삭제.
//                // 만약 상어가 움직일 곳이 없으면 break;
//
//                // 샤크가 떠난곳은 array = 0;
//                array[shark.row][shark.col] = 0;
//
//                int temp = findMax();
//                if(temp == -1)
//                    break;
//                else{
//                    // 최대의 물고기를 먹자.
//                    answer += temp;
//                    shark = new Node(-1, map.get(temp).row, map.get(temp).col,
//                            map.get(temp).dir);
//                    array[map.get(temp).row][map.get(temp).col] = -1;
//                    map.remove(temp);
//                }
//            }
//
//
//            // 물고기 순회하자. 1부터 16까지.
//            for(int i = 1; i <= 16; i++){
//                // 물고기가 맵에 없으면 continue;
//                if(!map.containsKey(i))
//                    continue;
//
//                // 빈칸과 다른물고기 있는칸 찾자. (만약 다른물고기가 있는칸이면 둘이 swap)
//                // 해당칸이 상어나, 범위 이상이면 불가
//                int dir = findDir(i);
//
//                // 이동하려는 칸이 반킨이냐 아니면 다른물고가 있는가?
//                int nextRow = map.get(i).row + dy[dir];
//                int nextCol = map.get(i).col + dx[dir];
//                if(array[nextRow][nextCol] == 0){
//                    // 빈칸이면 해당 좌표 및 dir방향 수정
//                    // 이전칸 array = 0; 옮긴칸 array = i;
//                    array[map.get(i).row][map.get(i).col] = 0;
//                    map.get(i).row = nextRow;
//                    map.get(i).col = nextCol;
//                    map.get(i).dir = dir;
//                    array[nextRow][nextCol] = i;
//                }else{
//                    // 물고기가 있는 칸이면 서로 교환한다.
//                    // 좌표바꾸고, array값바꾸고, dir값 바꿔.
//                    int num1 = i;
//                    int num2 = array[nextRow][nextCol];
//
//                    array[nextRow][nextCol] = i;
//                    array[map.get(i).row][map.get(i).col] = num2;
//
//                    map.get(num2).row = map.get(i).row;
//                    map.get(num2).col = map.get(i).col;
//
//                    map.get(i).row = nextRow;
//                    map.get(i).col = nextCol;
//                    map.get(i).dir = dir;
//
//                }
//
//            }
//
//        }
//        System.out.println(answer);
//    }
//}
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.HashMap;
//import java.util.StringTokenizer;
//
//public class algo200 {
//
//
//    static HashMap<Integer, Node> map = new HashMap<>();
//    static Node shark;
//    static int answer = 0;
//    // 위 -> 왼위 -> 왼 -> 왼아 -> 아래 -> 아오 -> 오른쪽 -> 우측위
//    // 0 1 2 3 4 5 6 7
//    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
//    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
//    static class Node {
//        int num;
//        int row;
//        int col;
//        int dir;
//
//        Node(int num, int row, int col, int dir) {
//            this.num = num;
//            this.row = row;
//            this.col = col;
//            this.dir = dir;
//        }
//    }
//
//    // 샤크 = -1,  빈칸 = 0;
//    static int[][] array;
//
//    //상어가 갈수 있는 방향중, 제일 큰것, 못가면 -1,
//    public static int findMax(){
//
//        // 한번이라도 먹을수 있는 물고기 체크
//        int cnt = 0;
//        int max = 0;
//        while(true) {
//            int nextRow = shark.row + dy[shark.dir];
//            int nextCol = shark.col + dx[shark.dir];
//
//            //  해당 영역밖으로 넘어가면 못간다.
//            if (nextRow < 0 || nextRow >= 4 || nextCol < 0 || nextCol >= 4)
//                break;
//            // 빈칸이면 패스
//            if(array[nextRow][nextCol] == 0)
//                continue;
//
//            max = Math.max(array[nextRow][nextCol], max);
//            cnt++;
//            shark.row = nextRow;
//            shark.col = nextCol;
//        }
//        if(cnt == 0)
//            return -1;
//        else
//            return max;
//    }
//    public static int findDir(int num){
//        Node temp = map.get(num);
//        for(int i = 0 ; i < 8; i++){
//            int goDir = (temp.dir + i) % 8;
//            int nextRow = temp.row + dy[goDir];
//            int nextCol = temp.col + dx[goDir];
//
//            // 범위 밖에가버리면 continue;
//            if(nextRow < 0 || nextRow >= 4 || nextCol < 0 || nextCol >= 4)
//                continue;
//
//            // 상어가있는 곳이면 continue;
//            if(array[nextRow][nextCol] == -1)
//                continue;
//
//            // 이동가능한 방향이면 return goDir;
//            return goDir;
//        }
//
//        // 못가는곳이면 -1;
//        return -1;
//    }
//    public static void moveFish(){
//        for(int i = 1; i <= 16; i++){
//            // 물고기가 맵에 없으면 continue;
//            if(!map.containsKey(i))
//                continue;
//
//            // 빈칸과 다른물고기 있는칸 찾자. (만약 다른물고기가 있는칸이면 둘이 swap)
//            // 해당칸이 상어나, 범위 이상이면 불가
//            int dir = findDir(i);
//
//            // 이동하려는 칸이 반킨이냐 아니면 다른물고가 있는가?
//            int nextRow = map.get(i).row + dy[dir];
//            int nextCol = map.get(i).col + dx[dir];
//            if(array[nextRow][nextCol] == 0){
//                // 빈칸이면 해당 좌표 및 dir방향 수정
//                // 이전칸 array = 0; 옮긴칸 array = i;
//                array[map.get(i).row][map.get(i).col] = 0;
//                map.get(i).row = nextRow;
//                map.get(i).col = nextCol;
//                map.get(i).dir = dir;
//                array[nextRow][nextCol] = i;
//            }else{
//                // 물고기가 있는 칸이면 서로 교환한다.
//                // 좌표바꾸고, array값바꾸고, dir값 바꿔.
//                int num1 = i;
//                int num2 = array[nextRow][nextCol];
//
//                array[nextRow][nextCol] = i;
//                array[map.get(i).row][map.get(i).col] = num2;
//
//                map.get(num2).row = map.get(i).row;
//                map.get(num2).col = map.get(i).col;
//
//                map.get(i).row = nextRow;
//                map.get(i).col = nextCol;
//                map.get(i).dir = dir;
//
//            }
//
//        }
//    }
//    public static void dfs(int sx, int sy, int sd, int eat ){
//        answer = Math.max(eat, answer);
//
//        int[][] tempMap = new int[4][4];
//        for(int i = 0; i < 4; i++){
//            System.arraycopy(array[i], 0, tempMap[i], 0, 4);
//        }
//
//        HashMap<Integer, Node> mapTemp = new HashMap<>();
//        for(int i : map.keySet())
//            mapTemp.put(i, map.get(i));
//
//        moveFish();
//
//        for(int i = 1; i < 4; i++){
//            int nextRow = sy + dy[sd] * i;
//            int nextCol = sx + dx[sd] * i;
//
//            if(nextRow < 0 || nextRow >= 4 || nextCol < 0 || nextCol >= 4)
//                continue;
//            if(array[nextRow][nextCol] == 0)
//                continue;
//            int num1 = array[nextRow][nextCol];
//            int num2 = array[sy][sx];
//            int dir = map.get(num1).dir;
//
//            array[sy][sx] = 0;
//            shark = new Node(-1, map.get(num1).row, map.get(num1).col,
//                    map.get(num1).dir);
//            array[map.get(num1).row][map.get(num1).col] = -1;
//            map.remove(num1);
//
//            dfs(nextCol, nextRow, dir, eat + num1);
//
//            map.put(num1, new Node(num1, nextRow, nextCol, dir));
//            array[sy][sx] = -1;
//            array[map.get(num1).row][map.get(num1).col] = num1;
//
//        }
//
//        map = new HashMap<>();
//        for(int i : mapTemp.keySet())
//            map.put(i, mapTemp.get(i));
//
//        for(int i = 0 ; i < 4; i++)
//            System.arraycopy(tempMap[i], 0, array[i], 0, 4);
//
//
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        array = new int[4][4];
//
//        for(int i = 0; i < 4; i++){
//            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//            for(int j = 0 ; j < 4; j++){
//                int num = Integer.parseInt(st.nextToken());
//                int dir = Integer.parseInt(st.nextToken()) - 1;
//                map.put(num, new Node(num, i, j, dir));
//                array[i][j] = num;
//            }
//        }
//        int sum = array[0][0];
//        int dir = map.get(array[0][0]).dir;
//        shark = new Node(-1, 0, 0, map.get(array[0][0]).dir);
//        map.remove(array[0][0]);
//        array[0][0] = -1;
//        dfs(0, 0, dir, sum);
//        System.out.println(answer);
//    }
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class algo200 {

    static Node shark;
    static Node[] fish = new Node[17];
    static int maxLength = 4;
    static int[][] map;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static class Node{
        int num;
        int row;
        int col;
        int dir;
        int die;
        Node(int num, int row, int col, int dir,int die){
            this.num = num;
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.die = die;
        }
    }

    public static void moveFish(){

        for(int i = 1; i <= 16; i++){
            Node temp = new Node(i, fish[i].row, fish[i].col, fish[i].dir, fish[i].die);

            // fish가 죽어있으면 움직일수 없다.
            if(temp.die == 0)
                continue;


            int dir = -1;
            // 움직일수 있는곳 을찾자, 만약 상어가있거나 맵밖이면 continue;
            for(int j = 0 ; j < 8; j++){
                int nextRow = temp.row + dy[(temp.dir + j) % 8];
                int nextCol = temp.col + dx[(temp.dir + j) % 8];

                if(nextRow < 0 || nextRow >= maxLength || nextCol < 0 || nextCol >= maxLength)
                    continue ;

                if(map[nextRow][nextCol] == -1)
                    continue;

                dir = (temp.dir + j) % 8;
                break;
            }

            // 만약 가려는 곳이 물고기가 있으면 그물고기와 swap
            int nextRow = temp.row + dy[dir];
            int nextCol = temp.col + dx[dir];

            if(map[nextRow][nextCol] >= 1){
                // 해당되는 물고기 dir먼저 바꾸고,
                // 다음물고기와 해당물고기 좌표바꾸고,
                // map에 값 바꾸고.

                int value1 = i;
                int value2 = map[nextRow][nextCol];

                fish[i].dir = dir;
                fish[i].row = fish[map[nextRow][nextCol]].row;
                fish[i].col = fish[map[nextRow][nextCol]].col;
                map[nextRow][nextCol] = i;

                fish[value2].row = temp.row;
                fish[value2].col = temp.col;
                map[temp.row][temp.col] = value2;
            }else{
                // 빈칸이면 단순히 옮기기만 하면된다.
                // 옮기고 자기가 떠난 자리는 0으로 바꾸자.
                int value1 = i;
                int value2 = map[nextRow][nextCol];

                fish[i].dir = dir;
                fish[i].row = nextRow;
                fish[i].col = nextCol;
                map[nextRow][nextCol] = i;
                map[temp.row][temp.col] = 0;

            }
        }
    }
    static int answer = 0;
    public static void dfs(int row, int col, int dir, int cnt){

        answer = Math.max(cnt, answer);

        int[][] temp = new int[maxLength][maxLength];
        for(int i = 0; i < maxLength; i++){
            for(int j = 0; j < maxLength; j++){
                temp[i][j] = map[i][j];
            }
        }

        Node[] tempFish = new Node[17];
        for(int i = 1 ; i <= 16; i++)
            tempFish[i] = new Node(i, fish[i].row, fish[i].col, fish[i].dir, fish[i].die);;

        // 물고기 움직이는 함수.
        moveFish();

        // 상어를 움직이자.
        for(int i = 1; i <= 3; i++){
            int nextRow = row + dy[dir] * i;
            int nextCol = col + dx[dir] * i;

            //물고기가 없는칸이나, 맵밖으로 벗어나면 접근불가
            if(nextRow < 0 || nextRow >= maxLength || nextCol < 0 || nextCol >= maxLength)
                continue;
            if(map[nextRow][nextCol] == 0 || map[nextRow][nextCol] == -1)
                continue;

            Node prev = shark;
            Node next = fish[map[nextRow][nextCol]];
            //이동하면 그칸에 물고기먹고, 그물고기 방향으로 변경.
            // 기존에칸은 0;
            map[prev.row][prev.col] = 0;
            shark.row = nextRow;
            shark.col = nextCol;
            shark.dir = fish[map[nextRow][nextCol]].dir;
            map[nextRow][nextCol] = -1;
            // 물고기 죽음표시
            fish[next.num].die = 0;

            dfs(nextRow, nextCol, shark.dir, cnt + next.num);

            // 다시 복구
            shark.row = prev.row;
            shark.col = prev.col;
            shark.dir = prev.dir;
            map[prev.row][prev.col] = -1;
            map[nextRow][nextCol] = next.num;
            fish[next.num].die = 1;

        }
        for(int i = 0; i < maxLength; i++){
            for(int j = 0; j < maxLength; j++){
                map[i][j] = temp[i][j];
            }
        }
        for(int i = 1 ; i <= 16; i++)
            fish[i] = new Node(i, tempFish[i].row, tempFish[i].col, tempFish[i].dir, tempFish[i].die);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[maxLength][maxLength];
        for(int i = 0; i < maxLength; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < maxLength; j++){
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;
                fish[num] = new Node(num, i, j, dir, 1);
                map[i][j] = num;
            }
        }

        // 일단 상어가 0,0 고기먹자.
        // 먹고 상어 위치 및 dir해당 고기로 변경, map = -1, 해당물고기 죽음 표시.
        int count = map[0][0];
        shark = new Node(-1, 0, 0, fish[map[0][0]].dir, 1);
        fish[map[0][0]].die = 0;
        map[0][0] = -1;
        dfs(0, 0, shark.dir, count);
        System.out.println(answer);

    }
}

