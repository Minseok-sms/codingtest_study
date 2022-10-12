import java.text.SimpleDateFormat;
import java.util.Arrays;

import java.util.StringTokenizer;
import java.text.ParseException;

public class algo63 {
    /*
    프로그래머스 광고삽입. x
     */

    public static int changeToSecond(int hh, int mm, int ss){
        int total = hh * 3600 + mm * 60 + ss;
        return total;
    }
    public static String solution(String play_time, String adv_time, String[] logs){
        String answer = "";
        if(play_time.equals(adv_time)){
            answer += "00:00:00";
            return answer;
        }
        Arrays.sort(logs);


        int min = 0;

        StringTokenizer st = new StringTokenizer(adv_time, ":");
        int adv = changeToSecond(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()));

        for(int i = 0; i < logs.length; i++){

            st = new StringTokenizer(logs[i], "-");
            String startString = st.nextToken();
            st = new StringTokenizer(startString, ":");
            int start = changeToSecond(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
            int last = start + adv;

            int total = 0;
            for(int j = 0 ; j < logs.length; j++){

                StringTokenizer st1 = new StringTokenizer(logs[j], "-");
                String temp1 = st1.nextToken();
                String temp2 = st1.nextToken();

                st1 = new StringTokenizer(temp1, ":");

                int firstTime = changeToSecond(Integer.parseInt(st1.nextToken()),Integer.parseInt(st1.nextToken()),
                        Integer.parseInt(st1.nextToken()));

                st1 = new StringTokenizer(temp2, ":");
                int lastTime = changeToSecond(Integer.parseInt(st1.nextToken()),Integer.parseInt(st1.nextToken()),
                        Integer.parseInt(st1.nextToken()));


                if(i == j){
                    int k1 = Math.max(start, firstTime);
                    int k2 = Math.min(last,lastTime);
                    total += k2 - k1;
                }
                else{
                    int count = 0;
                    for(int k = start ; k < last; k++){
                        if(k >= firstTime && k <= lastTime)
                            count++;
                    }
                    total += count;

                }

            }
            if(total > min){
                answer = "";
                answer += startString;
                min = total;
            }
        }

        System.out.println(answer);
        return answer;
    }
    public static void main(String[] args) throws ParseException {
        String play_time = "02:03:55";
        String adv_time = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
        solution(play_time,adv_time,logs);
    }
}
