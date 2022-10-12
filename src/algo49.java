public class algo49 {
    public static int solution(int n, int[] times) {
        int answer = 0;
        int temp = 0;
        int left = times[0];
        int right = n * times[times.length - 1];
      // 2 4 5
        // 5
        while (right < 0) {
            temp = 0;

            int mid = right;
            int sum = 0;
            // 4 + 2*2 +
            for (int i = 0; i < times.length; i++) {
                sum += mid / times[i];
                temp += (i + 1) * sum;
                // 4+ 4
            }
            if (temp <= n) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }

        return answer;
    }
    public static void main(String[] args) {
        int[] times = {2,3};
        int answer = solution(4,times);
    }
}
//public class algo49 {
//    public static int solution(int n, int[] times) {
//
//    }
//    public static void main(String[] args) {
//        int[] times = {2,3};
//        int answer = solution(4,times);
//    }
//}
