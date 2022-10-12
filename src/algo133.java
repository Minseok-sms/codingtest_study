import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class algo133 {

    /*
    백준 : 2343
    기타레슨
     */
    static int[] lec;
    static int N,M;

    //  1 2 3 4 5 6 7 8 9
    //  1 3 6 10
    public static int binarySearch(int left, int right){

        while(left <= right){
            int mid = (left + right) / 2;

            int cnt = 1;
            int sum = 0;
            for(int i = 0; i < N ; i++){
                sum += lec[i];
                if(sum > mid){
                    sum = lec[i];
                    cnt++;
                }
            }

            if(cnt > M)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return left;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lec = new int[N];
        st = new StringTokenizer(br.readLine(), " ");

        int leftMax = 0;
        for (int i = 0; i < N; i++) {
            lec[i] = Integer.parseInt(st.nextToken());
            leftMax = Math.max(lec[i], leftMax);
        }

        System.out.println(binarySearch(leftMax, 1000000000));
    }
}

