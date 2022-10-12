import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class algo90 {

    /*
    백준 : 수이어쓰기 2
    이분탐색
     */

    public static int lengthSize(int mid){
        // mid = 10;
        int count = 0;
        for(int start = 1, digit = 1; start <= mid; start *= 10, digit++){
            int end = start * 10 - 1;
            if(mid < end){
                end = mid;
            }
            count += (end - start + 1) * digit;
        }
        return count;
    }
    public static int binarySearch(int N, int K){
        int left = 1;
        int right = N;
        int answer = 0;

        while(left <= right){
            int mid = (left + right) / 2;

            // 만약 mid = 10이면 10까지 숫자써서 그총길이를 반환
            int len = lengthSize(mid);
            if(len < K){
                left = mid + 1;
            }else{
                answer = mid;
                right = mid - 1;
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if(lengthSize(N) < K){
            System.out.println(-1);
            System.exit(0);
        }

        int answer = binarySearch(N, K);
        int length = lengthSize(answer); // 13

        String ans = answer + ""; // 2;
        System.out.println(ans.charAt(ans.length() - length + K - 1) + "");

    }
}





//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class algo90 {
//
//
//    public static int binarySearch(int N, int K){
//        long count;
//        int left = 1;
//        int right = N;
//        int mid;
//        int answer = 0;
//
//        count = 0;
//        count += N;
//        for(int i = 10; i <= 100000000; i *= 10){
//            if(N < 10)
//                break;
//            if(N / i == 0){
//                count += N - (i / 10) + 1;
//                break;
//            }
//        }
//        if(count < K) {
//            return -1;
//        }
//        else {
//            while (left <= right) {
//                // left  = 6, right = 9
//                // left = 8, right = 9;
//                // left = 9, right = 9;
//                mid = (left + right) / 2;
//                count = 0;
//                count += mid;
//                int i;
//                for (i = 10; i <= 100000000; i *= 10) {
//                    if(N < 10)
//                        break;
//                    if (mid / i == 0) {
//                        count += mid - (i / 10) + 1;
//                        break;
//                    }
//                }
//
//                if (count < K) {
//                    left = mid + 1;
//                } else {
//                    // count >= K
//                    right = mid - 1;
//                    if(count == K){
//                        if(i == 10){
//                            answer = mid;
//                        }else
//                            answer = mid % (i / 10);
//                    }else if(count == K - 1){
//                        answer = mid / (i / 10);
//                    }
//                }
//            }
//        }
//        return answer;
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        int N = Integer.parseInt(st.nextToken());
//        int K = Integer.parseInt(st.nextToken());
//        int answer = binarySearch(N, K);
//        System.out.println(answer);
//
//    }
//}
