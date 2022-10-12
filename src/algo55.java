//public class algo55 {
//    public static int solution(int[] food_times, long k) {
//        int answer = 0;
//        int count = 0;
//        long sec = 0;
//        stop:
//        while(true){
//            count = count % food_times.length;
//            if(sec == k){
//                if(food_times[count] == 0){
//                    for(int i = 0 ; i < food_times.length; i++){
//                        count = count % food_times.length;
//                        if(food_times[count] != 0) {
//                            answer = count + 1;
//                            break stop;
//                        }
//                        else
//                            count++;
//                    }
//                    // 싹다 0이면
//                    answer = -1;
//                    break stop;
//                }else {
//                    answer = count + 1;
//                    break stop;
//                }
//            }
//            if(food_times[count] > 0){
//                food_times[count]--;
//                count++;
//                sec++;
//            }else
//                count++;
//        }
//        System.out.println(answer);
//        return answer;
//    }
//
//    public static void main(String[] args) {
//        int[] foodtimes = {3,1,2};
//        solution(foodtimes, 5);
//    }
//}

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
    프로그래머스 : 무지의 먹방라이브
 */
public class algo55 {
    static class Food implements Comparable<Food>{
        int idx;
        int time;

        Food(int idx, int time){
            this.idx = idx;
            this.time = time;
        }

        @Override
        // time에 따라 오름차순 정렬이됨.
        public int compareTo(Food aFood){
            return this.time - aFood.time;
        }

    }
    static List<Food> arr;
    public static int solution(int[] food_times, long k) {
        int answer = 0;
        arr = new LinkedList<>();
        for(int i = 0 ; i < food_times.length; i++){
            arr.add(new Food(i + 1, food_times[i]));
        }
        Collections.sort(arr);

        for(Food f : arr){
            System.out.println(f.idx + " " + f.time);
        }

        int prev_time = 0;
        int idx = 0;
        int n = food_times.length;

        for(Food f : arr){
            long diff = f.time - prev_time;
            if(diff != 0){
                long cost = diff * n;
                if(cost <= k){
                    k -= cost;
                    prev_time = f.time;
                }else{
                    k %= n;
                    arr.subList(idx, food_times.length).sort((Food a, Food b) -> {
                        return a.idx - b.idx;
                    });
                    return arr.get(idx + (int)k).idx;
                }
            }
            idx++;
            n--;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] foodtimes = {3,1,2};
        System.out.println(solution(foodtimes, 5));
    }
}
