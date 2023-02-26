import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class algo187 {

    /*
    백준 : 16958
    텔레포트
     */
    static int N, T;
    static ArrayList<Node> coor = new ArrayList<>();
    static ArrayList<Way> way = new ArrayList<>();
    static int[][] map;
    static int MAX = 100_000_000;

    static class Node{
        int check;
        int row;
        int col;
        Node(int check, int row, int col){
            this.check = check;
            this.row = row;
            this.col = col;
        }
    }
    static class Way{
        int start;
        int end;
        Way(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        for(int i = 1 ; i <= N; i++){
            for(int j = 1; j <= N ;j++){
                if(i == j)
                    continue;
                map[i][j] = MAX;
            }
        }
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            coor.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int M = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            way.add(new Way(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }


        // 각도시간의 거리를 초기화하자.
        // 1->2 , 2->3, 3->4, 4->5, 5->6, 1->3
        for(int i = 1; i <= N; i++){
            for(int j = i + 1; j <= N; j++){
                Node temp1 = coor.get(i - 1);
                Node temp2 = coor.get(j - 1);
                // 만약 temp1,2가 둘다 특별한 도시다 ?
                if(temp1.check == 1 && temp2.check == 1) {
                    // 만약 텔레포트 거리가 더빠른지?, 아니면 식거리가 더빠른지 알아보자.
                    int range = Math.abs(temp1.row - temp2.row) + Math.abs(temp1.col - temp2.col);

                    // 그냥 식거리가 더빠르다.
                    if(range < T){
                        map[i][j] = range;
                        map[j][i] = range;
                    }else{
                        // 텔포가 더빠르다
                        map[i][j] = T;
                        map[j][i] = T;
                    }
                }else {
                    // 두도시다 특별한 도시가 아니다. 거리식을 넣자
                    int range = Math.abs(temp1.row - temp2.row) + Math.abs(temp1.col - temp2.col);
                    map[i][j] = range;
                    map[j][i] = range;
                }
            }
        }
        // 플로이드- 와샬
        for(int k = 1; k <= N ; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1 ; j <= N; j++){
                    if(i == j)
                        continue;
                    // 거쳐가는 값 최소화하자.
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(Way i : way)
            sb.append(map[i.start][i.end] + "\n");
        System.out.println(sb);
    }
}