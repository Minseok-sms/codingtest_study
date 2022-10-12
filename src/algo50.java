import java.util.ArrayList;
import java.util.Collections;

public class algo50 {

    static long globalCount = 0;
    //연승 카운트
    static int global = 1;

    public static void dp(long[] players, int i ,long power, int k, long count , long depth){

        if(count == depth){
            globalCount = Math.max(globalCount, power);
            return ;
        }
        //power가 플레이어보다 작을때 + k

        if(players[i] > power) {
            global = 1;
            dp(players, i + 1, power + k, k, count + 1, depth);
        }
        else{
            // power가 플레이어보다 클때, 연승할경우
            dp(players, i + 1,power + (global++), k, count + 1, depth);
            global = 1;
            // power가 플레이어보다 클때, 강제 패배하는경우
            dp(players, i + 1,power + k, k, count + 1, depth);
        }
    }
    public static long solution(long[] players, int power, int k){
        long answer = 0;
        int depth = players.length;
        dp(players,0,power, k, 0 ,depth);
        //Collections.sort(array, Collections.reverseOrder());
       // System.out.println(array.get(0));
        answer = globalCount;
        System.out.println(answer);
        return answer;
    }
    public static void main(String[] args) {
        long[] players = {1,2,1,2,1};
        solution(players, 2, 100);
    }
}


/*
import java.util.ArrayList;
import java.util.Collections;

public class algo50 {
    static int[] winner;
    static ArrayList<Integer> array = new ArrayList<>();
    //연승 카운트
    static int global = 1;

    public static void dp(int[] players,int i ,int power, int k, int count ,int depth){

        if(count == depth){
            array.add(power);
            return ;
        }
        //power가 플레이어보다 작을때 + k

        if(players[i] > power) {
            global = 1;
            dp(players, i + 1, power + k, k, count + 1, depth);
        }
        else{
            // power가 플레이어보다 클때, 연승할경우
            dp(players, i + 1,power + (global++), k, count + 1, depth);
            global = 1;
            // power가 플레이어보다 클때, 강제 패배하는경우
            dp(players, i + 1,power + k, k, count + 1, depth);
        }
    }
    public static long solution(long[] players, int power, int k){
        int answer = 0;
        int depth = players.length;
        winner = new int[depth];
        for(int i = 0 ; i < depth; i++)
            winner[i] = i + 1;
        dp(players,0,power, k, 0 ,depth);
        Collections.sort(array, Collections.reverseOrder());
        System.out.println(array.get(0));
        answer = array.get(0);
        return answer;
    }
    public static void main(String[] args) {
        int[] players = {1,2,1,2,1};
        solution(players, 2, 100);
    }
}

 */