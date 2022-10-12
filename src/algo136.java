import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class algo136 {
    /*
    백준 : 1644
    소수의 연속합
     */

    static ArrayList<Integer> array = new ArrayList<>();
    static int N;
    public static int findAns(){
        int start=0, end=0, sum=0, count=0;
        while(true) {
            if(sum >= N ) sum -= array.get(start++);
            else if(end == array.size()) break;
            else sum += array.get(end++);
            if(N==sum) count++;
        }

        return count;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        boolean[] prime = new boolean[N + 1];
        prime[0] = prime[1] = true;

        for(int i = 2; i * i <= N; i++){
            if(prime[i] == false){
                for(int j = i * i; j <= N; j += i)
                    prime[j] = true;
            }
        }
        for(int i = 1; i<= N; i++){
            if(prime[i] == false)
                array.add(i);
        }

        int answer = findAns();
        System.out.println(answer);
    }
}


// 12 + 11 + 13
// 23 + 13 = 36 + 17 = 53