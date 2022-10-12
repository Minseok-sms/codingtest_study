//import java.util.*;
//
//public class algo7 {
//
//    public static int[] solution(String[] genres, int[] plays) {
//        int[] answer = {};
//        ArrayList<Integer> answerA = new ArrayList<>();
//        HashMap<String, Integer> map = new HashMap<>();
//        //HashMap<String, Integer> map2 = new HashMap<>();
//
//
//        for(int i = 0; i < genres.length; i++) {
//            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
//            //  map2.put(genres[i], plays[i]);
//        }
//
//        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(map.entrySet());
//        entryList.sort(new Comparator<Map.Entry<String, Integer>>() {
//            @Override
//            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                return o2.getValue() - o1.getValue();
//            }
//        });
//
//        for(Map.Entry<String, Integer> entry : entryList){
////            ArrayList<ArrayList<Integer>> array = new ArrayList<>();
////            ArrayList<Integer> index = new ArrayList<>();
////            ArrayList<Integer> value = new ArrayList<>();
//            HashMap<Integer, Integer> sortArr = new HashMap<>(); // hashmap -> plays, index // key값 기준정렬
//            //System.out.println("entry.getKey() = " + entry.getKey());
//            for(int i = 0 ; i < genres.length; i++){
//                if(entry.getKey().equals(genres[i])) // key값이랑 장르값이 같을시
//                    sortArr.put(plays[i], i);
//            }
//            Object[] mapkey = sortArr.keySet().toArray();
//            Arrays.sort(mapkey);
//
//            int count = 0;
//            for(Integer key : sortArr.keySet()){
//                if(sortArr.size() == 1)
//                    answerA.add(sortArr.get(key));
//                else if(sortArr.size() > 1 && count < 2) {
//                    answerA.add(sortArr.get(key));
//                    count++;
//                }
//               // System.out.println("sortArr.get(key) = " + sortArr.get(key));
//            }
//        }
//        answer = new int[answerA.size()];
//        for(int i = 0 ; i < answerA.size(); i++)
//            answer[i] = answerA.get(i);
//
//
//        return answer;
//    }
//    public static void main(String[] args) {
//        String[] genres = new String[]{"classic", "pop", "classic", "classic", "pop"};
//        int[] plays = new int[]{500,600,150,800,2500};
//        plays = solution(genres, plays);
//        for(int i : plays)
//            System.out.println("i = " + i);
//    }
//}


import java.util.*;

public class algo7 {

    public static int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < genres.length; i++)
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);

        List<Map.Entry<String, Integer>> list_entries = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());

        // 비교함수 Comparator를 사용하여 오름차순으로 정렬
        Collections.sort(list_entries, new Comparator<Map.Entry<String, Integer>>() {
            // compare로 값을 비교
            public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2) {
                // 오름 차순 정렬
                return obj2.getValue().compareTo(obj1.getValue());
            }
        });
        // 결과 출력
        ArrayList<Integer> answerList = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : list_entries) {
            int genreCount = 0;
            for(int i = 0; i < genres.length; i++) {
                if(entry.getKey().equals(genres[i]))
                    genreCount++;
            }
            int[][] tempArr = new int[genreCount][2];
            int tempArrCount = 0;
            for(int i = 0 ; i < genres.length ; i++){
                if(entry.getKey().equals(genres[i])) {
                    tempArr[tempArrCount][0] = plays[i];
                    tempArr[tempArrCount++][1] = i;
                }
            }
            // 500 0
            // 150 2
            // 800 3
            //Arrays.sort(tempArr, Collections.reverseOrder());
            Arrays.sort(tempArr, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[0] == o2[0])
                        return o2[1] - o1[1];
                    else
                        return o2[0] - o1[0];
                }
            });
            if(genreCount == 1)
                answerList.add(tempArr[0][1]);

            // 600 3    // 600 3
            // 500 1    // 600 2
                        // 600 0
            // 600 2    // 500 1
            // 600 0
            else{ // 같은재생곡일시 번호가 낮은곳을 수록해야한다.
                if(tempArr[0][0] == tempArr[1][0]){
                    int count = 1;
                    for(int i = 2; i < tempArr.length; i++){
                        if(tempArr[i-1][0] == tempArr[i][0])
                            count++;
                    }
                    answerList.add(tempArr[count][1]);
                    answerList.add(tempArr[count-1][1]);
                }else {
                    answerList.add(tempArr[0][1]);
                    answerList.add(tempArr[1][1]);
                }
            }
        }
        answer = new int[answerList.size()];
        for(int i = 0; i < answerList.size(); i++)
            answer[i] = answerList.get(i);

        return answer;
    }

    // 500 2
    // 500 3
    // 500 1
    public static void main(String[] args) {
        String[] genres = new String[]{"classic", "pop", "classic", "classic", "pop"};
        int[] plays = new int[]{500,600,150,800,2500};
        // plays = solution(genres, plays);
//        for(int i : plays)
//            System.out.println("i = " + i);
        int[][] temp = new int[][]{{500,2}, {500,3}, {500, 1},{500 , 15}};
        Arrays.sort(temp, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0])
                    return o2[1] - o1[1];
                else
                    return o2[0] - o1[0];
            }
        });
        for(int i = 0 ; i < temp.length; i++)
            System.out.println("temp[i][1] = " + temp[i][1]);
    }
}
