public class algo84 {

    static int globalCount = 0;
    public static void dfs(int[] numbers, int target,int sum, int depth){
        if(depth == numbers.length){
            if(target == sum){
                globalCount++;
                return ;
            }
            return ;
        }
        dfs(numbers, target, sum - numbers[depth], depth + 1);
        dfs(numbers, target, sum + numbers[depth], depth + 1);
    }
    public static int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        System.out.println(globalCount);
        return globalCount;
    }
    public static void main(String[] args) {
        int[] number = {1,1,1,1,1};
        solution(number, 3);
    }
}
