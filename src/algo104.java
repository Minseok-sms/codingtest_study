import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class algo104 {
    /*
    백준 : 2015
    수들의 합 4
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");


        HashMap<Integer, Integer> map = new HashMap<>();
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 데이터 배열
        int[] array = new int[N + 1];
        // 누적합 배열
        int[] temp = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1 ; i <= N ; i++){
            array[i] = Integer.parseInt(st.nextToken());
            temp[i] = temp[i - 1] + array[i];
        }

        map.put(0, 1);
        long sum = 0;
        for(int i = 1; i <= N; i++){
            sum += map.getOrDefault(temp[i] - K, 0);

            map.put(temp[i], map.getOrDefault(temp[i], 0) + 1);
        }

        System.out.println(sum);

    }
}
