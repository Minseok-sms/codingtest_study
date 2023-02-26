import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class algo189 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] array = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < N; i++)
            array[i] = Integer.parseInt(st.nextToken());
        int answer = -Integer.MAX_VALUE;

        int[] sum = new int[ N + 1];
        for(int i = 1; i <= N ; i++)
            sum[i] = sum[i - 1] + array[i - 1];
        for(int i = K ; i <= N; i++){

            answer = Math.max(answer, sum[i] - sum[i - K]);
        }
        System.out.println(answer);



    }
}
