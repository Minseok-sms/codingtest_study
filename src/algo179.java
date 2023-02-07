import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class algo179 {
    /*
    백준 : 10159
    저울
     */


    static int N, M;
    static final int INF = 100_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        ArrayList<Integer> array = new ArrayList<>();
        String str = "";

        int[] bu = new int[3];



        int[][] map = new int[N + 1][N + 1];

        // 비교할수 있는 순위 높 -> 낮
        boolean[][] check1 = new boolean[N + 1][N + 1];

        // 비교할수 있는 순위 낮 -> 높
        boolean[][] check2 = new boolean[N + 1][N + 1];
//        Arrays.sort(map, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return 0;
//            }
//        });

        Arrays.sort(map, new Comparator<int[]>(){

            public int compare(int[] o1, int[] o2){

                // +면 내림차순, -면 오름차순
                return 0;
            }
        });
        Arrays.sort(map, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2){

                return 0;
            }
        });
        Arrays.sort(map, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){

                return 0;
            }
        });



        for(int i = 0 ; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 비교할수 있는것 -> 1로바꾸기
            check1[a][b] = true;
            check2[b][a] = true;
        }

        // 플로이드 워셜 알고리즘
        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    // 자기 자신이면 패스
                    if(i == j)
                        continue;
                    // 길을 뚫을 수 잇을때
                   if(check1[i][k] == true && check1[k][j] == true)
                       check1[i][j] = true;

                   if(check2[i][k] == true && check2[k][j] == true)
                       check2[i][j] = true;
                }
            }
        }

        // or을 취해서 물건비교가능
        for(int i = 1; i <= N; i++){
           for(int j = 1 ; j <= N; j++){
               check1[i][j] |= check2[i][j];
           }
        }

        for(int i = 1 ; i <= N ;i++){
            int cnt = 0;
            for(int j = 1 ; j <= N; j++){
                if(i == j)
                    continue;
                if(check1[i][j] == false)
                    cnt++;
            }
            System.out.println(cnt);
        }

    }
}




// 1 -> 2 -> 3
// 3 -> 4
// 6 -> 5 - > 4


// 5 -> 4
// 6 -> 5


// 0  1 1   1  INF   INF
//INF 0  1  INF  INF   INF
//INFINF 0   1   INF   INF
//INFINF INF 0   INF    INF
//INFINFINF 1    0     INF
// INfINF INFINF  1     0