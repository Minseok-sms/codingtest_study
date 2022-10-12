import java.util.Arrays;

public class algo5 {
    public static boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);

        for(int i = 0; i < phone_book.length - 1; i++ ){
            if(phone_book[i+1].startsWith(phone_book[i])){
                answer = false;
                break;
            }
        }

        return answer;




//        boolean answer = true;
//        for(int i = 0; i < phone_book.length; i++){
//            String num = phone_book[i];
//            int numLength = num.length();
//            for(int j = 0; j < phone_book.length; j++){
//                if( j == i)
//                    continue;
//                if(phone_book[j].length() < numLength)
//                    continue;
//                String compNum = phone_book[j].substring(0, numLength);
//                if(num.equals(compNum)){
//                    answer = false;
//                }
//            }
//        }
//        return answer;
    }
    public static void main(String[] args) {
        String[] array = new String[]{"119", "97674223", "1195524421"};
        Boolean answer = solution(array);
        System.out.println("answer = " + answer);
        
        String[] array1 = new String[]{"aaa","aa","aaaa"};
        Arrays.sort(array1);
        for(String n1 : array1)
            System.out.println("n1 = " + n1);
    }
}
