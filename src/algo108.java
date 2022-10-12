import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class algo108 {

    /*
    백준 : 2696
    중앙값 구하기
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < T; i++){
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });

            // 내림차순 정렬
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });

            StringBuilder sb = new StringBuilder();
            int N = Integer.parseInt(br.readLine());


            for(int j = 0; j < (N / 10) + 1 ; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int k = 1;
                while(st.hasMoreTokens()) {
                    int num = Integer.parseInt(st.nextToken());
                    if (k % 2 == 1) {
                        minHeap.offer(num);
                    } else
                        maxHeap.offer(num);

                    if (minHeap.size() + maxHeap.size() == 1) {
                        sb.append(minHeap.peek() + " ");
                        k++;
                        continue;
                    }
                    // 1 5 (minheap)
                    // 2   (maxHeap)
                    // 만약 홀수 일때 이제 중앙값 계산하고 스왑진행해서 minHeap의 최댓값 이 중앙값이됨.
                    int temp1 = minHeap.peek();
                    int temp2 = maxHeap.peek();
                    if (temp1 >= temp2) {
                        minHeap.poll();
                        maxHeap.poll();
                        minHeap.offer(temp2);
                        maxHeap.offer(temp1);
                    }
                    if ((minHeap.size() + maxHeap.size()) % 2 == 1)
                        sb.append(minHeap.peek() + " ");
                    k++;
                }

            }
            System.out.println(N / 2 + 1);
            System.out.println(sb.toString());
        }
    }
}

