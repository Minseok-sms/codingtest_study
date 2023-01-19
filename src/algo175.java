import java.util.StringTokenizer;

public class algo175 {

    class Solution {
        // 40 40 40 40
        // 40 40 40 30
        // 40 40 40 20
        // 40 40 40 10
        
        static int[] sale = {10, 20, 30, 40};
        static int emoticonsSize = 0;
        static int usersSize = 0;
        static int maxPeople = 0, maxMoney = 0;
        static int[][] usersCopy;
        static int[] emoticonsCopy;

        public static void dfs(String str,int depth, int count){

            if(count == depth){
                // 순차적으로 할인비율을 뽑았다.
                // 할인비율 넣어라

                //4030
                // depth = 2;
                int[] psale = new int[depth];
                StringTokenizer st = new StringTokenizer(str, "0");
                for(int i = 0; i < depth; i++){
                    String temp = st.nextToken() + "0";
                    int num = Integer.parseInt(temp);
                    psale[i] = num;
                }

                // 계산하자.
                int peopleCnt = 0;
                int moneyCnt = 0;
                for(int i = 0 ; i < usersSize; i++){
                    int percent = usersCopy[i][0];
                    int money = usersCopy[i][1];


                    int buy = 0;
                    for(int j = 0 ; j < psale.length; j++){
                        System.out.println(psale[j]);
                        if(percent >= psale[j])
                            buy += emoticonsCopy[j] - (emoticonsCopy[j] * psale[j] / 100);

                    }
                    //System.out.println(buy);
                    //만약 이모티콘 총구매비용이 사용자 구매비용 (money)보다 클경우
                    // 서비스 가입한다.이때 돈은나뚜고 서비스 인원만 +1 증가
                    // 작으면 money를 증가
                    if(buy >= money)
                        peopleCnt++;
                    else
                        moneyCnt += buy;

                }

                // 만약 기존 서비스 가입자보다 지금 비율에서 더 높으면 저장
                if(maxPeople < peopleCnt){
                    maxPeople = peopleCnt;
                    maxMoney = moneyCnt;
                }else if(maxPeople == peopleCnt){
                    //만약 서비스가입자는 같은데 지금비율에서 이모티콘 판매액이 더높으면
                    if(maxMoney < moneyCnt){
                        maxPeople = peopleCnt;
                        maxMoney = moneyCnt;
                    }
                }
                return ;
            }
            dfs(str + sale[3], depth, count + 1);
            dfs(str + sale[2], depth, count + 1);
            dfs(str + sale[1], depth, count + 1);
            dfs(str + sale[0], depth, count + 1);

        }
        public int[] solution(int[][] users, int[] emoticons) {
            int[] answer = {};
            answer = new int[2];

            emoticonsSize = emoticons.length;
            usersSize = users.length;

            usersCopy = users;
            emoticonsCopy = emoticons;

            dfs("", emoticonsSize, 0);
            answer[0] = maxPeople;
            answer[1] = maxMoney;
            return answer;
        }
    }
}

