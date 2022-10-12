//
//import java.util.ArrayList;
//import java.util.HashMap;
//
///*
//프로그래머스 : 합승 택시요금
// */
//public class algo60 {
//
//    static ArrayList<Node>[] array;
//    static ArrayList<Integer> check = new ArrayList<>();
//    static boolean[] visited;
//    static ArrayList<Road> roadA = new ArrayList<>();
//    static ArrayList<Road> roadB = new ArrayList<>();
//    static int min = Integer.MAX_VALUE;
//    static class Node{
//        int end;
//        int value;
//        Node(int end,int value){
//            this.end = end;
//            this.value = value;
//        }
//    }
//
//    static class Road{
//        ArrayList<Integer> array = new ArrayList<>();
//        int value;
//        Road(ArrayList<Integer> number, int value){
//            this.array = number;
//            this.value = value;
//        }
//    }
//    public static void dfs(int start ,int target, int sum, ArrayList<Road> road){
//
//        visited[start] = true;
//        if(start == target){
//            ArrayList<Integer> temp = new ArrayList<>();
//            for(int a : check){
//                temp.add(a);
//            }
//            road.add(new Road(temp, sum));
//            return ;
//        }
//        for(int i = 0 ; i < array[start].size(); i++){
//            if(visited[array[start].get(i).end] == true)
//                continue;
//            check.add(array[start].get(i).end);
//            visited[array[start].get(i).end] = true;
//            dfs(array[start].get(i).end, target, sum + array[start].get(i).value, road);
//            visited[array[start].get(i).end] = false;
//            check.remove(Integer.valueOf(array[start].get(i).end));
//
//        }
//    }
//    public static int solution(int n, int s, int a, int b, int[][] fares) {
//        int answer = 0;
//        array = new ArrayList[n + 1];
//        for(int i = 0 ; i < n + 1; i++)
//            array[i] = new ArrayList<>();
//
//        visited = new boolean[n + 1];
//        int[][] faresCopy = new int[n + 1][n + 1];
//        for(int i = 0; i < fares.length; i++){
//
//                array[fares[i][0]].add(new Node(fares[i][1], fares[i][2]));
//                array[fares[i][1]].add(new Node(fares[i][0], fares[i][2]));
//                faresCopy[fares[i][0]][fares[i][1]] = fares[i][2];
//                faresCopy[fares[i][1]][fares[i][0]] = fares[i][2];
//
//        }
//
//        check.add(s);
//        dfs(s, a,0, roadA);
//        visited = new boolean[n + 1];
//        check = new ArrayList<>();
//        check.add(s);
//        dfs(s, b, 0, roadB);
//
//        // roadA = 4,1,5,3,2 10
//        // roadA = 4,2 66
//
//
//        // roadB = 4,1,5,6 44;
//        for(int i = 0 ; i < roadA.size(); i++){
//            for(int j = 0 ; j < roadB.size(); j++){
//                Road first = roadA.get(i);
//                Road last = roadB.get(j);
//
//                int count = first.array.size() > last.array.size() ? last.array.size() : first.array.size();
//                int comp = 0;
//                for(int k = 0 ; k < count; k++){
//                    if(first.array.get(k) == last.array.get(k))
//                        comp++;
//                }
//                int total = 0;
//                if(comp <= 1)
//                    min = Math.min(min, first.value + last.value);
//                else{
//
//                    for(int k = 0 ; k < comp - 1; k++){
//                        int num1 = first.array.get(k);
//                        int num2 = first.array.get(k + 1);
//                        total += faresCopy[num1][num2];
//                    }
//                    min = Math.min(min, first.value + last.value - total);
//                }
//            }
//        }
//        System.out.println(min);
//        return answer;
//    }
//    public static void main(String[] args) {
//        int[][] fares = {{4,1,10}, {3,5,24}, {5,6,2}, {3,1,41}, {5,1,24}, {4, 6, 50}, {2 ,4 ,66}, {2, 3, 22}, {1, 6, 25}};
//        solution(6,4,6,2,fares);
//    }
//}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
프로그래머스 : 합승 택시요금
 */
public class algo60 {

    static int MAX = Integer.MAX_VALUE;
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        int[][] dp = new int[n + 1][n + 1];
        for(int i = 0 ; i <= n; i++){
            Arrays.fill(dp[i], MAX);
            dp[i][i] = 0;
        }

        for(int i = 0 ; i < fares.length; i++){

            int first = fares[i][0];
            int last = fares[i][1];
            int value = fares[i][2];

            dp[first][last] = value;
            dp[last][first] = value;
        }

        for(int k = 1 ; k <= n; k++){
            for(int i = 1; i <= n ;i++){
                for(int j = 1; j <= n; j++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        // 만약 가운데 통과하는게 없으면
        answer = dp[s][a] + dp[s][b];

        for(int i = 1; i <= n; i++){
            answer = Math.min(answer, dp[s][i] + dp[i][a] + dp[i][b]);
        }
        System.out.println(answer);
        return answer;
    }
    public static void main(String[] args) {
        int[][] fares = {{4,1,10}, {3,5,24}, {5,6,2}, {3,1,41}, {5,1,24}, {4, 6, 50}, {2 ,4 ,66}, {2, 3, 22}, {1, 6, 25}};
        solution(6,4,6,2,fares);
    }
}
