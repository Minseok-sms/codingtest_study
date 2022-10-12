
// 카카오 블라인드 : 문자열압축
public class algo9 {
    public int solution(String s) {
        int answer = 0;
        int stringHalf = s.length() / 2;
        for(int i = 1; i <= stringHalf ; i++){
            for(int j = 0; j < s.length(); j += i){
                String str1 = "";
                String str2 = "";
                str1 = s.substring(j, i - 1);
                //str2 = s.substring();

            }
        }








        return answer;
    }
    public static void main(String[] args) {

    }
}
