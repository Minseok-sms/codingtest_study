import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class algo35 {
    /*
    1654 : 랜선자르기
    이분탐색
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        ArrayList<Integer> array = new ArrayList<>();
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < K ; i++)
            array.add(Integer.parseInt(br.readLine()));
        Collections.sort(array);

        long left = 1;
        long right = array.get(K - 1);
        long middle = 0;

        while(left <= right){
            long count = 0;

            middle = (left + right) / 2;
            for(int i = 0 ; i < K ; i++){
                count += array.get(i) / middle;
            }

            if(count < N)
                right = middle - 1;
            else if(count >= N)
                left = middle + 1;
        }
    }
}
