import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class algo142 {

    /*
    프로그래머스 : 주차 요금 계산
     */
    public static int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        int def_T = fees[0];
        int def_M = fees[1];
        int count_T = fees[2];
        int count_M = fees[3];
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> timeStack = new HashMap<>();
        for(int i = 0 ; i < records.length; i++){
            StringTokenizer st = new StringTokenizer(records[i], " ");
            String timeTemp = st.nextToken();
            StringTokenizer st1 = new StringTokenizer(timeTemp, ":");

            //시간을 분으로 바꾸자
            int timeToMin = Integer.parseInt(st1.nextToken()) * 60 + Integer.parseInt(st1.nextToken());
            // 차량번호판
            int number = Integer.parseInt(st.nextToken());
            // 출차 or 입차
            String state = st.nextToken();

            if(state.equals("IN")){
                map.put(number, timeToMin);
            }else{
                int time1 = map.get(number);
                // 해당차량에 누적시간더해주고
                timeStack.put(number, timeStack.getOrDefault(number, 0) + timeToMin - time1);
                // 기존 map은 -1으로 초기화한다 왜? 나갓다는걸 표시하기위해
                map.put(number, -1);
            }
        }

        // 들어가고 안나온차를 판별하자. 22:59분에 나온다는걸 계산
        for(int i : map.keySet()){
            if(map.get(i) != -1){
                int time1 = map.get(i);
                // 23시 59분 -> 1439
                timeStack.put(i, timeStack.getOrDefault(i, 0) + 1439 - time1);
            }
        }
        int[][] carCash = new int[map.size()][2];
        int count = 0;
        for(int i : timeStack.keySet()){
            carCash[count][0] = i;
            if(timeStack.get(i) < def_T)
                carCash[count][1] = def_M;
            else
                carCash[count][1] = def_M + (int)Math.ceil((double)(timeStack.get(i) - def_T) / count_T ) * count_M;
            count++;
        }

        Arrays.sort(carCash, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        answer = new int[map.size()];
        for(int i = 0 ; i < carCash.length; i++)
            answer[i] = carCash[i][1];

        return answer;
    }
    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        solution(fees, records);
    }

}
