import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class algo198 {
    /*
    백준 : 21608
    상어초등학교
     */
    static int N;
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    static ArrayList<Node> coor;
    static int[][] array;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Node{
        int cnt;
        int row;
        int col;
        Node(int cnt, int row, int col){
            this.cnt = cnt;
            this.row = row;
            this.col = col;
        }
    }
    public static void find1(int row, int col, int num1, int num2, int num3, int num4){
        int cnt = 0;

        for(int i = 0 ; i < 4; i++){
            int nextRow = row + dy[i];
            int nextCol = col + dx[i];
            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N)
                continue;
            // 그좌표 기준으로 상하좌우 좋아하는 사람이 존재하면 cnt++;
            if(array[nextRow][nextCol] == num1 || array[nextRow][nextCol] == num2
            || array[nextRow][nextCol] == num3 || array[nextRow][nextCol] == num4)
                cnt++;
        }
        // 좋아하는사람 수 와 좌표넣자
        coor.add(new Node(cnt, row, col));
    }
    public static void find2(int row, int col){
        int cnt = 0;
        for(int i = 0 ; i < 4; i++){
            int nextRow = row + dy[i];
            int nextCol = col + dx[i];
            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N)
                continue;
            // 비어있는 칸이면 cnt ++;
            if(array[nextRow][nextCol] == 0){
                cnt++;
            }
        }
        coor.add(new Node(cnt, row, col));
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N][N];
        visited = new boolean[N][N];

        for(int k = 0 ; k < Math.pow(N, 2); k++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int stu =  Integer.parseInt(st.nextToken());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            int num3 = Integer.parseInt(st.nextToken());
            int num4 = Integer.parseInt(st.nextToken());
            if(!map.containsKey(stu)) {
                map.put(stu, new ArrayList<>());
                map.get(stu).add(num1);
                map.get(stu).add(num2);
                map.get(stu).add(num3);
                map.get(stu).add(num4);
            }

            // 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리 (조건1)
            coor = new ArrayList<>();
            for(int i = 0; i < N; i++){
                for(int j = 0 ; j < N ; j++){
                    // 이미 차지된 좌표이면 패스
                    if(visited[i][j] == true)
                        continue;
                    find1(i, j, num1, num2, num3, num4);
                }
            }


            // 좋아하는 학생수에 따라 내림차순 정렬해라
//            Collections.sort(coor, new Comparator<Node>() {
//                public int compare(Node o1, Node o2) {
//                    if (o1.cnt < o2.cnt)
//                        return 1;
//                    else
//                        return -1;
//                }
//            });
            Collections.sort(coor, new Comparator<Node>() {
                public int compare(Node o1, Node o2) {
                    return o2.cnt - o1.cnt;
                }
            });


            ArrayList<Node> temp = new ArrayList<>();
            int max = coor.get(0).cnt;
            for(Node i : coor){
                if(i.cnt == max)
                    temp.add(i);
            }
            //만약 size() == 1 이면 그자리 배치
            if(temp.size() == 1) {
                array[temp.get(0).row][temp.get(0).col] = stu;
                visited[temp.get(0).row][temp.get(0).col] = true;
                continue;
            }
            coor = new ArrayList<>();
            // 조건2 비어있는 칸이 가장 많은 칸으로 배정한다.
            for(Node i : temp){
                int row = i.row;
                int col = i.col;
                find2(row, col);
            }
            temp = new ArrayList<>();
            // 비어있는칸 내림차순해라.
//            Collections.sort(coor, new Comparator<Node>() {
//                public int compare(Node o1, Node o2) {
//                    if (o1.cnt < o2.cnt) {
//                        return 1;
//                    } else {
//                        return -1;
//                    }
//
//                }
//            });
            Collections.sort(coor, new Comparator<Node>() {
                public int compare(Node o1, Node o2) {
                    return o2.cnt - o1.cnt;

                }
            });

            max = coor.get(0).cnt;
            for(Node i : coor){
                if(i.cnt == max)
                    temp.add(i);
            }
            // 만약 size() == 1이면 조건2 완료
            if(temp.size() == 1) {
                array[temp.get(0).row][temp.get(0).col] = stu;
                visited[temp.get(0).row][temp.get(0).col] = true;
                continue;
            }

             //아니면 행의 번호가 가장 작은칸 -> 열의번호가 가장작은칸
            Collections.sort(temp, new Comparator<Node>(){
               public int compare(Node o1, Node o2){
                   if(o1.row < o2.row){
                       return o1.row - o2.row;
                   } else if (o1.row == o2.row) {
                       if(o1.col <= o2.col){
                           return o1.col - o2.col;
                       }else
                           return -1;
                   }else
                       return 0;

               }
            });


            array[temp.get(0).row][temp.get(0).col] = stu;
            visited[temp.get(0).row][temp.get(0).col] = true;
        }

        int answer = 0;
        // 학생의 만족도를 구하자 0, 1, 10, 100, 1000
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N ; j++){
                int stu = array[i][j];
                int num1 = map.get(stu).get(0);
                int num2 = map.get(stu).get(1);
                int num3 = map.get(stu).get(2);
                int num4 = map.get(stu).get(3);
                coor = new ArrayList<>();
                find1(i, j, num1 ,num2, num3, num4);
                if(coor.get(0).cnt == 0)
                    answer += 0;
                else if(coor.get(0).cnt == 1)
                    answer += 1;
                else if(coor.get(0).cnt == 2)
                    answer += 10;
                else if(coor.get(0).cnt == 3)
                    answer += 100;
                else if(coor.get(0).cnt == 4)
                    answer += 1000;
            }
        }
        System.out.println(answer);
    }
}
