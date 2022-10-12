import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class algo31 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        ArrayList<Integer> temp = new ArrayList<>();
        ArrayList<Integer> answer = new ArrayList<>();

        for(int i = 0 ; i < N; i++)
            array[i] = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < N; i++){

            if(temp.size() == 0) {
                temp.add(array[i]);
                answer.add(array[i]);
                continue;
            }
            else {
                int count = 0;
                for (int j = temp.size() - 1; j >= 0; j--) {
                    if (temp.get(j) <= array[i]) {
                        count++;
                        temp.add(j + 1, array[i]);
                        break;
                    }
                }
                if(count == 0)
                    temp.add(0, array[i]);

            }

            if(temp.size() % 2 == 0) {
                int div = temp.size() / 2;
                answer.add(Math.min(temp.get(div), temp.get(div - 1)));
            }else {
                answer.add(temp.get(temp.size() / 2));
            }
        }
        for(int i = 0 ; i < answer.size() ; i++){
            System.out.println(answer.get(i));
        }
    }
}
