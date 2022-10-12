/*
    카카오 블라인드 : 신규 아이디 추천
 */

public class algo10 {
    public static String solution(String new_id) {
        String answer = "";

        // 1단계 모든 대문자를 소문자로 치환.
        new_id = new_id.toLowerCase();

        System.out.println("new_id 1case = " + new_id);
        // 2단계 소문자, 숫자, - _  . 아닌문자 제거.
        for(int i = 0 ; i < new_id.length(); i++){
            char a = new_id.charAt(i);
            if(97 <= (int)a && (int)a <= 122) {
                continue;
            }
            if(48 <= (int)a && (int)a <= 57){
                continue;
            }

            if(a == '-' || a == '_' || a == '.'){
                continue;
            }else {
                new_id = new_id.substring(0, i) + new_id.substring(i + 1);
                i--;
            }
        }
        System.out.println("new_id 2case = " + new_id);

        // 3단계 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환

        for(int i = 1 ; i < new_id.length(); i++){
            boolean check = Character.compare(new_id.charAt(i-1), new_id.charAt(i)) == 0;
            if(check && new_id.charAt(i) == '.'){
                new_id = new_id.substring(0, i) + new_id.substring(i+1);
                i--;
            }
        }


        System.out.println("new_id 3case = " + new_id);

        //4단계 아이디의 처음에 위치한 '.'가 제거

        if(new_id.charAt(0) == '.') {
            if(new_id.length() == 1)
                new_id = "";
            else
                new_id = new_id.substring(1);
        }
        if(new_id.length() - 1 >= 1) {
            if (new_id.charAt(new_id.length() - 1) == '.')
                new_id = new_id.substring(0, new_id.length() - 1);
        }

        System.out.println("new_id 4case = " + new_id);

        //5단계 만약 아이디가 빈문자열이다 -> 'a' 대입
        if(new_id.length() == 0)
            new_id = new_id.concat("a");

        System.out.println("new_id 5case = " + new_id);

        //6단계 만약 아이디가 16자 이상이면 0~15자까지만 적용
        if(new_id.length() >= 16)
            new_id = new_id.substring(0,15);

        if(new_id.charAt(new_id.length() -1) == '.')
            new_id = new_id.substring(0, new_id.length() -1);

        System.out.println("new_id 6case = " + new_id);


        //7단계 만약 아이디 길이가 2자이하면 마지막문자를 계속끝에 더해서 길이3 될때 까지 붙임
        if(new_id.length() <= 2){
            if(new_id.length() == 1){
                char a = new_id.charAt(0);
                new_id = new_id.concat(Character.toString(a));
                new_id = new_id.concat(Character.toString(a));
            }else {
                char a = new_id.charAt(1);
                new_id = new_id.concat(Character.toString(a));
            }
        }

        System.out.println("new_id 7case = " + new_id);
        answer = new_id;
        return answer;
    }

    public static void main(String[] args) {
        String str = "abcdefghijklmn.p";
        System.out.println("str = " + str);
        System.out.println("str. = " + solution(str));
    }
}
