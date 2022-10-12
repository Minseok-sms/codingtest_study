import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class algo80 {

    static int N;
    static int[] array;
    static long MIN = Long.MAX_VALUE;
    static long MAX = Long.MIN_VALUE;
    /*
    백준 : 연산자끼어넣 (14888)
     */

    public static void dp(long sum,int depth, int plus, int minus, int mul, int div){

        if(depth == array.length) {
            MIN = Math.min(MIN, sum);
            MAX = Math.max(MAX, sum);
            return;
        }

        if(plus > 0)
            dp(sum + array[depth], depth + 1, plus - 1, minus ,mul, div);
        if(minus > 0)
            dp(sum - array[depth], depth + 1, plus , minus - 1 ,mul, div);
        if(mul > 0)
            dp(sum  * array[depth], depth + 1, plus, minus ,mul - 1, div);
        if(div > 0) {
            if(sum < 0)
                dp(-(-sum / array[depth]), depth + 1, plus, minus, mul, div - 1);
            else
                dp(sum / array[depth], depth + 1, plus, minus, mul, div - 1);
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0 ; i < N; i++)
            array[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int plus = Integer.parseInt(st.nextToken());
        int minus = Integer.parseInt(st.nextToken());
        int mul = Integer.parseInt(st.nextToken());
        int div = Integer.parseInt(st.nextToken());

        dp(array[0],1, plus, minus, mul, div );
        System.out.println(MAX);
        System.out.println(MIN);
    }
}
