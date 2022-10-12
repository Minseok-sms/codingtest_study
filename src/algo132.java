import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class algo132 {


    /*
    백준 : 3020
    개똥벌레
     */
    static int[] array;
    static int N,H;
    static HashMap<Integer, Integer> map = new HashMap<>();
    static int[] top;
    static int[] bot;
    static int min;

    public static int process(){
        int[] bot_sum = new int[H + 1]; // 높이가 h이하인 석순의 갯수
        int[] top_sum = new int[H + 1]; // 높이가 h이하인 종유석의 갯수

        for (int i = 1; i < H + 1; i++) {
            top_sum[i] = top_sum[i - 1] + top[i];
            bot_sum[i] = bot_sum[i - 1] + bot[i];
        }
        int cnt = 0;
        for(int i = 1; i < H + 1; i++){
            int crush = 0;
            crush += bot_sum[H] - bot_sum[i - 1];
            crush += top_sum[H] - top_sum[H - i];

            if(crush < min){
                min = crush;
                cnt = 1;
            }else if(crush == min){
                cnt++;
            }
        }
        return cnt;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        bot = new int[H + 1]; //석순
        top = new int[H + 1]; //종유석

        min = N;
       for(int i = 0; i < N / 2; i++) {
            bot[Integer.parseInt(br.readLine())]++;
            top[Integer.parseInt(br.readLine())]++;
        }
        int answer = process();
        System.out.println(min + " " + answer);
    }
}
