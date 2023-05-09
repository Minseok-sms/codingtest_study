//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.HashMap;
//import java.util.StringTokenizer;
//
//public class algo202 {
//    static int N;
//    static HashMap<Integer, Integer> map = new HashMap<>();
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        int[] array = new int[N + 1];
//        int[] answer = new int[N + 1];
//        for(int i = 1; i <= N ; i++)
//            array[i] = Integer.parseInt(st.nextToken());
//
//        // answer[0] = 0;
//        answer[1] = 0;
//        int max = array[1];
//        map.put(array[1], 1);
//
//        for(int i = 2; i <= N; i++){
//            // 만약 자기자신이 지금 젤크면 max갱신.
//            if(max < array[i]){
//                max = array[i];
//                map.put(array[i], i);
//                answer[i] = 0;
//            }else{
//                // 자기자신보다 큰 key값의 value에 접근하여 가장큰 idx가 자기답.
//                int maxIdx = -1;
//                int nowValue = array[i];
//                for(int j = nowValue ; j <= max; j++){
//                    if(map.containsKey(j)){
//                        if(maxIdx <= map.get(j))
//                            maxIdx = map.get(j);
//                    }
//                }
//                map.put(array[i], i);
//                answer[i] = maxIdx;
//            }
//        }
//        for(int i = 1; i <= N; i++)
//            System.out.print(answer[i] + " ");
//
//    }
//}
import java.io.*;
import java.util.*;
public class algo202 {
    static char[][] array;
    static int N, M;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0 , -1 };
    static boolean[] visited;
    static int MAX = 0;


    public static void dfs(int row, int col, int cnt) {

        MAX = Math.max(cnt, MAX);
        visited[array[row][col] - 'A'] = true;

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dy[i];
            int nextCol = col + dx[i];
            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
                continue;
            if (visited[array[nextRow][nextCol] - 'A'] == true)
                continue;

            visited[array[nextRow][nextCol] - 'A'] = true;
            dfs(nextRow, nextCol, cnt + 1);
            visited[array[nextRow][nextCol] - 'A'] = false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        array = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                array[i][j] = str.charAt(j);
            }
        }
        visited = new boolean[26];

        dfs(0, 0, 1);
        System.out.println(MAX);

    }
}