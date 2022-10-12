import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
    2866 : 문자열 잘라내기
 */
public class algo37 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        ArrayList<String> array = new ArrayList<>();
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        for (int i = 0; i < row; i++)
            array.add(br.readLine());

        int answer = 0;
        String[] temp = new String[col];


        // temp[0] = mmm
        // temp[1] = raa
        // temp[2] = vrt

        for (int i = 0; i < col; i++) {
            String str = "";
            for (int j = 1; j < row; j++) {
                str += array.get(j).charAt(i);
            }
            temp[i] = str;
        }
        stop:
        for (int i = 0; i < row; i++) {
            HashMap<String,Integer> map = new HashMap<>();
            for (int j = 0; j < col; j++) {
                String str = temp[j].substring(i);
                if (map.containsKey(str)) {
                    break stop;
                }else
                    map.put(str, 0);
            }
            answer++;
        }
        System.out.println(answer);
    }
}




/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class algo37 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        ArrayList<String> array = new ArrayList<>();
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int answer = 0;
        for(int i = 0 ; i < row ; i++)
            array.add(br.readLine());

        HashMap<String, Integer> map = new HashMap<>();

        stop:
        for(int k = 1; k < row ; k++) {
            String[] temp = new String[col];
            for (int i = k; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    temp[j] += array.get(i).charAt(j);
                }
            }

            for(int j = 0 ; j < col; j++) {
                if(map.containsKey(temp[j])){
                    break stop;
                }else
                    map.put(temp[j], map.getOrDefault(temp[j], 0) + 1);
            }
            answer++;
//            int keyCount = 0;
//            for(String key : map.keySet()){
//                if(map.get(key) >= 2) {
//                    keyCount++;
//                    break;
//                }
//            }
//            if(keyCount > 0)
//                break;
//            else
//                answer++;

        }
        System.out.println(answer);


    }
}

 */