import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class algo188 {


    static ArrayList<Node> array = new ArrayList<>();
    static int N;
    static class Node{
        int dur;
        int weight;

        Node(int dur, int weight){
            this.dur = dur;
            this.weight = weight;
        }
    }
    public static void dfs(int start, int cnt){

        // 자기꺼 이미 뿌셔져잇으면 cnt + 1하고 다음꺼
        if(array.get(start).dur < 0 || cnt == N - 1){
            dfs(start + 1, cnt );
            return ;
        }

        for(int i = 0; i < N; i++){
            // 자기 계란이면 패스
            if(start == i)
                continue;
            // 뿌실 상대방꺼 잇는지 ?
            if(array.get(i).dur <= 0)
                continue;
            array.get(start).dur -= array.get(i).weight;
            array.get(i).dur -= array.get(start).weight;

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            array.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }


    }
}
