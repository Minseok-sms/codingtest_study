import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class algo39 {

    static int P, Q, X, Y;
    static long N;
    static HashMap<Long, Long> map = new HashMap<>();
    public static long DP(long num){
        if(num <= 0)
            return 1;
        if(map.containsKey(num))
            return map.get(num);

        // 10000000 / 2 = 5,000,000 - 10,000,000
        long a = (long)Math.floor(num / P) - X;
        long b = (long)Math.floor(num / Q) - Y;

        map.put(num, DP(a) + DP(b));
        return map.get(num);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Long.parseLong(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        System.out.println(DP(N));
    }
}
