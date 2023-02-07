import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class algo177 {
    /*
    백준 : 14658
    하늘에서 별똥별이 빚발친다.
     */

    static int N, M, L, K;
    static class Node{
        int row;
        int col;
        Node(int col, int row){
            this.row = row;
            this.col = col;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());


        ArrayList<Node> array = new ArrayList<>();
        for(int i = 0 ; i < K; i++){
            st = new StringTokenizer(br.readLine(), " ");
            array.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int answer = 0;
        // 하나씩 때서 오른쪽위로 트렘플릿 설치해보자
        for(int i = 0; i < array.size(); i++) {
            for (int k = 0; k < array.size(); k++) {
                int row = array.get(i).row; // 4
                int col = array.get(k).col; // 2

                int cnt = 0;
                for (int j = 0; j < array.size(); j++) {
                    int row1 = array.get(j).row; // 1
                    int col1 = array.get(j).col; // 3

                    // 트렘플린 안에들어가는 범위를찾자.
                    if ((row <= row1 && row1 <= row + L) && (col <= col1 && col1 <= col + L)) {
                        cnt++;
                    }
                }
                answer = Math.max(answer, cnt);
            }
        }
        System.out.println(K - answer);

    }
}
// 3 2 7
// 1 4 3

// 0 0 1 0 0 0 0 0 0 0 0 0
// 0 0 0 0 0 0 0 0 0 0 0 0
// 0 0 0 0 0 0 1 0 0 0 0 0
// 0 1 0 0 0 0 0 0 0 0 0 0
// 0 0 0 0 0 0 0 0 0 0 0 0
// 0 0 0 0 1 0 0 1 0 0 0 0
// 0 0 0 1 0 0 0 0 0 0 0 0
// 0 0 0 0 0 0 0 0 0 0 0 0
// 0 0 0 0 0 0 0 0 0 0 0 0
// 0 0 0 0 0 0 0 0 0 0 0 1

