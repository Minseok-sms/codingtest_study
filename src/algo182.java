public class algo182 {

    public static int solution(int n, int[] stations, int w) {
        int answer = 0;
        int[] map = new int[n + 1];
        for (int i = 1; i <= n; i++)
            map[i] = 1;


        for (int i = 0; i < stations.length; i++) {
            int temp = stations[i];
            // temp = 4;
            // j = 3 ~ 5
            for (int j = temp - w; j <= temp + w; j++) {
                if (j < 1 || j >= n + 1)
                    continue;
                map[j] = 2;
            }
        }
        // 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
        // 2 2 2 2 2 2 2 2 2  2  2 2   2        2

        // 2로변환 완료후, 처음부터 탐색해서 w만큼 떨어진곳에 설치

        for (int i = 1; i < n + 1; i++) {

            //해당지점부터 w칸뒤에 자리가있는지 확인
            if (map[i] == 2)
                continue;

            int idx = 0;
            for (int j = i + w; j >= i; j--) {
                if (j >= 1 && j < n + 1) {
                    // 그칸에서 + W까지의 최대칸 반환
                    idx = j;
                    break;
                }
            }
            // idx지점에서 -w +w칸 2로변환시키자 가능한곳
            for (int j = idx - w; j <= idx + w; j++) {
                if (j < 1 || j >= n + 1)
                    continue;
                map[j] = 2;
            }

            answer++;
        }

        return answer;
    }
    public static void main(String[] args) {
        int n = 16;
        int[] stations = {1, 16};
        int w = 2;
        int answer = solution(n, stations, w);
        System.out.println(answer);
    }
}
