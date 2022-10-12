import java.util.*;


// 카카오 블라인드 : 신고결과받기
public class algo8 {
    public static int[] solution(String[] id_list, String[] report, int k) {
            int[] answer = new int[id_list.length];

            //중복 신고를 위한 중복제거
            List<String> reportList = new ArrayList<>();
            for (String str : report) {
                if (!reportList.contains(str)) {
                    reportList.add(str);
                }
            }
            HashMap<String, List<String>> reportMap = new HashMap<>();

            for(String id : id_list)
                reportMap.put(id, new ArrayList<>());

            for(String str : reportList){
                StringTokenizer st = new StringTokenizer(str);
                String reporter = st.nextToken();
                String reported = st.nextToken();
                reportMap.get(reported).add(reporter);
            }

            HashMap<String, Integer> result = new HashMap<>();
            for(String key : reportMap.keySet()){
                if(reportMap.get(key).size() >= k){
                    for(String person : reportMap.get(key)){
                        result.put(person, result.getOrDefault(person, 0) + 1);
                    }
                }
            }
            for(int i = 0; i < id_list.length; i++){
                answer[i] = result.getOrDefault(id_list[i], 0);
                System.out.println("answer[i] = " + answer[i]);
            }
            return answer;
    }
    public static void main(String[] args) {
        StringTokenizer st = new StringTokenizer("hello world", " ");
        System.out.println("st.nextToken() = " + st.nextToken());
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};


        int[] answer = new int[id_list.length];
        answer = solution(id_list, report, 2);



    }
}

/*
 int[] answer = {};

                //중복 신고를 위한 중복제거
            List<String> reportList = new ArrayList<>();
            for (String str : report) {
                if (!reportList.contains(str)) {
                    reportList.add(str);
                }
            }
            HashMap<String, Integer> map = new HashMap<>();
            for(int i = 0; i < reportList.size(); i++){
                StringTokenizer st = new StringTokenizer(reportList.get(i), " ");
                st.nextToken();
                String str = st.nextToken();
                map.put(str, map.getOrDefault(str, 0) + 1);
            }



            ArrayList<String> prob = new ArrayList<>();
            for(String key : map.keySet()){
                if(map.get(key) >= k)
                    prob.add(key);
            }
            answer = new int[id_list.length];
            for(int i = 0; i < id_list.length; i++)
                answer[i] = 0;
            HashMap<String, Integer> answerMap = new HashMap<>();

            for(int i = 0; i < report.length; i++){
                int count = 0;
                int id_list_count = 0;
                for(int j = 0; j < prob.size(); j++){
                    StringTokenizer st = new StringTokenizer(report[i], " ");
                    st.nextToken();
                    if(st.nextToken().equals(prob.get(j))){
                        count++;
                    }
                }
                StringTokenizer st1 = new StringTokenizer(report[i], " ");
                String str = st1.nextToken();

                answerMap.put(str, answerMap.getOrDefault(str, 0) + count);
            }
           for(int i = 0; i < id_list.length; i++) {
               if(answerMap.get(id_list[i]) != null)
                   answer[i] = answerMap.get(id_list[i]);
           }
            return answer;
 */
