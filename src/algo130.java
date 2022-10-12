import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class algo130 {

    /*
    백준 : 17471
    게리멘더링
    ******
     */
    static int N;
    static int[] popInfo;
    static ArrayList<Integer>[] array;
    static boolean totalCheck = false;
    static int min = Integer.MAX_VALUE;

    public static boolean isConnect(ArrayList<Integer> seq){

        // 지역구돌기위해 배열에담긴 지역을 돌수 잇는지 확인
        boolean[] visited = new boolean[N + 1];
        int num = seq.get(0);
        int size = seq.size();

        visited[num] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(num);

        int count = 1;
        while(!queue.isEmpty()){
            int temp = queue.poll();

            for(int i = 0; i < array[temp].size(); i++){
                if(visited[array[temp].get(i)] == true)
                    continue;

                if(seq.contains(array[temp].get(i)) == false)
                    continue;

                visited[array[temp].get(i)] = true;
                queue.offer(array[temp].get(i));
                count++;
            }
        }

        if(count == seq.size())
            return true;

        return false;
    }
    public static void checking(ArrayList<Integer> seq){


        // 1지역구 확인
        if(isConnect(seq) == false)
            return;


        //2지역구 확인
        // seq배열에서 제외한 나머지 arraylist를 제공
        ArrayList<Integer> seq2 = new ArrayList<>();
        for(int i = 1; i <= N; i++){
            if(!seq.contains(i))
                seq2.add(i);
        }

        if(isConnect(seq2) == false)
            return;

        totalCheck = true;
        // 1지역구 2지역구 둘다 나눠질수있으면 인구확인
        int local1 = 0;
        int local2 = 0;
        for(int l : seq)
            local1 += popInfo[l];
        for(int l : seq2)
            local2 += popInfo[l];

        min = Math.min(Math.abs(local1 - local2), min);

    }
    public static void dfs(int start,int count, int depth, ArrayList<Integer> seq){


        if(count == depth){
            // 조합해서 뽑앗으니까 지역1, 지역2나눠서 연결되어있는지 확인
            checking(seq);
            return ;
        }

        for(int i = start; i <= N; i++){
            seq.add(i);
            dfs(i, count + 1, depth, seq);
            seq.remove((Integer) i);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        popInfo = new int[N + 1];
        array = new ArrayList[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1 ; i <= N; i++) {
            popInfo[i] = Integer.parseInt(st.nextToken());
            array[i] = new ArrayList<>();
        }


        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int count = Integer.parseInt(st.nextToken());
            for(int j = 0; j < count; j++){
                int start = i + 1;
                int end = Integer.parseInt(st.nextToken());
                array[start].add(end);
                array[end].add(start);
            }
        }

        // 지역의수 절반만 돌면됨.
        for(int i = 1; i <= N /2 ; i++){
            ArrayList<Integer> tempArr = new ArrayList<>();
            dfs(1, 0, i, tempArr);
        }

        if(totalCheck == false)
            System.out.println(-1);
        else
            System.out.println(min);

    }
}
