import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class algo102 {
    /*
    백준 : 2352
    반도체 설계
    증가하는 부분수열 문제 + 이분탐색
     */
    static int[] temp;
    static int[] array;
    public static int binarySearch(int left, int right, int target){
        while(left < right){
            int mid = (left + right) / 2;
            if(temp[mid] < target){
                left = mid + 1;
            }else
               right = mid;
        }
        return right;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        array = new int[N];
        temp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0 ; i < N; i++)
            array[i] = Integer.parseInt(st.nextToken());

        int cnt = 0;
        // 4 2 6 3 1 5
        // 0 1 3 5 0 0 0
        // cnt = 2;
        for(int i = 0; i < N; i++){
            if(array[i] > temp[cnt]){
                cnt++;
                temp[cnt] = array[i];
                continue;
            }
            int idx = binarySearch(0, cnt, array[i]);
            temp[idx] = array[i];
        }
        System.out.println(cnt);
    }
}
