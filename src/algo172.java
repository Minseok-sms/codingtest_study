import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class algo172 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] array = new int[26];
        String temp;
        while((temp = br.readLine()) != null){
            for(int i = 0 ; i < temp.length(); i++){
                if(temp.charAt(i) == ' ')
                    continue;
                int value = temp.charAt(i) - 'a';
                array[value]++;
            }
        }
        // 최댓값 찾자
        int max = 0;
        for(int i = 0 ; i < array.length; i++){
            if(array[i] >= max)
                max = array[i];
        }

        // 최댓값에 해당되는 문자 출력
        for(int i = 0 ; i < array.length; i++){
            if(array[i] == max)
                System.out.print((char)(i + 'a'));
        }

    }
}

