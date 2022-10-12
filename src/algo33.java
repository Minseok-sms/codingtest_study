import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;



/*
       신세계 1번
 */
public class algo33 {
    public static int solution(int[] v, int a, int b){
        int answer = 0;
        Arrays.sort(v);
        // 5 5 4
        while(true){
            for(int i = v.length - 1; i >= 0 ; i--){
                if(i == v.length - 1)
                    v[i] -= a;
                else
                    v[i] -= b;
            }
            int zeroCount = 0;
            int correctCount = 0;
            for(int i = 0; i < v.length; i++){
                if(v[i] < 0)
                    zeroCount++;
                else
                    correctCount++;
            }
            if(zeroCount > 0)
                break;

            if(correctCount == v.length)
                answer++;
            Arrays.sort(v);

        }
        return answer;
    }
    public static int solution2(int[] v, int a, int b){
        Arrays.sort(v);
        int num = 0;
        if(v[v.length - 1] % a == 0){
            num = v[v.length - 1] / a;
            // a = 4 b = 2
        }else{ // 13 / 4 == 4,
            num += v[v.length - 1] / a;
            int temp = v[v.length - 1] % a % b;
            if(temp == 0)
                num++;
        }
        return num;

    }
    public static void main(String[] args) {
        int[] array = {9,10,14};
        System.out.println(solution2(array, 2, 1));
        System.out.println(solution(array,2,1));
    }
}
