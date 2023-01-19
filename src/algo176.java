//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.StringTokenizer;
//
//public class algo176 {
//    /*
//        0 6
//        1 4
//        2 13
//        3 5
//        3 8
//        5 7
//        5 9
//        6 10
//        8 11
//        8 12
//        12 14
//     */
//
//    static int N;
//    static int[][] array;
//    static boolean check = false;
//    static boolean[] visited;
//    static int MAX = 0;
//
//    public static void find(int start, int count, int depth){
//        if(check == true)
//            return ;
//        if(count == depth){
//            //만약 찾앗으면 check == true
//            check = true;
//            return ;
//        }
//        visited[start] = true;
//        for(int i = start; i < N; i++){
//            if(visited[i] == true)
//                continue;
//
//            int num1 = array[start][1];
//            int num2 = array[i][0];
//            if(num1 > num2)
//                continue;
//
//            visited[i] = true;
//            find(i, count + 1, depth);
//            visited[i] = false;
//
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//        array = new int[N][2];
//
//        for(int i = 0 ; i < N; i++){
//            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//            array[i][0] = Integer.parseInt(st.nextToken());
//            array[i][1] = Integer.parseInt(st.nextToken());
//        }
//        // 앞에꺼기준으로 오름차순, 같은거기준으로 뒤에꺼 오름차순
//        Arrays.sort(array, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if(o1[0] < o2[0])
//                    return o1[0] - o2[0];
//                else if(o1[0] == o2[0]){
//                    if(o1[1] < o2[1])
//                        return o1[1] - o2[1];
//                }
//                return 1;
//            }
//        });
////        for(int i = 0 ; i < N; i++)
////            System.out.println(array[i][0] + " " + array[i][1]);
//
//        for(int i = 1; i <= N; i++){
//            visited = new boolean[N];
//            check = false;
//            for(int j = 0; j < N; j++) {
//                find(j, 1, i);
//            }
//            if(check == true)
//                MAX = i;
//            else
//                break;
//        }
//        System.out.println(MAX);
//
//    }
//}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class algo176 {
    /*
       1 4
       3 5
       0 6
       5 7
       3 8
       5 9
       6 10
       8 11
       8 12
       2 13
       12 14




        0 6
        1 4
        2 13
        3 5
        3 8
        5 7
        5 9
        6 10
        8 11
        8 12
        12 14
     */

    static int N;
    static int[][] array;
    static boolean check = false;
    static boolean[] visited;
    static int MAX = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N][2];

        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            array[i][0] = Integer.parseInt(st.nextToken());
            array[i][1] = Integer.parseInt(st.nextToken());
        }
        // 앞에꺼기준으로 오름차순, 같은거기준으로 뒤에꺼 오름차순
        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] < o2[1])
                    return o1[1] - o2[1];
                else if(o1[1] == o2[1])
                    return o1[0] - o2[0];
                return 1;
            }
        });
        int prev_time = 0;
        int answer = 0;
        for(int i = 0; i < N; i++){
            int next_time = array[i][0];
            if(prev_time <= next_time){
                prev_time = array[i][1];
                answer++;
            }
        }
        System.out.println(answer);
    }
}
