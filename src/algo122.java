import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class algo122 {

    /*
    백준 : 11053
    가장 긴증가하는 부분수열
     */
    static int[] array;
    static int[] dp;
    public static int binarySearch(int target, int left, int right){
        while(left < right){
            int mid = (left + right) / 2;
            if(dp[mid] < target){
                left = mid + 1;
            }else
                right = mid;
        }
        return right;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        array = new int[n];
        dp = new int[n + 1];


        // 0 10 20 0 0 0
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < n; i++)
            array[i] = Integer.parseInt(st.nextToken());


        int cnt = 0;
        for(int i = 0 ; i < n; i++){
            if(dp[cnt] < array[i]) {
                cnt++;
                dp[cnt] = array[i];
                continue;
            }
           int idx = binarySearch(array[i], 0, cnt);
            dp[idx] = array[i];
        }
        System.out.println(cnt);
    }
}
