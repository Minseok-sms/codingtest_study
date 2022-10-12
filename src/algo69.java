import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


public class algo69 {

    /*
         프로그래머스 디스크컨트롤러 * 다시
     */

    static boolean[] visited;
    static int min = Integer.MAX_VALUE;
//    public static void dfs(int start,int real,int total, int[][] jobs, int depth){
//
//        if(depth == jobs.length){
//            min = Math.min(min, total / 3);
//            return ;
//        }
//        for(int i = 0 ; i < jobs.length; i++){
//            if(visited[i] == true)
//                continue;
//
//            visited[i] = true;
//            int end = jobs[i][1] + real; // 12
//            int time = end - jobs[i][0];
//            dfs(i,real + jobs[i][1] ,total + time, jobs, depth + 1);
//            visited[i] = false;
//
//        }
//    }
    public static int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>(((o1,o2) -> o1[1] - o2[1]));
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int idx = 0;
        int count = 0;
        int now = 0;
        while(count < jobs.length){
            while(idx < jobs.length && jobs[idx][0] <= now){
                queue.add(jobs[idx++]);
            }
            if(queue.isEmpty())
                now = jobs[idx][0];
            else{
                int[] temp = queue.poll();
                answer += temp[1] + now - temp[0];
                now += temp[1];
                count++;
            }

        }
        return (answer / jobs.length);
    }
    public static void main(String[] args) {
        int[][] jobs = {{0,3}, {1,9}, {2,6}};
        solution(jobs);

    }
}
