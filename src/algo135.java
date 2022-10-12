import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class algo135 {
    /*
    백준 : 17951
    흩날리는 시험지 속에서 내 평점이 느껴진거야

    이분탐색
     */
    static int[] array;
    static int N, K;
    public static int binarySearch(int left, int right){

        while(left <= right){
            int mid = (left + right) / 2;
            int groupCount = 1;
            int sum = 0;
            for(int i = 0; i < N; i++){
                sum += array[i];
                if(sum >= mid){
                    groupCount++;
                    sum = 0;
                }
            }
            if(groupCount > K)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return left - 1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        array = new int[N];

        int right = 0;
        for(int i = 0 ; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
            right += array[i];
        }

        int answer = binarySearch(0, right);
        System.out.println(answer);
    }
}

