import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class algo101 {
    /*
    백준 : 2110
    공유기 설치
     */

    // 1 2 4 8 9

    static int globalCount = 0;
    public static void binarySearch(int[] array, int C){
        int left = 1;
        int right = array[array.length - 1];

        while(left <= right){
            int mid = (left + right) / 2;
            int count = C - 1;

            int first = array[0];
            for(int i = 1 ; i < array.length; i++){
                if(array[i] - first >= mid){
                    first = array[i];
                    count--;
                }
            }
            if(count <= 0){
                if(count == 0){
                    globalCount = Math.max(mid, globalCount);
                }
                globalCount = mid;
                left = mid + 1;
            }else
                right = mid - 1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] array = new int[N];
        for(int i = 0 ; i < N ; i++)
            array[i] = Integer.parseInt(br.readLine());

        Arrays.sort(array);

        binarySearch(array, C);
        System.out.println(globalCount);
    }
}
