import java.io.*;
import java.util.Stack;

public class algo137 {
    /*
    백준 : 1874
    스택수열
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        for(int i = 0 ; i < N; i++)
            array[i] = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();

        int idx = 0;
        int startNum = 1;
        while(true){
            if(!stack.contains(array[idx])){
                if(array[idx] < startNum){
                    System.out.println("NO");
                    System.exit(0);
                }
                for(int i = startNum; i <= array[idx]; i++) {
                    stack.push(i);
                    sb.append("+\n");
                }
                startNum = array[idx] + 1;
                stack.pop();
                sb.append("-\n");
                idx++;
            }else{
                while(true){
                    if(stack.peek() != array[idx]) {
                        stack.pop();
                        sb.append("-\n");
                    }
                    else{
                        stack.pop();
                        sb.append("-\n");
                        break;
                    }
                }
                idx++;
            }
            if(idx == array.length) {
                break;
            }
        }
        System.out.println(sb);
    }
}
