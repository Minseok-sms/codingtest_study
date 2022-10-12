import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class algo32 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        ArrayList<String> array = new ArrayList<>();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        for(int i = 0 ; i < M ; i++){
            String str = br.readLine();
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        int count = 0;
        for(String key : map.keySet()){
            if(map.get(key) == 2) {
                count++;
                array.add(key);
            }
        }
        System.out.println(count);
        Collections.sort(array);
        for(String str : array)
            System.out.println(str);

    }
}
