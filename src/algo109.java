import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

import static java.lang.System.exit;

public class algo109 {

    /*
    백준 : 1715
    카드정렬하기
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        for(int i = 0 ; i < N; i++){
            int num = Integer.parseInt(br.readLine());
            queue.offer(num);
        }
        if(queue.size() == 1){
            System.out.println(0);
            exit(0);
        }else if(queue.size() == 2){
            System.out.println(queue.poll() + queue.poll());
            exit(0);
        }
        int answer = 0;

        while(queue.size() > 1){
            int num1 = queue.poll();
            int num2 = queue.poll();
            answer += num1 + num2;
            queue.offer(num1 + num2);
        }
        System.out.println(answer);
    }
}


// 10 20 40 50
//
//  30 + (30 + 40)
//    100 + 50
// 30    90
//    120

// 10 20 40
//  30 + ( 30 + 40 ) == 100


// 10 20 40 50
//   30    90


// 30 + 70 + 120 = 220
// 30 + 90 + 120