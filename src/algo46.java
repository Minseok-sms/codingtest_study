import java.util.HashMap;

public class algo46 {

    /*
      프로그래머스 : 단체사진 찍기
     */
    static boolean[] visited;
    static int globalCount = 0;
    static HashMap<Character, Integer> map = new HashMap<>();
    static int[] dfsTemp;
    public static void dfs(int start, String[] data, int n){

        if(start == 8){
            boolean tempBool = true;
            for(String str : data){
                int a = dfsTemp[map.get(str.charAt(0))];
                int b = dfsTemp[map.get(str.charAt(2))];
                char wh = str.charAt(3);
                int num = str.charAt(4) - '0' + 1;

                if(wh == '='){
                    if(Math.abs(a - b) != num)
                        tempBool = false;
                }else if(wh == '>'){
                    if(Math.abs(a - b) <= num)
                        tempBool = false;
                }else if(wh == '<'){
                    if(Math.abs(a - b) >= num)
                        tempBool = false;
                }
            }
            if(tempBool == true){
                globalCount++;
            }
        }
        for(int i = 0 ; i < 8; i++){
            if(visited[i] == true)
                continue;
            //  0  1 2 3 5 6 4 7
            visited[i] = true;
            dfsTemp[start] = i;
            dfs(start + 1 ,data, n);
            visited[i] = false;
        }
    }
    public static int solution(int n, String[] data) {
        int answer = 0;
        map.put('A', 0);
        map.put('C', 1);
        map.put('F', 2);
        map.put('J', 3);
        map.put('M', 4);
        map.put('N', 5);
        map.put('R', 6);
        map.put('T', 7);
        visited = new boolean[map.size()];
        dfsTemp = new int[map.size()];
        dfs(0,data,n);
        answer = globalCount;
        return answer;
    }

    public static void main(String[] args) {
        char a = '0';
        System.out.println(a - '0' + 1);

    }
    //{A, C, F, J, M, N, R, T}
    // 0, 1, 2, 3, 4, 5, 6, 7
    //{N F, R M J C T, A}
    //
}

// ch = 0 , 1, 2, 3,4, 5, 7 7