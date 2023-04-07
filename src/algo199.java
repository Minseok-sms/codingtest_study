//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//public class algo199 {
//    /*
//    백준 : 15683
//    감시
//     */
//    static int[][] map;
//    static int N,M;
//    static ArrayList<Node> array = new ArrayList<>();
//    static int[] dx = {-1, 0, 1, 0}; // 왼 위 우 아
//    static int[] dy = {0, -1, 0, 1};
//    static class Node{
//        int no;
//        int row;
//        int col;
//        Node(int no, int row, int col){
//            this.no = no;
//            this.row = row;
//            this.col = col;
//        }
//    }
//
//    // # = 7;
//    // idx방향으로 #으로 감염
//    public static void change(int idx, int row, int col){
//        if(idx == 0){
//            for(int i = col - 1; i >= 0; i--){
//                if(map[row][i] == 6)
//                    break;
//                else if(map[row][i] == 0)
//                    map[row][i] = 7;
//            }
//        }else if(idx == 1){
//            for(int i = row - 1; i >= 0; i--){
//                if(map[i][col] == 6)
//                    break;
//                else if(map[i][col] == 0)
//                    map[i][col] = 7;
//            }
//        }else if(idx == 2){
//            for(int i = col + 1; i < M ; i++){
//                if(map[row][i] == 6)
//                    break;
//                else if(map[row][i] == 0)
//                    map[row][i] = 7;
//            }
//        }else{
//            for(int i = row + 1; i < N; i++ ){
//                if(map[i][col] == 6)
//                    break;
//                else if(map[i][col] == 0)
//                    map[i][col] = 7;
//            }
//        }
//    }
//    // idx방향으로 0의 갯수찾자.
//    public static int find(int idx, int row, int col){
//
//        int cnt = 0;
//        //왼쪽방향 끝까지 0의갯수 찾자.
//        if(idx == 0){
//            for(int i = col - 1; i >= 0; i--){
//                if(map[row][i] == 6)
//                    break;
//                else if(map[row][i] == 0)
//                    cnt++;
//            }
//        }else if(idx == 1){
//            // 위쪽 방향 끝까지
//            for(int i = row - 1; i >= 0; i--){
//                if(map[i][col] == 6)
//                    break;
//                else if(map[i][col] == 0)
//                    cnt++;
//            }
//        }else if(idx == 2){
//            // 오른쪽 방향 끝까지
//            for(int i = col + 1; i < M ; i++){
//                if(map[row][i] == 6)
//                    break;
//                else if(map[row][i] == 0)
//                    cnt++;
//            }
//        }else{
//
//            // 아래쪽에서 끝까지
//            for(int i = row + 1; i < N; i++ ){
//                if(map[i][col] == 6)
//                    break;
//                else if(map[i][col] == 0)
//                    cnt++;
//            }
//
//        }
//        return cnt;
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        map = new int[N][M];
//
//
//        // cctv 정보넣자
//        for(int i = 0 ; i < N; i++){
//            st = new StringTokenizer(br.readLine(), " ");
//            for(int j = 0 ; j < M; j++) {
//                map[i][j] = Integer.parseInt(st.nextToken());
//                if(map[i][j] != 0 && map[i][j] != 6)
//                    array.add(new Node(map[i][j], i, j));
//            }
//        }
//
//        //전체 cctv로 순환하자
//        for(int i = 0 ; i < array.size(); i++){
//
//            // cctv 번호
//            int no = array.get(i).no;
//            int idx = -1, max = 0;
//            if(no == 1){
//                // cctv -> 1이면 왼 위 우 아.
//                for(int j = 0; j < 4; j++){
//                    int temp = max;
//                    max = Math.max(find(j, array.get(i).row, array.get(i).col), max);
//                    if(temp != max)
//                        idx = j;
//                }
//                // idx방향으로 감염시켜.
//                change(idx, array.get(i).row, array.get(i).col);
//            }else if(no == 2){
//                // cctv -> 2 왼 우 // 위 아
//                for(int j = 0; j < 2; j++){
//                    int cnt1 = find(j, array.get(i).row, array.get(i).col);
//                    int cnt2 = find(j + 2, array.get(i).row, array.get(i).col);
//                    int temp = max;
//                    max = Math.max(cnt1 + cnt2, max);
//                    if(max != temp)
//                        idx = j;
//                }
//                // idx방향으로 감염시키자.
//                // 왼 위, 오 아.
//                change(idx, array.get(i).row, array.get(i).col);
//                change(idx + 2, array.get(i).row, array.get(i).col);
//
//            }else if(no == 3){
//                // cctv -> 3 왼아/ 왼위/ 위우/ 우아
//                for(int j = 0 ; j < 4; j++){
//                    int cnt1 = find(j, array.get(i).row, array.get(i).col);
//                    int cnt2 = find((j + 1) % 4, array.get(i).row, array.get(i).col);
//                    int temp = max;
//                    max = Math.max(cnt1 + cnt2, max);
//                    if(max != temp)
//                        idx = j;
//                }
//                // idx방향으로 감염시키자.
//                change(idx, array.get(i).row, array.get(i).col);
//                change((idx + 1) % 4, array.get(i).row, array.get(i).col);
//            }else if(no == 4){
//                for(int j = 0; j < 4; j++){
//                    int cnt1 = find(j, array.get(i).row, array.get(i).col);
//                    int cnt2 = find((j + 1) % 4, array.get(i).row, array.get(i).col);
//                    int cnt3 = find((j + 2) % 4, array.get(i).row, array.get(i).col);
//                    int temp = max;
//                    max = Math.max(cnt1 + cnt2 + cnt3, max);
//                    if(max != temp)
//                        idx = j;
//                }
//                change(idx, array.get(i).row, array.get(i).col);
//                change((idx + 1) % 4, array.get(i).row, array.get(i).col);
//                change((idx + 2) % 4, array.get(i).row, array.get(i).col);
//            }else if(no == 5){
//                // 상하좌우 한번에 감염시키자.
//                change(0, array.get(i).row, array.get(i).col);
//                change(1, array.get(i).row, array.get(i).col);
//                change(2, array.get(i).row, array.get(i).col);
//                change(3, array.get(i).row, array.get(i).col);
//
//            }
//
//        }
//
//        int answer = 0;
//        for(int i = 0 ; i < N; i++){
//            for(int j = 0 ; j < M; j++){
//                if(map[i][j] == 0)
//                    answer++;
//            }
//        }
//        System.out.println(answer);
//
//    }
//}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class algo199 {
    /*
    백준 : 15683
    감시
     */
    static int[][] map;
    static int min = Integer.MAX_VALUE;
    static int N,M;
    static boolean[] visited;
    static ArrayList<Node> array = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0}; // 왼 위 우 아
    static int[] dy = {0, -1, 0, 1};
    static class Node{
        int no;
        int row;
        int col;
        Node(int no, int row, int col){
            this.no = no;
            this.row = row;
            this.col = col;
        }
    }

    // # = 7;
    // idx방향으로 #으로 감염
    public static void change(int[][] map,int idx, int row, int col){
        if(idx == 0){
            for(int i = col - 1; i >= 0; i--){
                if(map[row][i] == 6)
                    break;
                else if(map[row][i] == 0)
                    map[row][i] = 7;
            }
        }else if(idx == 1){
            for(int i = row - 1; i >= 0; i--){
                if(map[i][col] == 6)
                    break;
                else if(map[i][col] == 0)
                    map[i][col] = 7;
            }
        }else if(idx == 2){
            for(int i = col + 1; i < M ; i++){
                if(map[row][i] == 6)
                    break;
                else if(map[row][i] == 0)
                    map[row][i] = 7;
            }
        }else{
            for(int i = row + 1; i < N; i++ ){
                if(map[i][col] == 6)
                    break;
                else if(map[i][col] == 0)
                    map[i][col] = 7;
            }
        }
    }
    public static int[][] copyMap(int[][] map) {
        int[][]tmp = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                tmp[i][j] = map[i][j];
            }
        }
        return tmp;
    }
    public static void dfs(int cnt, int[][] map, ArrayList<Node> array){
        if(cnt == array.size()){
            int zeroCnt = 0;
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < M; j++){
                    if(map[i][j] == 0)
                        zeroCnt++;
                }
            }
            min = Math.min(zeroCnt, min);
            return ;
        }

        for(int i = 0; i < array.size(); i++){
            int no = array.get(i).no;
            int row = array.get(i).row;
            int col = array.get(i).col;

            if(visited[i] == true)
                continue;

            int[][] temp;
            if(no == 1){
                for(int j = 0; j < 4; j++){
                    temp = copyMap(map);
                    change(temp, j, row, col);
                    visited[i] = true;
                    dfs(cnt + 1, temp, array);
                    visited[i] = false;
                }
            }else if(no == 2){
                for(int j = 0; j < 2; j++){
                    temp = copyMap(map);
                    change(temp, j, row, col);
                    change(temp, j + 2, row, col);
                    visited[i] = true;
                    dfs(cnt + 1, temp, array);
                    visited[i] = false;
                }
            }else if(no == 3){
                for(int j = 0 ; j < 4; j++){
                    temp = copyMap(map);
                    change(temp, j, row, col);
                    change(temp, (j + 1) % 4, row, col);
                    visited[i] = true;
                    dfs(cnt + 1, temp, array);
                    visited[i] = false;
                }
            }else if(no == 4){
                for(int j = 0 ; j < 4; j++){
                    temp = copyMap(map);
                    change(temp, j, row, col);
                    change(temp, (j + 1) % 4, row, col);
                    change(temp, (j + 2) % 4,row, col);
                    visited[i] = true;
                    dfs(cnt + 1, temp, array);
                    visited[i] = false;
                }
            }else{
                temp = copyMap(map);
                change(temp,0, row, col);
                change(temp,1, row, col);
                change(temp,2, row, col);
                change(temp,3, row, col);
                visited[i] = true;
                dfs(cnt + 1, temp, array);
                visited[i] = false;
            }


        }



    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];


        // cctv 정보넣자
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0 && map[i][j] != 6)
                    array.add(new Node(map[i][j], i, j));
            }
        }
        visited = new boolean[array.size()];
        dfs(0, map, array);

        //전체 cctv로 순환하자

        System.out.println(min);

    }
}
