import java.util.Arrays;
import java.util.Comparator;

public class algo74 {

    public static int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int cam = routes[0][1];
        answer = 1;
        for(int i = 1; i < routes.length; i++){
            if(routes[i][0] > cam){
                answer++;
                cam = routes[i][1];
            }
        }

        return answer;
    }
    public static void main(String[] args) {

    }
}

// -20 -15
// -18 -13
// -14 -5
// -10 -2

