import java.util.HashMap;

public class algo57 {

    static boolean[] visited;
    static HashMap<String, Integer> map = new HashMap<>();
    public static void dfs(int depth, String str, String[] user_id, String[] banned_id){

        if(depth == banned_id.length){
            String temp = str;
            String answer = "";
            for(int k = 0 ; k < user_id.length; k++) {
                if(temp.contains("" + k))
                    answer += k;
            }

            if(!map.containsKey(answer)){
                map.put(answer, 0);
            }
            return ;
        }

        for(int i = 0 ; i < user_id.length; i++){
            if(visited[i] == true)
                continue;

            String temp = user_id[i];
            for(int j = 0; j < banned_id[depth].length(); j++){
                if(banned_id[depth].length() != temp.length())
                    continue;
                if(banned_id[depth].charAt(j) == '*') {
                    if(j == banned_id[depth].length() - 1)
                        temp = temp.substring(0, j) + '*';
                    else
                        temp = temp.substring(0, j) + '*' + temp.substring(j + 1);
                }
            }

            if(temp.equals(banned_id[depth])){
                visited[i] = true;
                dfs(depth + 1, str + i, user_id, banned_id);
                visited[i] = false;
            }
        }
    }
    public static int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        visited = new boolean[user_id.length];



        dfs(0, "", user_id,banned_id);
            System.out.println(map.size());
            answer = map.size();


        return answer;
    }
    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"*rodo", "*rodo", "******"};
        solution(user_id, banned_id);
    }
}

// 1 1 0 0 0
// 2 1 1 0 0
// 2 1 1 1 1
// 2, 1, 2,


// 2, 2, 1 ,1
// 2C2 +