import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class algo150 {
    /*
    회의실 배정 : 1931번
     */

    static int[][] array;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N][2];

        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            array[i][0] = Integer.parseInt(st.nextToken());
            array[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1])
                    return o1[0] - o2[0];

                return o1[1] - o2[1];
             }
        });
        int count = 0;
        int prev_end_time = 0;

        for(int i = 0; i < N; i++){
            if(prev_end_time <= array[i][0]){
                prev_end_time = array[i][1];
                count++;
            }
        }
        System.out.println(count);
    }
}
