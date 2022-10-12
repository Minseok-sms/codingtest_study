//
//import java.io.*;
//import java.util.*;
//
//public class algo24 {
//
//    static ArrayList<Integer>[] arr;
//    static int[][] map;
//    static boolean[] visited;
//    static ArrayList<Integer> answer = new ArrayList<>();
//    static ArrayList<Integer> max = new ArrayList<>();
//    static int level = 0;
//    public static void depthDfs(int start,int root, int count){
//        visited[start] = true;
//        level = level < count ? count : level;
//        for(int i = 0; i < arr[start].size(); i++){
//            if(visited[arr[start].get(i)] == true || arr[start].get(i) < root)
//                continue;
//
//            visited[arr[start].get(i)] = true;
//            depthDfs(arr[start].get(i), root, count + 1);
//        }
//    }
//    public static void dfs(int start,int sum,int count, int depth, int root){
//
//        visited[start] = true;
//        if(count == depth){
//            max.add(sum);
//            return ;
//        }
//        for(int i = 0; i < arr[start].size(); i++){
//
//            if(visited[arr[start].get(i)] == true || arr[start].get(i) < root)
//                continue;
//
//            if(arr[start].get(i) == root){
//                Collections.sort(max, Collections.reverseOrder());
//                int temp = max.get(0);
//                max.clear();
//            }
//
//            visited[arr[start].get(i)] = true;
//            dfs(arr[start].get(i), sum + map[start][arr[start].get(i)],count + 1, depth, root);
//            visited[arr[start].get(i)] = false;
//        }
//
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(br.readLine());
//        arr = new ArrayList[N + 1];
//        map = new int[N + 1][N + 1];
//
//        for(int i = 0; i < arr.length; i++)
//            arr[i] = new ArrayList<>();
//        for(int i = 0; i < N - 1; i++){
//            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//            int first = Integer.parseInt(st.nextToken());
//            int second = Integer.parseInt(st.nextToken());
//            int value = Integer.parseInt(st.nextToken());
//
//            arr[first].add(second);
//            arr[second].add(first);
//            map[first][second] = value;
//            map[second][first] = value;
//        }
//        for(int i = 1; i < N + 1; i++){
//            int depthCount = 0;
//            visited = new boolean[N + 1];
//            depthDfs(i, i, 0);
//            depthCount = level;
//            level = 0;
//            if(depthCount == 0)
//                continue;
//
//            visited = new boolean[N + 1];
//            max.clear();
//            dfs(i, 0, 0, depthCount, i);
//
//            Collections.sort(max, Collections.reverseOrder());
//            answer.add(max.get(0) + max.get(1));
//        }
//        Collections.sort(answer, Collections.reverseOrder());
//        System.out.println(answer.get(0));
//    }
//}


import java.io.*;
import java.util.*;

public class algo24 {

    static ArrayList<Integer>[] arr;
    static int[][] map;
    static boolean[] visited;
    static int max = 0;
    static int index = 0;

    public static void dfs(int start, int sum){
        visited[start] = true;
        if(max < sum){
            max = sum;
            index = start;
        }
        for(int i = 0; i < arr[start].size(); i++){
            if(visited[arr[start].get(i)] == true)
                continue;
            visited[arr[start].get(i)] = true;
            dfs(arr[start].get(i), sum + map[start][arr[start].get(i)]);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new ArrayList[N + 1];
        map = new int[N + 1][N + 1];

        for(int i = 0; i < arr.length; i++)
            arr[i] = new ArrayList<>();
        for(int i = 0; i < N - 1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            arr[first].add(second);
            arr[second].add(first);
            map[first][second] = value;
            map[second][first] = value;
        }

        visited = new boolean[N + 1];
        dfs(1, 0);
        max = 0;
        visited = new boolean[N + 1];
        dfs(index, 0);
        System.out.println(max);
    }
}
