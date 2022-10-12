import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class algo157 {

    /*
    백준 : 14891
     */
    // 2 6
    static class Node{
        int target;
        int path;
        Node(int target, int path){
            this.target = target;
            this.path = path;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] status = new String[4];
        for(int i = 0 ; i < 4; i++)
            status[i] = br.readLine();


        int N = Integer.parseInt(br.readLine());
        Node[] node = new Node[N];
        for(int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            node[i] = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
        }

        for(int i = 0 ; i < N; i++){

            ArrayList<Node> ex = new ArrayList<>();
            int start = node[i].target;
            int path = node[i].path;

            // 우측 : 2, 좌측 : 6
            //기준점 우측확인
            for(int k = start + 1; k < 4; k++){
                if(status[start].charAt(2) != status[k].charAt(6)){
                    ex.add(new Node(k, path == -1 ? 1 : -1));
                    start = k;
                    path = (path == -1 ? 1 : - 1);
                }else
                    break;
            }
            start = node[i].target;
            path = node[i].path;

            // 기준점 좌측확인
            for(int k = start - 1; k >= 0; k--){
                if(status[start].charAt(6) != status[k].charAt(2)){
                    ex.add(new Node(k, path == -1 ? 1 : -1));
                    start = k;
                    path = (path == -1 ? 1 : - 1);
                }else
                    break;
            }

            start = node[i].target;
            path = node[i].path;
            // 자기 자신 회전
            if(path == 1){
                if(status[start].charAt(7) == '1'){
                    status[start] = "1" + status[start].substring(0, 7);
                }else{
                    status[start] = "0" + status[start].substring(0, 7);
                }
            }else{
                if(status[start].charAt(0) == '1')
                    status[start] = status[start].substring(1,8) + "1";
                else
                    status[start] = status[start].substring(1,8) + "0";
            }

            // 확인 완료했으니 ex배열에 들어간 톰니바퀴를 옮기기처리
            for(int k = 0; k < ex.size(); k++){
                int idx = ex.get(k).target;
                int clock = ex.get(k).path;

                // 시계방향일때
                if(clock == 1){
                    //만약 끝자락이 1이면
                    if(status[idx].charAt(7) == '1'){
                        status[idx] = "1" + status[idx].substring(0, 7);
                    }else{
                        status[idx] = "0" + status[idx].substring(0, 7);
                    }
                }else{
                    //반시계 방향 처음이 1이면
                    if(status[idx].charAt(0) == '1')
                        status[idx] = status[idx].substring(1,8) + "1";
                    else
                        status[idx] = status[idx].substring(1,8) + "0";
                }

            }
        }

        int answer = 0;
        int count = 1;
        for(int i = 0 ; i < 4; i++){
            if(status[i].charAt(0) == '1') {
                answer += count;
                count *= 2;
            }else
                count *= 2;
        }
        System.out.println(answer);
    }
}
