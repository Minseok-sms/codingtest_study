public class 소수점처리 {
    /*
    소수점 처리하는 소스
     */
    public static void main(String[] args) {


        // .00000으로 동일하게 되면 .0g하나만 표시된다.
        double num = 1522.0231232;
        num = num * 10;
        num = Math.floor(num);
        num = num / 10;

        System.out.println(num);
        System.out.println(String.format("%.2f", num));
    }
}