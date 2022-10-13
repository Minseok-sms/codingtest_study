import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class algo162 {

    /*
    백준 : 1918
    후위표기식 (다시)
     */

    static HashMap<Character, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Stack<Character> stack = new Stack<>();

        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);

        String answer = "";
        boolean check = false;
        for(int i = 0; i < str.length(); i++){
            char temp = str.charAt(i);
            //AB+C+DE/FG++HI/++
            // +(/+
            //A+B+C+(D/E+(F+G)+(H/I))
            //AB+C+DE
            //피연산자 일시 바로 출력
            if('A' <= temp && temp <= 'Z')
                answer += temp;
            else if(map.containsKey(temp) && check == false){
                // 연산자이고, 괄호진행안될때 자기보다 우선순위가 높거나 같은것들을 뺴고 자신을 스택에 담는다.
                int size = stack.size();
                for(int j = 0 ; j < size; j++){
                    if(map.get(stack.peek()) >= map.get(temp)){
                        answer += stack.pop();
                    }
                }
                stack.push(temp);
            }else{
                if(temp == ')'){
                    while(true){
                        char temp1 = stack.pop();
                        if(temp1 == '(')
                            break;
                        answer += temp1;
                    }
                }else {
                    check = true;
                    stack.push(temp);
                }
            }
        }
        while(!stack.isEmpty())
            answer += stack.pop();
        System.out.println(answer);

    }
}

// ABC+*DE/-

// -/