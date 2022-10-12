import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class algo36 {

    static HashMap<Long, Long> map = new HashMap<>();
    static int P,Q;
    public static long DP(long num){


        // A10000000 = A[5000000
        if(num == 0)
            return 1;
        if(map.containsKey(num))
            return map.get(num);

        long a = (long)Math.floor(num / P);
        long b = (long)Math.floor(num / Q);

        map.put(num, DP(a) + DP(b));
        return map.get(num);
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long N = Long.parseLong(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        System.out.println(DP(N));
    }
}
