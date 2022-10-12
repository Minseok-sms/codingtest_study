//public class algo18 {
//
//
//    static boolean[] visited;
//    static int answer = 0;
//
//    public static void dfs(String begin, String target, String[] words, int cnt){
//        if(begin.equals(target)) {
//            answer = cnt;
//            return;
//        }
//
//        for(int i = 0; i < words.length; i++){
//            if(visited[i] == true)
//                continue;
//
//            int k = 0;
//            for(int j = 0 ; j < begin.length(); j++){
//                if(begin.charAt(j) == words[i].charAt(j)){
//                    k++;
//                }
//            }
//            if(k == begin.length() - 1){
//                visited[i] = true;
//                dfs(words[i], target, words, cnt + 1);
//                visited[i] = false;
//            }
//        }
//
//
//    }
//    public int solution(String begin, String target, String[] words) {
//        int answer = 0;
//
//        return answer;
//    }
//    public static void main(String[] args) {
//
//    }
//}


public class algo18 {


    static boolean[] visited;
    static int answer = 0;
    public static void dfs(String[] words, String begin, String target, int cnt){

        if(begin.equals(target)){
            answer = cnt;
            return ;
        }

        for(int i = 0; i < words.length; i++){
            if(visited[i] == true)
                continue;

            int count = 0;
            for(int j = 0; j < begin.length(); j++){
                if(words[i].charAt(j) == begin.charAt(j))
                    count++;
            }
            if(count == begin.length() - 1){
                visited[i] = true;
                dfs(words, words[i], target, cnt + 1);
                visited[i] = false;
            }
        }
        return ;
    }
    public static int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        dfs(words,begin,target,0);
        return answer;
    }
    public static void main(String[] args) {
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        int a = solution("hit", "cog", words);
        System.out.println("a = " + answer);
    }
}
