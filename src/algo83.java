import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.exit;

public class algo83 {

    /*
    13023번 백준
     */
    static ArrayList<Integer>[] array;
    static int N;
    static int M;
    static boolean[] visited;
    static boolean check = false;
    public static void dfs(int start, int depth){

        visited[start] = true;
        if(depth == 4){
            check = true;
            return;
        }
        for(int i = 0; i < array[start].size(); i++){
            if(visited[array[start].get(i)] == true)
                continue;

            visited[array[start].get(i)] = true;
            dfs(array[start].get(i), depth + 1);
            visited[array[start].get(i)] = false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        array = new ArrayList[N];

        for(int i = 0 ; i < N ; i++)
            array[i] = new ArrayList<>();


        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int last = Integer.parseInt(st.nextToken());
            array[start].add(last);
            array[last].add(start);
        }

        for(int i = 0; i < M ; i++){
            visited = new boolean[N];
            dfs(i, 0);
            if(check == true) {
                System.out.println(1);
                exit(0);
            }
        }
        System.out.println(0);
    }
}

// A B
// B C
// C D
// D A
// B E

