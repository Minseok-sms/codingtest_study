import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class algo99 {

    /*
    백준 : 11286
    절대값 힙
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int abs1 = Math.abs(o1);
                int abs2 = Math.abs(o2);

                if(abs1 > abs2)
                    return abs1 - abs2;
                else if(abs1 == abs2)
                    return o1 - o2;
                else
                    return -1;
            }
        });
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(br.readLine());
            if(num == 0){
                if(queue.size() == 0)
                    System.out.println(0);
                else
                    System.out.println(queue.poll());
            }else{
                queue.offer(num);
            }
        }

    }
}
