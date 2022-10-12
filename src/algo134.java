import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class algo134 {
    /*
    백준 : 1072
    게임
     */
    static long total, win, Z;
    public static int binarySearch(long left, long right){


        while(left <= right){
            long mid = ( left + right ) / 2;
            long tempTotal = total + mid;
            long tempWin = win + mid;
            long tempAns = (long)Math.floor(100*tempWin/tempTotal);;

            if(tempAns == Z)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return (int)left;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        total = Integer.parseInt(st.nextToken());
        win = Integer.parseInt(st.nextToken());

        Z = (long)Math.floor(100*win/total);

        if(Z >= 99)
            System.out.println(-1);
        else {
            int answer = binarySearch(1, total);
            System.out.println(answer);
        }

    }
}
