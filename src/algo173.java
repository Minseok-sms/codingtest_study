//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
//public class algo173 {
//    /*
//    백준 : 13335
//    트럭
//     */
//    static int N, W, L;
//    static ArrayList<Integer> array = new ArrayList<>();
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        N = Integer.parseInt(st.nextToken());
//        W = Integer.parseInt(st.nextToken());
//        L = Integer.parseInt(st.nextToken());
//
//        st = new StringTokenizer(br.readLine(), " ");
//        for(int i = 0 ; i  < N; i++)
//            array.add(Integer.parseInt(st.nextToken()));
//
//        int time = 0;
//        int idx = 0;
//        int progWeight = 0;
//        int waitQueue = 0;
//        int count = array.size();
//        Queue<Integer> queue = new LinkedList<>();
//
//        while(true){
//            int item = array.get(idx);
//            progWeight += item;
//            time++;
//
//
//            if(progWeight <= L && queue.size() <= W) {
//                queue.offer(item);
//                waitQueue++;
//                if(idx < N - 1)
//                     idx++;
//            }else{
//                progWeight -= item;
//                waitQueue++;
//            }
//            if(waitQueue == W){
//                if(queue.size() == W)
//                    waitQueue -= 1;
//                else
//                    waitQueue = 0;
//                if(!queue.isEmpty()) {
//                    int temp = queue.poll();
//                    progWeight -= temp;
//                    count--;
//                    if(count == 0)
//                        break;
//
//                }
//                else
//                    break;
//            }
//
//            // 지나갔으면 큐에서 빼야함
//        }
//        System.out.println(time + 1);
//
//
//    }
//}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class algo173 {
    /*
    백준 : 13335
    트럭
     */
    static int N, W, L;
    static ArrayList<Integer> array = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i  < N; i++)
            array.add(Integer.parseInt(st.nextToken()));

        int time = 0;
        int idx = 0;
        int progWeight = 0;
        Queue<Integer> queue = new LinkedList<>();

        //다리길이만큼 queue에 0 을추가해줌
        for(int i = 0 ; i < W ; i++){
            queue.add(0);
        }

        while(true){
           time++;
           // 큐에서 하나뺴고 다음트럭을 넣으려고하자
            progWeight -= queue.poll();
            if(progWeight + array.get(idx) <= L){
                queue.offer(array.get(idx));
                progWeight += array.get(idx);
                if(--N == 0)
                    break;
                idx++;
            }else{
                queue.offer(0);
            }
        }
        time += queue.size();
        System.out.println(time);


    }
}

