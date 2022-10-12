import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class algo153 {
    /*
    부등호 : 2529
     */
    static String[] real;
    static boolean[] visited = new boolean[10];
    static ArrayList<Integer> array = new ArrayList<>();
    static long Max = 0;
    static long Min = Long.MAX_VALUE;
    public static void dfs(int start, int count, int depth){
        if(count == depth){
            int trueCount = 0;
            for(int i = 0; i < real.length; i++){
                char temp = real[i].charAt(0);
                if(temp == '<' && array.get(i) < array.get(i + 1))
                    trueCount++;
                else if(temp == '>' && array.get(i) > array.get(i + 1))
                    trueCount++;
            }
            String temp = "";
            for(int i : array)
                temp += i;

            if(trueCount == real.length){
                Max = Math.max(Long.parseLong(temp), Max);
                Min = Math.min(Long.parseLong(temp), Min);
            }
            return ;
        }
        for(int i = 0 ; i <= 9; i++){
            if(visited[i] == true)
                continue;
            visited[i] = true;
            array.add(i);
            dfs(i, count + 1, depth);
            array.remove(array.indexOf(i));
            visited[i] = false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        real = new String[K];

        StringTokenizer st = new StringTokenizer(br.readLine(), "  ");
        for(int i = 0 ; i < K; i++)
            real[i] = st.nextToken();

        //System.out.println((int)Math.pow(10,2));
        dfs(0, 0, K + 1);
        System.out.println(Max);
        if(Min / (long)Math.pow(10, K) == 0) {
            System.out.print("0");
            System.out.println(Min);
        }else
            System.out.println(Min);
    }
}
