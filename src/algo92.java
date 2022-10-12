import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class algo92 {

    /*
    백준 : 12738

    LIS 알고리즘 -> 가장긴증가하는 부분수열 구하기
     */

    static int[] temp;
    static int[] array;

    public static int binarySearch(int cnt, int target){
        int left = 0;
        int right = cnt;

        while(left < right){
            int mid = (left + right) / 2;
            if(temp[mid] < target){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return right;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        array = new int[N];
        temp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < N; i++)
            array[i] = Integer.parseInt(st.nextToken());

        temp[0] = -1_000_000_001;

        int cnt = 0;
        for(int i = 0; i < N; i++){
            if(array[i] > temp[cnt]){
                cnt++;
                temp[cnt] = array[i];
                continue;
            }

            int idx = binarySearch(cnt, array[i]);
            temp[idx] = array[i];
        }

        System.out.println(cnt);
    }
}
