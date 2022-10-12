import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class algo114 {

    /*
    백준 : 2437
    저울 => 누적합
     */
// 1 1 2 5 6 7 30
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");


        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(array);

        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (sum + 1 < array[i])
                break;

            sum += array[i];
        }
        System.out.println(sum + 1);
    }
}
//    static boolean[] visited;
//    static HashMap<Integer, Integer> map = new HashMap<>();
//    public static void dfs(int start,int[] arr,int depth, int depthCount, int sum){
//
//        if(depthCount == depth) {
//            map.put(sum, map.getOrDefault(sum, 0) + 1);
//            return ;
//        }
//
//        for(int i = start; i < arr.length; i++){
//            if(visited[i] == true)
//                continue;
//
//            visited[i] = true;
//            dfs(i, arr, depth, depthCount + 1, sum + arr[i]);
//            visited[i] = false;
//        }
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(br.readLine());
//        int[] array = new int[N];
//        visited = new boolean[N];
//
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//
//        int sum = 0;
//        for(int i = 0; i < N; i++) {
//            array[i] = Integer.parseInt(st.nextToken());
//            sum += array[i];
//        }
//        Arrays.sort(array);
//
//        for(int i = 1; i <= N; i++){
//            int depth = i;
//            visited = new boolean[N];
//            dfs(0, array, depth, 0,0);
//        }
//        for(int i = 1 ; i <= sum ; i++){
//            if(map.containsKey(i) == false){
//                System.out.println(i);
//                break;
//            }
//        }
//    }


// 1 1 2 5 6 7 30
// 1 2 3 4