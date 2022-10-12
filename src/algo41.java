//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.StringTokenizer;
//
//import static java.lang.Math.abs;
//
//public class algo41 {
//
//    // -39 -37 -29 -28 -6 2 11
//    // 11 + 11 + 6 + 6 + 29 + 29 + 39
//    // 34 + 58 + 39 = 73 + 58 = 131;
//    // 5 / 2 = 2
//
//    // -45 -26 -18 -9 -4 22 40 50
//
//    // 45 26 18 9 4
//    // 22
//
//    // 18 + 45 + 45 + 50
//
//    // 2 6 11 28 29 37 39
//    // 6 + 6 + 28 + 28 + 37 + 37 + 39
//    // 4 + 11 + 11 + 29 + 29 + 39
//    // 26 + 58 + 39
//    // 84 + 39 = 123
//
//    // -1 3 4 5 6 11
//    //  1 + 1 + 3+ 3+ 5+ 5+ 11
//
//
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//        ArrayList<Integer> array = new ArrayList<>();
//
//        st = new StringTokenizer(br.readLine(), " ");
//        for (int i = 0; i < N; i++)
//            array.add(Integer.parseInt(st.nextToken()));
//        Collections.sort(array);
//        int start = abs(array.get(0));
//        int end = abs(array.get(array.size() - 1));
//        int answer = 0;
//
//
//        if(array.size() == 1){
//            answer = abs(array.get(0));
//        }
//        else if(end > start){
//            int indexCount = 0;
//            int minusCount = 0;
//            for(int i = 0 ; i < array.size(); i++){
//                if(array.get(i) < 0)
//                    minusCount++;
//                else
//                    break;
//            }
//            // -45 -26 -18 -9 -4 22 40 50
//
//            for(int i = 0 ; i < minusCount / M ; i++){
//                for(int j = 0 ; j < M ; j++){
//                    if(j == 0)
//                        answer += abs(array.get(indexCount++)) * 2;
//                    else
//                        indexCount++;
//                }
//            }
//            for(int i = 0 ; i < minusCount % M ;i++){
//                if(i == 0)
//                     answer += abs(array.get(indexCount++)) * 2;
//                else
//                    indexCount++;
//            }
//            int plusCount = array.size() - minusCount;
//            for(int i = 0 ; i < plusCount % M; i++){
//                if(i == 0)
//                    answer += array.get(indexCount++) * 2;
//                else
//                    indexCount++;
//            }
//            for(int i = 0 ; i < plusCount / M; i++){
//                for(int j = 0 ; j < M ; j++){
//                    if(j == M - 1) {
//                        if (indexCount == array.size() - 1)
//                            answer += array.get(indexCount++);
//                        else
//                            answer += array.get(indexCount++) * 2;
//                    }
//
//                    else
//                        indexCount++;
//                }
//            }
//        }
//        // -39 -37 -29 -28 -6 2 11
//        // 11 + 11 + 6 + 6 + 29 + 29 + 39
//        else if (start > end){
//            int indexCount = 0;
//            int minusCount = 0;
//            for(int i = 0 ; i < array.size(); i++){
//                if(array.get(i) < 0)
//                    minusCount++;
//                else
//                    break;
//            }
//
//            for(int i = 0 ; i < minusCount / M ; i++){
//                for(int j = 0 ; j < M ; j++){
//                    if(j == 0) {
//                        if (indexCount == 0)
//                            answer += abs(array.get(indexCount++));
//                        else
//                            answer += abs(array.get(indexCount++)) * 2;
//                    }
//                    else
//                        indexCount++;
//                }
//            }
//
//            // -39 -37 -29 -28 -6 2 11
//            // 11 + 11 + 6 + 6 + 29 + 29 + 39
//
//            for(int i = 0 ; i < minusCount % M ;i++){
//                if(i == 0)
//                    answer += abs(array.get(indexCount++)) * 2;
//                else
//                    indexCount++;
//            }
//            int plusCount = array.size() - minusCount;
//            for(int i = 0 ; i < plusCount % M; i++){
//                if(i == 0)
//                    answer += array.get(indexCount++) * 2;
//                else
//                    indexCount++;
//            }
//            for(int i = 0 ; i < plusCount / M; i++){
//                for(int j = 0 ; j < M ; j++){
//                    if(j == M - 1) {
//                        answer += array.get(indexCount++) * 2;
//                    }
//                    else
//                        indexCount++;
//                }
//            }
//        }
//        System.out.println(answer);
//    }
//
//}
// -45 -26 -18 -9 -4 22 40 50
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class algo41 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Integer> plus = new ArrayList<>();
        ArrayList<Integer> minus = new ArrayList<>();

        st = new StringTokenizer(br.readLine(), " ");
        int findMax = 0;
        int answer = 0;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(Math.abs(num) > Math.abs(findMax)){
                findMax = num;
            }
            if(num > 0)
                plus.add(num);
            else
                minus.add(-num);
        }
        Collections.sort(plus, Collections.reverseOrder());
        Collections.sort(minus, Collections.reverseOrder());

        // -39 -37 -29 -28 -6 2 11

        // 37 29 28 6
        // 11 2
        // 50 40 22
        // 45 26 18 9 4
        // 50
        if(findMax < 0){
            answer += Math.abs(findMax);
            for(int i = 0 ; i < M ; i++)
                if(!minus.isEmpty())
                    minus.remove(0);

        }else{
            answer += Math.abs(findMax);
            for(int i = 0 ; i < M; i++)
                if(!plus.isEmpty())
                    plus.remove(0);
        }

        // 5 4 3
        // 1
        int idx = 0;
        while(!plus.isEmpty()){
            if(idx == 0) {
                answer += plus.get(idx) * 2;
                plus.remove(idx);
            }else{
                plus.remove(idx / M);
            }
            idx++;
            idx %= M;
        }
        idx = 0;
        while(!minus.isEmpty()){
            if(idx == 0){
                answer += minus.get(idx) * 2;
                minus.remove(idx);
            }else{
                minus.remove(idx / M);
            }
            idx++;
            idx %= M;
        }
        System.out.println(answer);
    }

}