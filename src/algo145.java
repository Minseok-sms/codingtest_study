
import java.util.Comparator;
import java.util.PriorityQueue;

public class algo145 {
    /*
    프로그래머스 : 야근지수
     */
    public static long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for(int i : works)
            queue.offer(i);

        while(n > 0){
            int temp = queue.poll();
            if(temp == 0)
                break;
            queue.offer(temp - 1);
            n--;
        }
        while(!queue.isEmpty()) {
            answer += Math.pow(queue.poll(), 2);
        }
        return answer;
    }
    public static void main(String[] args) {
        int[] works = {1, 1};
        int n = 3;
        solution(n, works);
    }
}



// 09 : 00 : 4
