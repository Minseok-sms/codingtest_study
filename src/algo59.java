import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class algo59 {

    public static String solution(int n, int k, String[] cmd) {
        String answer = "";
        int table_size = n;
        Stack<Integer> stack = new Stack<>();

        for(int i = 0 ; i < cmd.length; i++){
            StringTokenizer st = new StringTokenizer(cmd[i], " ");
            String temp = st.nextToken();
            int num = 0;
            switch(temp.charAt(0)){
                // 0 1 2 3 4 5 6 7
                case 'D':
                    num = Integer.parseInt(st.nextToken());
                    k += num;
                    break;

                case 'U':
                    num = Integer.parseInt(st.nextToken());
                    k -= num;
                    break;
                case 'C':
                        stack.push(k);
                        table_size--;
                        if(k == table_size){
                            k--;
                        }
                    break;
                case 'Z':
                    int stackNum = stack.pop();
                    table_size++;
                    if(stackNum <= k)
                        k++;
                    break;
            }

        }
        StringBuilder builder = new StringBuilder();
        for(int i = 0 ; i <table_size; i++)
            builder.append("O");
        int stackSize = stack.size();
        for(int i = 0 ; i < stackSize; i++){
            builder.insert(stack.pop(), "X");
        }

        answer = builder.toString();
        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
        solution(8,2,cmd);
    }
}
