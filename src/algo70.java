import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//public class algo70 {
//    public static long[] solution(long k, long[] room_number) {
//        long[] answer = {};
//        ArrayList<Long> array = new ArrayList<>();
//        boolean[] check = new boolean[(int) (k + 1)];
//        check[0] = true;
//
//
//
//        for(long i : room_number){
//            if(check[(int)i] == false){
//                check[(int)i] = true;
//                array.add(i);
//            }else {
//                for (long j = i + 1; j <= k; j++) {
//                    if (check[(int) j] == false) {
//                        check[(int) j] = true;
//                        array.add(j);
//                        break;
//                    }
//                }
//            }
//        }
//        answer = new long[array.size()];
//        long tp = 0;
//        for(long i : array)
//            answer[(int) tp++] = i;
//
//        return answer;
//    }
//    public static void main(String[] args) {
//        long[] a = {1,3,4,1,3,1};
//        solution(10, a);
//
//    }
//}
public class algo70 {
    static Map<Long, Long> map = new HashMap<>();
    public static long[] solution(long k, long[] room_number) {
        long[] answer = {};
        answer = new long[room_number.length];
        for(int i = 0; i < room_number.length; i++){
            answer[i] = findroom(room_number[i]);
        }
        return answer;
    }
    public static long findroom(long room){
        //해당방이 비어있는지 검사
        if(!map.containsKey(room)){
            map.put(room, room + 1);
            return room;
        }
        long nextroom = map.get(room);
        long find = findroom(nextroom);
        map.put(room,find);
        return find;
    }
    public static void main(String[] args) {
        long[] a = {1,3,4,1,3,1};
        solution(10, a);

    }
}

// 1 2 3 4 5 6
//