//public class algo15 {
//
//    public static int solution(int[] numbers, int target) {
//        int answer = 0;
//        answer = dfs(numbers, target, 0,0);
//        return answer;
//
//    }
//    public static int dfs(int[] numbers, int target, int depth, int sum){
//        int solcnt = 0;
//        if (depth == numbers.length) {
//            if (sum == target) {
//                return 1;
//            }
//            return 0;
//        }
//        solcnt += dfs(numbers, target, depth + 1, sum + numbers[depth]);
//        solcnt += dfs(numbers, target, depth + 1, sum - numbers[depth]);
//        return solcnt;
//
//
//        //
//
//    }
//    public static void main(String[] args) {
//        int[] array = new int[]{4,1,2,1};
//        System.out.println("solution = " + solution(array,4));
//    }
//}
//
//
public class algo15 {

    public static int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }
    public static int dfs(int[] numbers, int target, int depth, int sum){
        int count = 0;
        if(depth == numbers.length){
            if(sum == target)
                return 1;
            else
                return 0;
        }
        count += dfs(numbers, target, depth + 1, sum + numbers[depth]);
        count += dfs(numbers, target, depth + 1, sum - numbers[depth]);

        return count;
    }
    public static void main(String[] args) {
        int[] array = new int[]{4,1,2,1};
        System.out.println("solution = " + solution(array,4));
    }
}


