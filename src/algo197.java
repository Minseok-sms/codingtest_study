//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.StringTokenizer;
//
//public class algo197 {
//
//    /*
//    백준 : 22866
//    탑보기.
//     */
//    static int[] array;
//    static int N;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//        array = new int[N + 1];
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = 1; i <= N; i++)
//            array[i] = Integer.parseInt(st.nextToken());
//
//
//        //하나씩 돌면서 왼쪽 오른쪽에 자기보다 높은 건물수 넣자.
//        for(int i = 1; i <= N; i++){
//
//            int currH = array[i];
//            int firstMax = currH;
//            ArrayList<Integer> buildings = new ArrayList<>();
//            // 왼쪽 확인
//            for(int j = i - 1; j >= 1; j--){
//                //자기보다 큰 건물 만낫을때랑, 그이후 건물은 firstMax보다 커야한다.
//                if(array[j] > currH && array[j] > firstMax){
//                    firstMax = array[j];
//                    buildings.add(j);
//                }
//            }
//            // 우측 확인
//            firstMax = currH;
//            for(int j = i + 1; j <= N; j++){
//                if(array[j] > currH && array[j] > firstMax){
//                    firstMax = array[j];
//                    buildings.add(j);
//                }
//            }
//            // 빌딩을 확인후 가장 가까운 건물의 번호를 -> 작은번호
//            int lowFloor = 0;
//            if(buildings.size() == 0)
//                sb.append("0\n");
//            else{
//                Collections.sort(buildings);
//
//                int maxLength = Integer.MAX_VALUE;
//                for(int j = 0 ; j < buildings.size(); j++){
//                    int diff = Math.abs(i - buildings.get(j));
//                    int check = maxLength;
//                    maxLength = Math.min(diff, maxLength);
//
//                    if(check != maxLength){
//                        lowFloor = buildings.get(j);
//                    }
//                }
//                sb.append(buildings.size() + " " + lowFloor + "\n");
//            }
//        }
//        System.out.println(sb);
//    }
//
//}

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.lang.*;

class Building{
    int index;
    int height;

    public Building(int index, int height) {
        this.index = index;
        this.height = height;
    }
}

public class algo197 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Stack<Building> stack = new Stack<>();
        String[] s = br.readLine().split(" ");


        Building[] arr = new Building[n+1];
        int [][] indexAndGap = new int[n+1][2];
        int [] cnt = new int[n+1];
        for(int i=1; i<=n; i++){
            arr[i] = new Building(i, Integer.parseInt(s[i-1]));
            Arrays.fill(indexAndGap[i],100001);
        }

        for(int i=1; i<=n; i++){
            while(!stack.isEmpty() && stack.peek().height<=arr[i].height){
                stack.pop();
            }

            cnt[i] += stack.size();

            if(!stack.isEmpty()){
                int gap = Math.abs(stack.peek().index-i);
                if(gap<indexAndGap[i][1]){
                    indexAndGap[i][0] = stack.peek().index;
                    indexAndGap[i][1] = gap;
                }
                else if(gap == indexAndGap[i][1] && stack.peek().index<indexAndGap[i][0]){
                    indexAndGap[i][0] = stack.peek().index;
                }
            }

            stack.push(arr[i]);
        }

        stack =new Stack<>();
        for(int i=n; i>=1; i--){
            while(!stack.isEmpty() && stack.peek().height<=arr[i].height){
                stack.pop();
            }

            cnt[i] +=stack.size();

            if(!stack.isEmpty()){
                int gap = Math.abs(stack.peek().index-i);
                if(gap<indexAndGap[i][1]){
                    indexAndGap[i][0] = stack.peek().index;
                    indexAndGap[i][1] = gap;
                }
                else if(gap == indexAndGap[i][1] && stack.peek().index<indexAndGap[i][0]){
                    indexAndGap[i][0] = stack.peek().index;
                }
            }
            stack.push(arr[i]);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++){ 
            if(cnt[i]==0){
                sb.append(0).append("\n");
            }
            else{
                sb.append(cnt[i]).append(" ").append(indexAndGap[i][0]).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}