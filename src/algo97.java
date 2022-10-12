//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.PriorityQueue;
//import java.util.Stack;
//
//public class algo97 {
//    /*
//    백준 : 1655
//    가운데를 말해요
//
//     */
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(br.readLine());
//        ArrayList<Integer> answer = new ArrayList<>();
//        PriorityQueue<Integer> queue = new PriorityQueue<>();
//        Stack<Integer> stack = new Stack<>();
//        for(int i = 0 ; i < N; i++){
//            int temp = Integer.parseInt(br.readLine());
//            queue.add(temp);
//
//            if(queue.size() % 2 == 1){
//                int insertNum = 0;
//                int queueSize = queue.size();
//                for(int j = 0 ; j <= queueSize / 2; j++){
//                    insertNum = queue.poll();
//                    stack.add(insertNum);
//                }
//                while(!stack.isEmpty())
//                    queue.add(stack.pop());
//                answer.add(insertNum);
//            }else{
//                int insertNum = 0;
//                int queueSize = queue.size();
//                for(int j = 0 ; j <= queueSize / 2 - 1 ; j++){
//                    insertNum = queue.poll();
//                    stack.add(insertNum);
//                }
//                while(!stack.isEmpty())
//                    queue.add(stack.pop());
//                answer.add(insertNum);
//            }
//        }
//        for(int num : answer)
//            System.out.println(num);
//
//
//    }
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class algo97 {
    /*
    백준 : 1655
    가운데를 말해요

     */



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        //  minHeap -> 꺼낼때 작은값부터
        //  maxHeap -> 꺼낼때 큰값부터

        PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> o1 - o2);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        StringBuilder sb = new StringBuilder();

        // 10 8 5 3 5 -1

        for(int i = 0 ; i < N ; i++){
            int num = Integer.parseInt(br.readLine());

            if(minHeap.size() == maxHeap.size())
                maxHeap.offer(num);
            else
                minHeap.offer(num);

            if(!minHeap.isEmpty() && !maxHeap.isEmpty()){
                if(minHeap.peek() < maxHeap.peek()){
                    int temp = minHeap.poll();
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(temp);
                }
            }
            sb.append(maxHeap.peek() + "\n");
        }
        System.out.println(sb);
    }
}
