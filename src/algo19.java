import java.util.*;

public class algo19 {

    static boolean[] visited;
    static ArrayList<String> array;

    public static void dfs(String[][] tickets,String words, int depth){
        if(depth == tickets.length){
            return ;
        }
        for(int i = 0 ; i < tickets.length ; i++){
            if(visited[i] == true)
                continue;
            
            if(tickets[i][0].equals(words)){
                visited[i] = true;
                if(array.size() < tickets.length + 1)
                     array.add(tickets[i][1]);
                dfs(tickets, tickets[i][1],depth + 1);
                visited[i] = false;
                if(array.size() < tickets.length +1)
                    array.remove(tickets[i][1]);
            }
        }
    }
    public static String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length + 1];
        int count = 0;
        array = new ArrayList<>();
        visited = new boolean[tickets.length];
        // 2 5 3 9 1
        //
        for(int i = 0 ; i < tickets.length; i++){
            for(int j = i + 1; j < tickets.length ; j++){
                if(tickets[i][1].charAt(0) > tickets[j][1].charAt(0)){
                    String[] temp = tickets[i];
                    tickets[i] = tickets[j];
                    tickets[j] = temp;
                }
            }
        }
        array.add("ICN");
        dfs(tickets, "ICN", 0);
        for(String str : array)
            answer[count++] = str;
        return answer;
    }

    public static void main(String[] args) {
        String[][] tickets = {{"ICN", "A"}, {"ICN", "B"}, {"B", "ICN"}};
        String[] temps = solution(tickets);
        for(String str : temps)
            System.out.println("str = " + str);

        // ICN BOO
        // DOO BOO
        // COO BOO
        // ICN COO
        
    }
}
