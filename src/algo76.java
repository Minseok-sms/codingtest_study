//import java.util.Arrays;
///*
//   프로그래머스 : 징검다리 건너기
// */
//
//public class algo76 {
//
//    public static int solution(int[] stones, int k) {
//        int answer = 0;
//
//        stop:
//        while(true){
//            // 연속된 0이 k개 확인
//            int zeroCount = 0;
//            for(int i : stones){
//                if(zeroCount == k)
//                    break stop;
//                if(i == 0){
//                    zeroCount++;
//                }else
//                    zeroCount = 0;
//            }
//
//            //배열중 최소값을 찾아서 다빼줌
//            //0보다 큰값으로 찾자.
//            int[] temp = Arrays.copyOf(stones, stones.length);
//            Arrays.sort(temp);
//            int min = 0;
//            for(int i = 0 ; i < temp.length; i++){
//                if(temp[i] >= 1) {
//                    min = temp[i];
//                    break;
//                }
//            }
//
//            for(int i = 0; i < stones.length; i++){
//                if(stones[i] != 0) {
//                    stones[i] -= min;
//                }
//            }
//            answer += min;
//
//        }
//        System.out.println(answer);
//        return answer;
//    }
//    public static void main(String[] args) {
//        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
//        solution(stones, 3);
//    }
//}
import java.util.Arrays;
/*
   프로그래머스 : 징검다리 건너기
 */

public class algo76 {

    public static int solution(int[] stones, int k) {
        int answer = 0;

        int left = 1;
        int right = 200000001;

        while(left <= right){
            int mid = (left + right) / 2;
            // 3, 3
            int checkCount = 0;
            boolean path = true;
            for(int i : stones){
                int check = i - mid;
                if(check <= 0)
                    checkCount++;
                else
                    checkCount = 0;

                if(checkCount == k)
                    path = false;
            }
            if(path == false){
                right = mid - 1;
            }else
                left = mid + 1;
        }
        answer = left;
        System.out.println(answer);
        return answer;
    }
    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        solution(stones, 3);
    }
}
