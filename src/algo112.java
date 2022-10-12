import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class algo112 {
    /*
    백준 : 7662
    이중 우선순위큐

    TreeMap 공부
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < T; i++){
            int N = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> que = new TreeMap<>();

            for(int j = 0 ; j < N ; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                String order = st.nextToken();
                int num = Integer.parseInt(st.nextToken());
                char a = order.charAt(0);

                if(a == 'I'){
                    que.put(num, que.getOrDefault(num, 0) + 1);
                }else{
                    if(que.size() == 0)
                        continue;

                    // 최댓값 삭제
                    if(num == 1){
                        int temp = que.lastKey();
                        if(que.get(temp) >= 2)
                            que.put(temp, que.get(temp) - 1);
                        else
                            que.remove(temp);
                    }else{
                        // 최솟값 삭제
                        int temp = que.firstKey();
                        if(que.get(temp) >= 2)
                            que.put(temp, que.get(temp) - 1);
                        else
                            que.remove(temp);
                    }
                }
            }
            if(que.size() == 0)
                System.out.println("EMPTY");
            else
                System.out.println(que.lastKey() + " " + que.firstKey());
        }
    }
}

// 1 1 2 3 6 7 30
