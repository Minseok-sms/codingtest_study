import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class algo161 {

    static int[][] map;
    static int N;
    static int sharkSize = 2;
    static int exp = 0;
    static int sharkRow, sharkCol;

    static int[] dy = {-1, 0 ,1 , 0};
    static int[] dx = {0, -1, 0 , 1};
    static int globalCount = 0;
    static boolean[][] visited;

    static ArrayList<Node> array = new ArrayList<>();
    public static void bfs(int row, int col){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(row, col, 0));
        visited[row][col] = true;

        while(!queue.isEmpty()){

            Node temp = queue.poll();

            for(int i = 0 ; i < 4; i++){
                int nextRow = temp.row + dy[i];
                int nextCol = temp.col + dx[i];

                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N)
                    continue;
                // 자기자신보다 크면 지나갈수 없음
                if(visited[nextRow][nextCol] == true || map[nextRow][nextCol] > sharkSize)
                    continue;


                //만약 빈칸이아닐경우와 자기보다작을때 = 잡아 먹을수 있는 고기 + 시간 넣어
                if(map[nextRow][nextCol] != 0 &&  map[nextRow][nextCol] < sharkSize){
                    array.add(new Node(nextRow, nextCol, temp.time + 1));
                    visited[nextRow][nextCol] = true;
                    queue.add(new Node(nextRow, nextCol, temp.time + 1));
                }

                if(map[nextRow][nextCol] == sharkSize || map[nextRow][nextCol] == 0) {
                    visited[nextRow][nextCol] = true;
                    queue.add(new Node(nextRow, nextCol, temp.time + 1));
                }

            }
        }



    }
    static class Node{
        int row;
        int col;
        int time;
        Node(int row, int col, int time){
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    sharkRow = i;
                    sharkCol = j;
                }
            }
        }
        while(true){
            visited = new boolean[N][N];
            array = new ArrayList<>();

            // 먹을수 있는 물고기 찾으러가자
            bfs(sharkRow, sharkCol);

            //만약 먹을수 업는 물고기가 없으면 종료
            if(array.size() == 0)
                break;

            //잡아 먹을수 있는 고기가 한마리다.
            if(array.size() == 1){
                sharkRow = array.get(0).row;
                sharkCol = array.get(0).col;
                globalCount += array.get(0).time;
                //경험치 1증가
                exp++;
            }else{
                // 여러마리 일경우 시간이 가장 적게걸린 물고기중 가장위 -> 가장왼쪽
//                Collections.sort(array, new Comparator<Node>() {
//                    @Override
//                    public int compare(Node o1, Node o2) {
//                        if(o1.time > o2.time){
//                            return o1.time - o2.time;
//                        }else if(o1.time == o2.time){
//                            if(o1.row == o2.row){
//                                return o1.col - o2.col;
//                            }else
//                                return o1.row - o2.row;
//                        }
//                        return -1;
//                    }
//                });

                Collections.sort(array, new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        if(o1.time == o2.time) {
                            if(o1.col == o2.col) {
                                if(o1.row > o2.row) {
                                    return 1;
                                }else {
                                    return -1;
                                }
                            }else {
                                if(o1.col > o2.col) {
                                    return 1;
                                }else {
                                    return -1;
                                }
                            }
                        }else if(o1.time > o2.time){
                            return 1;
                        }else {
                            return -1;
                        }
                    }
                });

                sharkRow = array.get(0).row;
                sharkCol = array.get(0).col;
                globalCount += array.get(0).time;
                //경험치 1증가
                exp++;
            }
            // 잡아먹은 자리 0으로 바꿈
            map[sharkRow][sharkCol] = 0;

            if(exp == sharkSize){
                sharkSize++;
                exp = 0;
            }
        }
        System.out.println(globalCount);

    }
}
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.LinkedList;
//import java.util.Queue;
//
//public class algo161 {
//
//    // 먹이가 될 물고기
//    private static class Fish {
//        int x, y, time;
//
//        public Fish(int x, int y, int time) {
//            super();
//            this.x = x;
//            this.y = y;
//            this.time = time;
//        }
//    }
//
//    static int N;
//    static int[][] map;
//    static boolean[][] isVisit;
//
//    static Fish shark;		// 상어도 물고기! 상어의 위치를 기록
//    static int size = 2;
//    static int eaten = 0;
//
//    static ArrayList<Fish> feedList = new ArrayList<>();
//    static int answer;
//
//    static int[] dx = {-1, 1, 0, 0};
//    static int[] dy = {0, 0, -1, 1};
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        String[] input = br.readLine().split(" ");
//        N = Integer.parseInt(input[0]);
//
//        map = new int[N][N];
//        isVisit = new boolean[N][N];
//
//        for (int i = 0; i < N; i++) {
//            input = br.readLine().split(" ");
//
//            for (int j = 0; j < N; j++) {
//                map[i][j] = Integer.parseInt(input[j]);
//
//                if(map[i][j] == 9) {
//                    shark = new Fish(i, j, 0);
//                    map[i][j] = 0;
//                }
//            }
//        }
//
//        simulation();
//        System.out.println(answer);
//    }
//
//    private static void simulation() {
//
//        Queue<Fish> q = new LinkedList<>();
//        q.add(shark);
//        isVisit[shark.x][shark.y] = true;
//
//        while(true) {
//            while(!q.isEmpty()) {
//                Fish now = q.poll();
//                int time = now.time;
//
//
//                for (int k = 0; k < 4; k++) {
//                    int nx = now.x + dx[k];
//                    int ny = now.y + dy[k];
//
//                    // 범위 밖 -> 아웃
//                    if(!(0 <= nx && nx < N && 0 <= ny && ny < N) || isVisit[nx][ny]) continue;
//
//                    // [식사 가능] 먹이 리스트에 추가
//                    if(map[nx][ny] < size && map[nx][ny] != 0) {
//                        q.add(new Fish(nx, ny, time+1));
//                        isVisit[nx][ny] = true;
//                        feedList.add(new Fish(nx, ny, time+1));
//                    }
//
//                    // [이동만 가능] 사이즈랑 동일 OR 비어있음(0)
//                    if(map[nx][ny] == size || map[nx][ny] == 0) {
//                        q.add(new Fish(nx, ny, time+1));
//                        isVisit[nx][ny] = true;
//                    }
//                }
//            }
//
//            if(!feedList.isEmpty()) {
//                eat();
//                // 식사가 끝났으면, 방문 배열 초기화
//
//                q.clear();
//                isVisit = new boolean[N][N];
//
//                // 다시 이동하는 큐(q)에다가 현재 상어 추가
//                q.add(shark);
//                isVisit[shark.x][shark.y] = true;
//            }else {
//                return;
//            }
//        }
//
//    }
//
//    // 먹이 리스트가 값이 존재하면, 식사 시작
//    private static void eat() {
//
//        // 정렬을 통해 우선순위별로 먹이 리스트 재정렬
//        Collections.sort(feedList, new Comparator<Fish>() {
//            @Override
//            public int compare(Fish o1, Fish o2) {
//                if(o1.time == o2.time) {
//                    if(o1.x == o2.x) {
//                        if(o1.y > o2.y) {
//                            return 1;
//                        }else {
//                            return -1;
//                        }
//                    }else {
//                        if(o1.x > o2.x) {
//                            return 1;
//                        }else {
//                            return -1;
//                        }
//                    }
//                }else if(o1.time > o2.time){
//                    return 1;
//                }else {
//                    return -1;
//                }
//            };
//        });
//
//        Fish now = feedList.get(0);
//
//        // 상어 위치 이동, 상어가 먹은 횟수 증가, 상어 걸린 시간 증가
//        shark.x = now.x;
//        shark.y = now.y;
//
//        if(++eaten == size) {
//            size++;
//            eaten = 0;
//        }
//
//        answer += now.time;
//
//        // 지도 위에 해당 위치 0 처리
//        map[now.x][now.y] = 0;
//
//        // 먹이 리스트 초기화
//        feedList.clear();
//    }
//
//}
