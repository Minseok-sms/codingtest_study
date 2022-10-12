import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class algo93 {

    /*
    백준 : 14003 가장 긴 증가하는 부분수열
     */

    static int[] temp;
    static int[] array;
    static int[] indexOrder;
    public static int binarySearch(int left, int right, int target){

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
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        array = new int[N];
        temp = new int[N + 1];
        indexOrder = new int[N];


        for(int i = 0 ; i < N; i++)
            array[i] = Integer.parseInt(st.nextToken());

        temp[0] = -1_000_000_001;

        int cnt = 0;

        // 10  20 10 30 20 50
        //-INF 0  0  0  0  0

        for(int i = 0 ; i < N ; i++){
            if(array[i] > temp[cnt]){
                cnt++;
                temp[cnt] = array[i];
                indexOrder[i] = cnt - 1;
                continue;
            }
            int idx = binarySearch(0, cnt, array[i]);
            temp[idx] = array[i];
            indexOrder[i] = idx - 1;
        }
        System.out.println(cnt);

        Stack<Integer> stack = new Stack<>();
        for(int i = N - 1 ; i >= 0; i--){
            if(cnt - 1 == indexOrder[i]){
                stack.push(array[i]);
                cnt--;
            }
        }
        while(!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }

    }
}
