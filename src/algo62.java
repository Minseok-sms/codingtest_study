import java.util.HashMap;

public class algo62 {

    /*
    다단계 칫솔판매
     */

    static HashMap<String, String> map = new HashMap<>();
    static HashMap<String, Integer> money = new HashMap<>();
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = {};

        for(int i = 0 ; i < enroll.length; i++) {
            map.put(enroll[i], referral[i]);
        }
        for(int i = 0; i < enroll.length; i++){
            money.put(enroll[i], 0);
        }
        int total = 0;
        for(int i = 0 ; i < seller.length; i++){
            String name = seller[i];
            int nameMoney = amount[i] * 100;

            // young 1200
            while(true){
                if(nameMoney / 10 == 0 || name.equals("-")) {
                    money.put(name, money.getOrDefault(name, 0) + nameMoney);
                    break;
                }
                else {
                    money.put(name, money.getOrDefault(name, 0) + nameMoney - (nameMoney / 10));
                }

                nameMoney = nameMoney / 10;
                name = map.get(name); // center 1;
            }
        }

        answer = new int[enroll.length];
        int idx = 0;
        for(String key : enroll) {
            answer[idx++] = money.get(key);

        }
        return answer;
    }

    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};
        solution(enroll, referral, seller, amount);
    }
}
