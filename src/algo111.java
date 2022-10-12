import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class algo111 {

    /*
    프로그래머스 : 이중우선순위큐
     */

    public static int[] solution(String[] operations) {
        int[] answer = {};

        // 최댓값 반환 queue
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });


        for(int i = 0 ; i < operations.length; i++){
            StringTokenizer st = new StringTokenizer(operations[i], " ");
            String order = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            char a = order.charAt(0);

            if(a == 'I'){
                maxHeap.offer(num);
                //minHeap.offer(num);
            }else if(a == 'D'){
                if(maxHeap.size() == 0)
                    continue;

                if(num == 1) {
                    maxHeap.poll();
                }
                else {
                    Stack<Integer> stack = new Stack<>();
                    while(maxHeap.size() > 1)
                        stack.push(maxHeap.poll());
                    // 최솟값 제거완료
                    maxHeap.poll();
                    while(!stack.isEmpty())
                        maxHeap.offer(stack.pop());
                }
            }
        }
        answer = new int[2];
        if(maxHeap.size() == 0){
            answer[0] = 0;
            answer[1] = 0;
        }else{
            int maxNum = maxHeap.poll();
            while(maxHeap.size() > 1)
                maxHeap.poll();
            int minNum = maxHeap.poll();

            answer[0] = maxNum;
            answer[1] = minNum;
        }

        return answer;
    }
    public static void main(String[] args) {

    }
}
