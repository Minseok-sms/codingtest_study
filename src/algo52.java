import java.util.ArrayList;
import java.util.Collections;

/*
프로그래머스 : N으로 표현
 */
public class algo52 {

    static ArrayList<Integer> array = new ArrayList<>();
    static int min = Integer.MAX_VALUE;
    public static void dp(int N, int number, int count ,int sum){

        if(count > 8)
            return ;
        if(sum == number){
            min = Math.min(count, min);
            return ;
        }

        int temp = 0;
        for(int i = 0 ; i < 8 ; i++) {
            if(count + i < 8) {
                temp = 10 * temp + N;
                dp(N, number, count + (i + 1), sum + temp);
                dp(N, number, count + (i + 1), sum - temp);
                dp(N, number, count + (i + 1), sum / temp);
                dp(N, number, count + (i + 1), sum * temp);
            }
        }

    }
    public static int solution(int N, int number) {
        int answer = 0;
        dp(N, number, 0, 0);
        if(min == Integer.MAX_VALUE || min > 8)
            return - 1;
        else
            return min;

    }
    public static void main(String[] args) {
        int answer = solution(3, 31168);
    }
}
