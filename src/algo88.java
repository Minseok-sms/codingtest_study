import java.util.Arrays;

public class algo88 {
    /*
        프로그래머스 : 입국심사
     */

    public static long solution(int n, int[] times) {
        Arrays.sort(times);
        long start = times[0];
        long end = (long)n * times[times.length - 1];

        while(start <= end){
            long mid = (start + end) / 2;
            long sum = 0;
            for(int i = 0 ; i < times.length ; i++){
                sum += mid / times[i];
            }
            if(sum >= n)
                end = mid - 1;
            else
                start = mid + 1;
        }
        System.out.println(start);
        return start;
    }
}
/*
import java.util.Arrays;
class Solution {
    public long solution(int n, int[] times) {
       long answer = 0;
        Arrays.sort(times);
        long left = times[0];
        long right = (long)n * times[times.length - 1];
        while(left <= right){
            long mid = (left + right) / 2;
            long sum = 0;
            for(int i = 0 ; i < times.length; i++)
                sum += mid / times[i];
            if(sum < n){
                left = mid + 1;
            }else {
                right = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}
 */