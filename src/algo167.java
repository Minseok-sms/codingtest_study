import java.io.*;
import java.util.*;

public class algo167 {

    static int answer = 0;
    static int N;
    static int globalCount = 0;
    static ArrayList<Integer>[] array;
    static boolean[] chooseVisited;
    static boolean[] visited;
    static boolean check = false;
    public static void bfs(int start){
        visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);
        globalCount = 1;


        while(!queue.isEmpty()){
            int temp = queue.poll();

            for(int i = 0 ; i < array[temp].size(); i++){
                // 자 만약 넣는게 등대로 선택된애다.
                if(visited[array[temp].get(i)] == true)
                    continue;

                if(chooseVisited[temp] == true){
                    // 등대로 선택된애는 등대 + 기본애 둘다 넣을수 있음
                    queue.add(array[temp].get(i));
                    globalCount++;
                    visited[array[temp].get(i)] = true;
                }else{
                    // 등대로 선택되지 않은애다 // 무조건 등대인 애를 넣어야한다.
                    if(chooseVisited[array[temp].get(i)] == true){
                        queue.add(array[temp].get(i));
                        globalCount++;
                        visited[array[temp].get(i)] = true;
                    }

                }
            }
        }


    }
    public static void choose(int count, int depth){
        if(count == depth){
            //등대 램덤으로 뽑았을때
            bfs(1);
            if(globalCount == N)
                check = true;
            return ;
        }
        for(int i = 1 ; i <= N; i++){
            if(chooseVisited[i] == true)
                continue;
            chooseVisited[i] = true;
            choose(count + 1, depth);
            chooseVisited[i] = false;
        }
    }
    public static int solution(int n, int[][] lighthouse) {

        array = new ArrayList[n + 1];
        N = n;

        for(int i = 1 ; i <= n; i++)
            array[i] = new ArrayList<>();

        for(int i = 0 ; i < lighthouse.length; i++){
            int start = lighthouse[i][0];
            int end = lighthouse[i][1];
            array[start].add(end);
            array[end].add(start);
        }
        for(int i = 1; i <= n; i++){
            chooseVisited = new boolean[n + 1];
            choose(0, i);
            if(check == true){
                answer = i;
                break;
            }
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        int temp = 8;
        int[][] lighthouse = new int[][]{ {1,2}, {1,3}, {1,4}, {1,5}, {5,6}, {5, 7}, {5, 8}};
        int answer = solution(temp, lighthouse);
        System.out.println(answer);

    }
}
