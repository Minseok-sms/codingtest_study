import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class algo148 {
    /*
    프로그래머스 : 여행경로
     */

    static boolean[] visited;
    static ArrayList<String> array;
    static String result = "J";
    public static void dfs(String start,int depth,int count,String temp, String[][] tickets ){

            if(count == depth){
                // 더작은 경로를 result에 넣자.
                // ISN S
                // ISN A
                result = result.compareTo(temp) > 0 ? temp : result;
                return;
            }
            for(int i = 0; i < tickets.length; i++){
                if(visited[i] == true)
                    continue;
                // ISO ENF ISF
                if(tickets[i][0].equals(start)){
                    visited[i] = true;
                    dfs(tickets[i][1], depth,count + 1, temp += tickets[i][1] + " ", tickets);
                    visited[i] = false;
                    temp = temp.substring(0, temp.length() - 4);
                }
            }
    }
    public static String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length + 1];
//        Arrays.sort(tickets, new Comparator<String[]>() {
//            @Override
//            public int compare(String[] o1, String[] o2) {
//                if(o1[0].equals(o2[0]))
//                    return (o1[1].charAt(0) - '0') - (o2[1].charAt(0) - '0');
//                else
//                    return (o1[0].charAt(0) - '0') - (o2[0].charAt(0) - '0');
//            }
//        });

        visited = new boolean[tickets.length];
        dfs("ICN", tickets.length, 0, "ICN ", tickets);
        StringTokenizer st = new StringTokenizer(result, " ");
        int count = 0;
        while(st.hasMoreTokens()){
            answer[count++] = st.nextToken();
        }
        return answer;
    }
    public static void main(String[] args) {
        //String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
        //String[][] tickets = {{"ICN","A"}, {"ICN","B"},{"B","ICN"}};

         //.out.println(str2.compareTo(str1));
        solution(tickets);
    }

}
