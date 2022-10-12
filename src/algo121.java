import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class algo121 {
    /*
    백준 : 1003
     피보나치 함수
    */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            int[] zeroCount = new int[41];
            int[] oneCount = new int[41];

            zeroCount[0] = 1;
            zeroCount[1] = 0;

            oneCount[0] = 0;
            oneCount[1] = 1;

            for (int j = 2; j <= N; j++) {
                zeroCount[j] = zeroCount[j - 1] + zeroCount[j - 2];
                oneCount[j] = oneCount[j - 1] + oneCount[j - 2];
            }
            System.out.println(zeroCount[N] + " " + oneCount[N]);
        }
    }
}
