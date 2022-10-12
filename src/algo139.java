import java.util.*;

public class algo139 {
    /*
    프로그래머스 : 베스트앨범
     */

    public static int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        ArrayList<Integer> answerArray = new ArrayList<>();
        HashMap<String, Integer> genre = new HashMap<>();
        ArrayList<Integer> playsArray = new ArrayList<>();

        // 장르 -> 노래횟수를 hashMap에넣자
        // classic -> 1450
        // pop -> 3100

        for(int i = 0; i < genres.length; i++){
            genre.put(genres[i], genre.getOrDefault(genres[i], 0) + plays[i]);
        }


        // playsArray -> 1450, 3100
        for(String key : genre.keySet()){
            playsArray.add(genre.get(key));
        }

        // playsArray -> 3100, 1450
        Collections.sort(playsArray, Collections.reverseOrder());


        for(int i = 0 ; i < playsArray.size(); i++){
            int correctGenre = playsArray.get(i);
            ArrayList<Integer> temp = new ArrayList<>();
            ArrayList<Integer> temp2 = new ArrayList<>();
            for(int j = 0; j < genres.length; j++){
                if(genre.get(genres[j]) == correctGenre){
                    temp.add(j);
                    temp2.add(plays[j]);
                }
            }
            int[][] tempmap = new int[temp.size()][2];
            for(int j = 0 ; j < temp.size(); j++){
                tempmap[j][0] = temp.get(j);
                tempmap[j][1] = temp2.get(j);
            }
            Arrays.sort(tempmap, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o2[1] - o1[1];
                }
            });

            if(tempmap.length == 1){
                answerArray.add(tempmap[0][0]);
            }else{
                answerArray.add(tempmap[0][0]);
                answerArray.add(tempmap[1][0]);
            }
        }
        answer = new int[answerArray.size()];
        int count = 0;
        for(int i : answerArray) {
            System.out.println(i);
            answer[count++] = i;
        }

        return answer;
    }
    public static void main(String[] args) {
        String[] genre = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        solution(genre, plays);
    }
}
//
//import java.util.*;
//
//
//class Solution {
//    public int[] solution(String[] genres, int[] plays) {
//        int[] answer = {};
//        HashMap<String, Integer> map = new HashMap<>();
//        for(int i = 0; i < genres.length; i++)
//            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
//
//        List<Map.Entry<String, Integer>> list_entries = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
//
//        // 비교함수 Comparator를 사용하여 오름차순으로 정렬
//        Collections.sort(list_entries, new Comparator<Map.Entry<String, Integer>>() {
//            // compare로 값을 비교
//            public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2) {
//                // 오름 차순 정렬
//                return obj2.getValue().compareTo(obj1.getValue());
//            }
//        });
//        // 결과 출력
//        ArrayList<Integer> answerList = new ArrayList<>();
//        for(Map.Entry<String, Integer> entry : list_entries) {
//            int genreCount = 0;
//            for(int i = 0; i < genres.length; i++) {
//                if(entry.getKey().equals(genres[i]))
//                    genreCount++;
//            }
//            int[][] tempArr = new int[genreCount][2];
//            int tempArrCount = 0;
//            for(int i = 0 ; i < genres.length ; i++){
//                if(entry.getKey().equals(genres[i])) {
//                    tempArr[tempArrCount][0] = plays[i];
//                    tempArr[tempArrCount++][1] = i;
//                }
//            }
//            // 500 0
//            // 150 2
//            // 800 3
//            //Arrays.sort(tempArr, Collections.reverseOrder());
//            Arrays.sort(tempArr, new Comparator<int[]>() {
//                @Override
//                public int compare(int[] o1, int[] o2) {
//                    if(o1[0] == o2[0])
//                        return o2[1] - o1[1];
//                    else
//                        return o2[0] - o1[0];
//                }
//            });
//            if(genreCount == 1)
//                answerList.add(tempArr[0][1]);
//
//                // 600 3    // 600 3
//                // 500 1    // 600 2
//                // 600 0
//                // 600 2    // 500 1
//                // 600 0
//            else{ // 같은재생곡일시 번호가 낮은곳을 수록해야한다.
//                if(tempArr[0][0] == tempArr[1][0]){
//                    int count = 1;
//                    for(int i = 2; i < tempArr.length; i++){
//                        if(tempArr[i-1][0] == tempArr[i][0])
//                            count++;
//                    }
//                    answerList.add(tempArr[count][1]);
//                    answerList.add(tempArr[count-1][1]);
//                }else {
//                    answerList.add(tempArr[0][1]);
//                    answerList.add(tempArr[1][1]);
//                }
//            }
//        }
//        answer = new int[answerList.size()];
//        for(int i = 0; i < answerList.size(); i++)
//            answer[i] = answerList.get(i);
//
//        return answer;
//    }
//}