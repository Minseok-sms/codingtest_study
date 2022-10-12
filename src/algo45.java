public class algo45 {

    /*
    프로그래머스 : 멀쩡한 사각형
     */
    static double globalY, globalX;

    public static double algofunc(int y, int x){
        double a = -(globalY / globalX);
        double answer = a*x + globalY;
        return answer;
    }

    public static long solution(int w, int h) {
        long answer = 0;
        globalY = h;
        globalX = w;
        for(int i = 1 ; i <= w; i++){
            double temp = algofunc(0, i);
            answer += (int)temp;
        }
        answer *= 2;
        return answer;
    }
    public static void main(String[] args) {
        long answer = solution(8,12);
        System.out.println(answer);
    }
}
