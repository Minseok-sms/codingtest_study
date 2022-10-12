import java.util.Arrays;

//public class algo72 {
//
//
//    static boolean[] visited;
//    static int MAX = 0;
//    public static void dfs(int[] A, int[] B, int count, int sum){
//
//        MAX = Math.max(MAX, sum);
//
//        for(int i = 0; i < A.length; i++){
//            if(visited[i] == true)
//                continue;
//
//
//            visited[i] = true;
//            if(A[count] < B[i]) {
//                dfs(A, B, count + 1,sum + 1);
//            }else
//                dfs(A, B, count + 1, sum);
//
//            visited[i] = false;
//        }
//    }
//
//    public static int solution(int[] A, int[] B) {
//        int answer = -1;
//        visited = new boolean[A.length];
//
//        dfs(A, B, 0,0);
//        answer = MAX;
//        System.out.println(answer);
//        return answer;
//    }
//    public static void main(String[] args) {
//        int[] A = {5,1,3,7};
//        int[] B = {2,2,6,8};
//        solution(A,B);
//    }
//}
//
//// 1 3 5 7
//// 2 2 6 8
public class algo72 {


    public static int solution(int[] A, int[] B) {
        int answer = -1;
        Arrays.sort(A);
        Arrays.sort(B);
        int idx = 0;
        for(int i = 0 ; i < A.length; i++){
            if(A[idx] >= B[i])
                continue;

            idx++;
        }
        answer = idx;
        return answer;
    }
    public static void main(String[] args) {
        int[] A = {5,1,3,7};
        int[] B = {2,2,6,8};
        solution(A,B);
    }
}

// 1 3 5 7
// 2 2 6 8
