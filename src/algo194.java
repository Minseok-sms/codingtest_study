import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class algo194 {
    /*
    고층 빌딩
     */
    static int N;
    static ArrayList<Node> array = new ArrayList<>();

    static class Node{
        int x;
        int y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static boolean calculate(int i, int j, int k){
        // 빌딩 A의 좌표
        Node A = array.get(i);
        // 빌딩 B의 좌표
        Node B = array.get(j);
        //빌딩 C의 좌표
        Node C = array.get(k);

        // 기울기 a
        double a = (double)(B.y - A.y) / (B.x - A.x);
        // b의 값
        double b =  A.y - a * A.x;

        // 선분보다, Y값이 낮아야 빌딩이보인다.
        if(a * C.x + b >= C.y)
            return true;

        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < N ; i++)
            array.add(new Node(i + 1, Integer.parseInt(st.nextToken())));
        int answer = 0;
        // 0 ~ max idx 까지for
        for(int i = 0 ; i < N; i++){

            // 기준점 기준 왼쪽 빌딩
            // 기준점과 j점과 기울기 구하고
            int canView = 0;
            int temp = 0;
            for(int j = i - 1; j >= 0; j--){
                canView = 0;
                for(int k = i - 1; k >= j; k--) {
                    if (calculate(i, j, k))
                        canView++;
                    else break;

                }
                temp = Math.max(canView, temp);
            }

            // 기준점 기준 오른쪽 빌딩
            int canView2 = 0;
            int temp2 = 0;
            for(int j = i + 1; j < N; j++){
                canView2 = 0;
                for(int k = i + 1; k <= j; k++){
                    if(calculate(i, j, k)) {

                        canView2++;
                    }
                    else break;
                }
                temp2 = Math.max(canView2, temp2);
            }
            answer = Math.max(answer, temp + temp2);
        }
        System.out.println(answer);


    }
}
