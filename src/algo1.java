import java.util.*;

public class algo1 {

    public static int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }
    private static int dfs(int[] numbers, int target, int depth, int sum) {
            int solcnt = 0;
            if (depth == numbers.length) {
                if (sum == target) {
                    return 1;
                }
                return 0;
            }
            solcnt += dfs(numbers, target, depth + 1, sum + numbers[depth]);
            solcnt += dfs(numbers, target, depth + 1, sum - numbers[depth]);
            return solcnt;
    }
    public static void main(String[] args) {
        int[] numbers = new int[]{4,1,2,1};
        int target = 4;
        int sol = solution(numbers, target);
        System.out.println("sol = " + sol);
    }

}
