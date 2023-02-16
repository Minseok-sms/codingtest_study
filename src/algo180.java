//public class algo180 {
// 프로그래머스 : 110

    /*
    import java.util.*;
import java.io.*;
class Solution {
    public String[] solution(String[] s) {
        String[] answer = {};
        answer = new String[s.length];
        for(int i = 0; i < s.length; i++){
            String str = s[i];

            int cnt = 0; // 110갯수 카운트

            // 110110
            while(str.indexOf("110") >= 0){
                int idx = str.indexOf("110");
                // 110이마지막이면
                if(str.length() == idx + 2)
                    str = str.substring(0, idx);
                else
                    str = str.substring(0, idx) + str.substring(idx + 3);
                cnt++;
            }
            //System.out.println(str);
            // 뒤에서 마지막 0의 인덱스값찾아라
            int idx1 = str.lastIndexOf("0");
            //만약 찾을수 없으면
            if(idx1 == -1){
                // 110을 맨앞으로 가져오자
                String temp = "";
                for(int j = 0; j < cnt; j++){
                    temp += "110";
                }
                temp += str;
                answer[i] = temp;
            }else{
                // 0111
                for(int j = 0; j < cnt; j++){
                    int idx = str.lastIndexOf("0");

                    //만약 idx가 마지막문자이면?
                    if(idx == str.length() - 1)
                        str += "110";
                    else if(idx == 0){
                        str = "0110" + str.substring(idx + 1);
                    }else
                        str = str.substring(0, idx + 1) + "110" + str.substring(idx + 1);
                    // 0110111
                }
                answer[i] = str;
            }
            //

        }
        return answer;
    }
}

// 0110111

// 11

// 11110
// 11
     */
/*
    public static void main(String[] args) {
        class Solution {
            public String[] solution(String[] s) {
                String[] ret = new String[s.length];
                for(int i=0 ; i<s.length ; i++) {
                    ret[i] = helper(s[i]);
                }
                return ret;
            }
            public String helper(String s) {
                StringBuilder sb = new StringBuilder();
                StringBuilder plus = new StringBuilder();

                for(int i=0 ; i<s.length() ; i++) {
                    char c = s.charAt(i);
                    sb.append(c);
                    if(sb.length()>=3 && sb.charAt(sb.length()-3)=='1' && sb.charAt(sb.length()-2)=='1'  && sb.charAt(sb.length()-1)=='0') {
                        plus.append("110");
                        sb.delete(sb.length()-3, sb.length());
                    }
                }
                if(plus.length()>0) {
                    if(sb.indexOf("0")==-1) {
                        sb.insert(0,plus);
                    } else {
                        sb.insert(sb.lastIndexOf("0")+1,plus);
                    }
                }
                return sb.toString();
            }
        }
    }
}

*/
