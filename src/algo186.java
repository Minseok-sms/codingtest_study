import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class algo186 {
    /*
    백준 : 1099 (X)

     */

    static String sen;
    static int N;
    static ArrayList<String> array = new ArrayList<>();
    static boolean[] visited;
    static int MIN = Integer.MAX_VALUE;
    public static void dfs(int start, String idxStr, String str){
        if(str.length() > sen.length())
            return ;
        if(str.length() == sen.length()){
            int temp = 0;
            for(int j = 0; j < idxStr.length(); j++){
                String word = array.get(idxStr.charAt(j) - '0');
                int wordLen = word.length();


                if( temp + wordLen > sen.length())
                    return ;

                String comp = sen.substring(temp, temp + wordLen);
                temp += wordLen;

                HashMap<Character, Integer> map1 = new HashMap<>();
                HashMap<Character, Integer> map2 = new HashMap<>();

                boolean check = true;
                for(int k = 0 ; k < wordLen; k++)
                    map1.put(word.charAt(k), map1.getOrDefault(word.charAt(k), 0) + 1);
                for(int k = 0; k < wordLen; k++)
                    map2.put(comp.charAt(k), map2.getOrDefault(comp.charAt(k), 0) + 1);

                for(char a : map1.keySet()){
                    if(map2.containsKey(a) == false){
                        check = false;
                        break;
                    }
                    if(map1.get(a) != map2.get(a)){
                        check = false;
                        break;
                    }
                }
                if(check == false)
                    return ;
            }

            int count = 0;
            for(int j = 0; j < str.length(); j++){
                if(sen.charAt(j) != str.charAt(j))
                    count++;
            }
            MIN = Math.min(MIN, count);
            return ;
        }

        for(int i = 0; i < N; i++){
            dfs(i, idxStr + i, str + array.get(i));
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sen = br.readLine();
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++)
            array.add(br.readLine());

        visited = new boolean[N];
        dfs(0, "", "");
        if(MIN == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(MIN);
    }
}
