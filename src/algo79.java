import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;



public class algo79 {
    static ArrayList<Integer> array = new ArrayList<>();
    static int globalCount = Integer.MAX_VALUE;
    public static void dp(int num,int sum, int count){
        if(sum == num){
            globalCount = Math.min(count, globalCount);
            return ;
        }
        if(sum > num)
            return;

        for(int i = 0 ; i < array.size(); i++){
            dp(num, sum + array.get(i), count + 1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < N ; i++)
            array.add(Integer.parseInt(br.readLine()));
        dp(M, 0, 0);
        if(globalCount == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(globalCount);

    }
}
