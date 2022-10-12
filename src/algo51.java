import java.util.ArrayList;
import java.util.Collections;

public class algo51 {
    //라인 2번

    // dp(times, n, count = 1, time = 0);
    static ArrayList<Integer> array = new ArrayList<>();
    public static void dp(int[] times, int n, int count, int time){
        if(count > n)
            return ;
        if(n == count){
            array.add(time);
            return ;
        }
        for(int i = 1 ; i <= times.length; i++) {
            if(count >= i)
                dp(times, n, count + i, time + times[i-1]);
        }
    }
    public static int solution(int[] times, int n){
        int answer = 0;
        dp(times, n, 1, 0);
        Collections.sort(array);
        System.out.println(array.get(0));
        return answer;
    }
    public static void main(String[] args) {
        int[] times = {2,4,5};
        solution(times, 5);

    }
}
