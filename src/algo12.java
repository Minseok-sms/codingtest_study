import java.util.HashMap;
import java.util.Map;
 /*
 Lv. 1  로또의 최고 순위와 최저 순위

  */
public class algo12 {

    public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> grade = new HashMap<>();

        grade.put(6,1);
        grade.put(5,2);
        grade.put(4,3);
        grade.put(3,4);
        grade.put(2,5);

        // 최고 순위번호
        int upCount = 0;
        int downCount = 0;
        for(int i : lottos){
            if(i != 0) map.put(i, 0);
            else upCount++;
        }
        for(int i : win_nums){
            if(map.get(i) != null)
                map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for(Integer key : map.keySet()){
            if(map.get(key) == 1) {
                upCount++;
                downCount++;
            }
        }
        if(upCount == 0 || upCount == 1)
            answer[0] = 6;
        else
            answer[0] = grade.get(upCount);

        if(downCount == 0 || downCount == 1)
            answer[1] = 6;
        else
            answer[1] = grade.get(downCount);
        return answer;
    }
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] array = new int[]{1,2,3,4,10};
        for(int i : array){
            System.out.println("i = " + i);
        }

        int[] lottos = new int[]{44, 1, 0, 0, 31, 25};
        int[] win_nums = new int[]{31, 10, 45, 1, 6, 19};
        solution(lottos,win_nums);
    }
}
