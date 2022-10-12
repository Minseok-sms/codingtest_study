import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class algo103 {
    /*
    백준 : 3079
    입국심사
     */

    public static long binarySearch(int[] array, int M){
        long left = array[0];
        long right = (long)M * array[array.length - 1];

        while(left <= right){
            long mid = (left + right) / 2;

            long sum = 0;
            for(int i = 0 ; i < array.length; i++){
                sum += mid / array[i];
            }

            if(sum >= M){
                right = mid - 1;
            }else
                left = mid + 1;
        }

        return left;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] array = new int[N];

        for(int i = 0 ; i < N; i++)
            array[i] = Integer.parseInt(br.readLine());

        Arrays.sort(array);
        long answer = binarySearch(array, M);

        System.out.println(answer);
    }
}
