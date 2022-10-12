import java.util.Arrays;

public class algo3 {
    public static int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);

        for(int i = citations.length; i >= 0; i--){ // 8
            int count = 0;
            for(int j = citations.length - 1; j >= 0; j--){
                if( citations[j] >= i)
                    count++;
            }
            if(count == i){
                answer = i;
                break;
            }
        }
        return answer;

    }
    public static void main(String[] args) {
        int[] array = new int[]{1,4};
        int sol = solution(array);
        System.out.println("sol = " + sol);
    }
}
