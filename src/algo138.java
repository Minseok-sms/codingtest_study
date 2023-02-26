//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Comparator;
//import java.util.PriorityQueue;
//
//public class algo138 {
//
//    /*
//    백준 : 1927
//    최소힙
//     */
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        int N = Integer.parseInt(br.readLine());
//
//
//        // 최소힙
//        //PriorityQueue<Integer> queue = new PriorityQueue<>();
//
//        // 최대힙
////        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
////            @Override
////            public int compare(Integer o1, Integer o2) {
////                return o2 - o1;
////            }
////        });
//
//
//
//        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                int abs1 = Math.abs(o1);
//                int abs2 = Math.abs(o2);
//
//                if(abs1 == abs2){
//                    return o1 - o2;
//                }else
//                    return abs1 - abs2;
//            }
//        });
//        for(int i = 0 ; i < N; i++){
//            int num = Integer.parseInt(br.readLine());
//            if(num == 0){
//                if(!queue.isEmpty()) {
//                    sb.append(queue.poll() + "\n");
//                }
//                else
//                    sb.append("0\n");
//            }else
//                queue.offer(num);
//        }
//        System.out.print(sb);
//    }
//}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class algo138 {

    /*
    백준 : 1927
    최소힙
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());


        // 최소힙
        //PriorityQueue<Integer> queue = new PriorityQueue<>();

        // 최대힙
//        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });
        ArrayList<Integer> array = new ArrayList<>();
        int[][] array2 = new int[2][3];
        Arrays.sort(array2, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                return o1[0] - o2[0];
            }
        });


        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int abs1 = Math.abs(o1);
                int abs2 = Math.abs(o2);

                if(abs1 == abs2){
                    return o1 - o2;
                }else
                    return abs1 - abs2;
            }
        });
        for(int i = 0 ; i < N; i++){
            int num = Integer.parseInt(br.readLine());
            if(num == 0){
                if(!queue.isEmpty()) {
                    sb.append(queue.poll() + "\n");
                }
                else
                    sb.append("0\n");
            }else
                queue.offer(num);
        }
        System.out.print(sb);
    }
}
