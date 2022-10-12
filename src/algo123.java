import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class algo123 {

    /*
    백준 : 2661
    좋은수열
     */

    public static boolean isPossible(String temp){

        int length = temp.length() / 2;

        for(int i = 1; i <= length; i++){
            // 배열중 같은 값이 존재하면 false;
            if(temp.substring(temp.length() - i).equals(
                    temp.substring(temp.length() - 2 * i, temp.length() - i)))
            return false;
        }

        // 배열에 같은 수열이 존재하지않는다 true;
        return true;

    }
    public static void find(int n, String num){

        if(num.length() == n){
            System.out.println(num);
            System.exit(0);
        }

        for(int i = 1; i <= 3; i++){
            if(num.charAt(num.length() -1) - '0' == i)
                continue;

            String temp = num + i;
            if(temp.length() <= 3){
                find(n, temp);
            }else{
                if(isPossible(temp)){
                    find(n, temp);
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        find(N, "1");

    }
}

