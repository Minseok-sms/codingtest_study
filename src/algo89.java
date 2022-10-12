import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class algo89 {

    /*
    프로그래머스 이분탐색 : 징검다리

     */

    public static int binearySearch(int distance, int[] rocks, int n){
        long answer = 0;
        long left = 1, right = distance;
        long mid = 0;

        while(left <= right){
            int cnt = 0;
            int prev = 0;
            mid = (left + right) / 2;

            for(int i = 0; i < rocks.length; i++){
                if(rocks[i] - prev < mid)
                    cnt++;
                else
                    prev = rocks[i];

            }

            if(distance - prev < mid)
                cnt++;

            if(cnt <= n){
                answer = Math.max(answer, mid);
                left = mid + 1;
            }else
                right = mid - 1;
        }

        return (int) answer;
    }
    public static int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        return binearySearch(distance, rocks, n);
    }
    public static void main(String[] args) {

    }
}




//
//public class algo89 {
//
//    /*
//       프로그래머스 이분탐색 : 징검다리
//     */
//    // 2 11 14 25
//    //2 9 3  11
//
//    // 0 2 11 14 17 21 25
//    // right == 11;
//    // mid = 5;
//
//    static ArrayList<Integer> array = new ArrayList<>();
//    static boolean[] visited;
//    static int[] eraseRock;
//    static int maxDistance = 0;
//
//    public static void dfs(int start,int[] rocks,int n, int depth){
//
//        // 0, 2, 11, 14, 25
//        if(depth == n){
//            // 바위두개 제거
//            for(int k = 0 ; k < eraseRock.length; k++)
//                array.remove(array.indexOf(eraseRock[k]));
//
//            int min = Integer.MAX_VALUE;
//            for(int k = 1 ; k < array.size(); k++){
//                int temp = array.get(k) - array.get(k - 1);
//                min = Math.min(temp, min);
//            }
//
//            // 거리의 최솟값중 가장 큰값
//            maxDistance = Math.max(maxDistance, min);
//
//            // 바위 다시 두개 넣고 정렬
//            for(int k = 0 ; k < eraseRock.length; k++)
//                array.add(eraseRock[k]);
//            Collections.sort(array);
//
//            return ;
//        }
//        for(int i = start; i < rocks.length; i++){
//            if(visited[i] == true)
//                continue;
//            visited[i] = true;
//            eraseRock[depth] = rocks[i];
//            dfs(i, rocks, n, depth + 1);
//            visited[i] = false;
//        }
//    }
//    public static int solution(int distance, int[] rocks, int n) {
//        Arrays.sort(rocks);
//        array.add(0);
//        for(int i = 0 ; i < rocks.length; i++)
//            array.add(rocks[i]);
//        array.add(distance);
//
//        visited = new boolean[rocks.length];
//        eraseRock = new int[n];
//        dfs(0, rocks,n, 0);
//        System.out.println(maxDistance);
//        return maxDistance;
//    }
//    public static void main(String[] args) {
//        int[] rocks = {2,14,11,21,17};
//        int n = 2;
//        int distance = 25;
//        solution(distance, rocks, n);
//    }
//}
/*

import java.util.*;
class Solution {
    static boolean[] visited;
    static ArrayList<Integer> array = new ArrayList<>();
    static ArrayList<Integer> globalArr = new ArrayList<>();
    public void dfs(int start, int count, int depth, int[] rocks, int distance){

        if(count == depth){
            ArrayList<Integer> temp = new ArrayList<>();
            for(int i = 0 ; i < array.size(); i++)
                temp.add(array.get(i));
            Collections.sort(temp);
            int num = distance;
            for(int i = 1; i <temp.size(); i++){
                if(temp.get(i) - temp.get(i - 1) <= num)
                    num = temp.get(i) - temp.get(i - 1);
            }
            globalArr.add(num);
        }
        for(int i = start; i < rocks.length; i++){
            if(visited[i] == true)
                continue;

            visited[i] = true;
            array.add(rocks[i]);
            dfs(i, count + 1, depth, rocks, distance);
            for(int j = 2 ; j < array.size(); j++){
                if(array.get(j) == rocks[i])
                    array.remove(j);
            }
            visited[i] = false;
        }
    }
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        array.add(0);
        array.add(distance);
        visited = new boolean[rocks.length];
        dfs(0,0, rocks.length - n, rocks, distance);
        Collections.sort(globalArr);
        answer = globalArr.get(globalArr.size() - 1);
        return answer;
    }
}
 */