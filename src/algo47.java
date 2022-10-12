public class algo47 {


    static boolean[] visited;
    static String[] friends = {"A","C","F","J","M","N","R","T"};
    static int answer;
    public static void dfs(int start, String plus, String[] data, int n){

        if(plus.length() == 8){
            boolean check = true;
            for(String str : data){
                int idx1 = plus.indexOf(str.charAt(0));
                int idx2 = plus.indexOf(str.charAt(2));
                char wh = str.charAt(3);
                int count = str.charAt(4) - '0';

                if(wh == '='){
                    if(Math.abs(idx1 - idx2) != count + 1)
                        check = false;
                }else if(wh == '>') {
                    if (Math.abs(idx1 - idx2) <= count + 1)
                        check = false;
                }else if(wh == '<'){
                    if(Math.abs(idx1 - idx2) >= count + 1)
                        check = false;
                }
            }
            if(check == true)
                answer++;
        }
        for(int i = 0 ; i < friends.length; i++){
            if(visited[i] == true)
                continue;

            visited[i] = true;
            String temp = plus + friends[i];
            dfs(i, temp, data, n);
            visited[i] = false;
        }

    }
    public int solution(int n, String[] data) {

        visited = new boolean[8];
        for(int i = 0 ; i < 8; i++)
            visited[i] = false;
        answer = 0;
        dfs(0,"",data, n);
        return answer;
    }
    public static void main(String[] args) {

    }
}
