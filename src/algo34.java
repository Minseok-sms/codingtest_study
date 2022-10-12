import java.util.*;


/*
신세계 2번
 */
public class algo34 {
    static HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    static String[] name;
    static boolean[] visited;
    static ArrayList<String> sol = new ArrayList<>();
    static int global = 0;
    public static void dfs(int start){

        visited[start] = true;
        for(int i = 0; i < map.size(); i++){
            if(visited[i] == true)
                continue;
            int count = 0;
            for(int j = 1 ; j < 101 ; j++){
                if(map.get(name[i]).get(j) != -1)
                    count++;
            }

            if(count < 5) {
                visited[i] = true;
                continue;
            }

            if(Arrays.equals(map.get(name[start]).toArray(), map.get(name[i]).toArray())) {
                visited[i] = true;
                sol.add(name[i]);
                if(global == 0) {
                    sol.add(name[start]);
                    global++;
                }
                dfs(i);
            }


        }
    }
    public static String[] solution(String[] logs){
        String[] answer = {};
        for(int i = 0 ; i < logs.length; i++){
            StringTokenizer st = new StringTokenizer(logs[i], " ");
            String user = st.nextToken();
            String num = st.nextToken();
            String score = st.nextToken();
            map.put(user, new ArrayList<>());
            for(int j = 0 ; j < 101; j++){
                    map.get(user).add(-1);
            }

        }
        visited = new boolean[map.size()];
        name = new String[map.size()];

        for(int i = 0 ; i < logs.length; i++){
            StringTokenizer st = new StringTokenizer(logs[i], " ");
            String user = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            map.get(user).remove(num);
            map.get(user).add(num, score);

        }
        int count = 0;
        for(String key : map.keySet()) {
            name[count++] = key;
        }
        count = 0;
        for(int i = 0 ; i < map.size(); i++){
            count = 0;
            for(int j = 1 ; j < 101 ; j++){
                if(map.get(name[i]).get(j) != -1)
                    count++;
            }

            if(count >= 5) {
                global = 0;
                dfs(i);
            }
        }
        if(sol.size() == 0){
            answer = new String[1];
            answer[0] = "None";
        }
        else {
            answer = new String[sol.size()];
            count = 0;
            for (String str : sol) {
                answer[count++] = str;
            }
            Arrays.sort(answer);

        }
        for (String str : answer)
            System.out.println(str);
        return answer;
    }
    public static void main(String[] args) {
       String[] logs = {"0001 1 0", "0001 2 0", "0001 3 0", "0001 4 0", "0001 5 0", "0456 1 0", "0456 2 0", "0456 3 0", "0456 4 0", "0456 5 0"};
        solution(logs);
    }
}
