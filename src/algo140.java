import java.util.LinkedList;
import java.util.Queue;

public class algo140 {
    /*
    프로그래머스 : 두큐합 같게 만들기
     */
    public static int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        long sum = 0;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long s1 = 0, s2 = 0;

        for(int i = 0 ; i < queue1.length; i++){
            q1.add(queue1[i]);
            q2.add(queue2[i]);
            s1 += queue1[i];
            s2 += queue2[i];
        }

        sum = (s1 + s2);
        //만약 총합이 홀수이면 /2 가안되니까 합을 같게할수없다. -1
        if(sum % 2 == 1){
            answer = -1;
            return answer;
        }

        sum = sum / 2;
        int count = 0;
        int limit = queue1.length * 2;
        int p1 = 0, p2 = 0;
        boolean check = false;
        while(p1 <= limit && p2 <= limit){
            if(s2 > sum){
                int temp = q2.poll();
                q1.add(temp);
                s2 -= temp;
                s1 += temp;
                p1++;
            }else if(s1 > sum){
                int temp = q1.poll();
                q2.add(temp);
                s1 -= temp;
                s2 += temp;
                p2++;
            }else{
                return p1 + p2;
            }

        }
        return -1;
    }

    public static void main(String[] args) {

    }
}

// 100,000,000