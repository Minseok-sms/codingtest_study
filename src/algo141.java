import java.util.*;

public class algo141 {
    /*
    프로그래머스 : 등산코스 정하기
     */

    static ArrayList<Node>[] array;

    static class Node{
        int end;
        int value;
        Node(int end, int value){
            this.end = end;
            this.value = value;
        }
    }
    private static boolean isGate(int num, int[] gates) {
        for (int gate : gates) {
            if (num == gate) return true;
        }
        return false;
    }

    // num이 산봉우리인지 확인
    private static boolean isSummit(int num, int[] submits) {
        for (int submit : submits) {
            if (num == submit) return true;
        }
        return false;
    }
    public static int[] dijkstra(int n, int[] gates, int[] summits){
        Queue<Node> queue = new LinkedList<>();
        int[] intensity = new int[n + 1];

        Arrays.fill(intensity, Integer.MAX_VALUE);

        for(int i : gates){
            queue.add(new Node(i, 0));
            intensity[i] = 0;
        }


        while(!queue.isEmpty()){
            Node temp = queue.poll();
            int end = temp.end;
            int weight = temp.value;

            if(weight < intensity[end])
                continue;

            for(int i = 0 ; i < array[end].size(); i++){
                Node temp1 = array[end].get(i);

                int dis = Math.max(intensity[end], temp1.value);
                if(intensity[temp1.end] > dis) {
                    intensity[temp1.end] = dis;
                    queue.add(new Node(temp1.end, dis));
                }

            }
        }

        int summitNum = Integer.MAX_VALUE;
        int lowNum = Integer.MAX_VALUE;

        Arrays.sort(summits);

        for(int summit : summits){
            if(intensity[summit] < lowNum){
                summitNum = summit;
                lowNum = intensity[summit];
            }
        }

        return new int[]{summitNum, lowNum};
    }
    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        array = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++)
            array[i] = new ArrayList<>();

        for(int i = 0; i < paths.length; i++){
            int start = paths[i][0];
            int end = paths[i][1];
            int weight = paths[i][2];

            //출발지랑 산봉우리는 한방향으로만표시
            // 나머지 쉼터는 양뱡향
            if(isGate(start, gates) || isSummit(end, summits)){
                array[start].add(new Node(end, weight));
            }else if(isGate(end, gates) || isSummit(start, summits)){
                array[end].add(new Node(start, weight));
            }else {
                array[start].add(new Node(end, weight));
                array[end].add(new Node(start, weight));
            }

        }

        return dijkstra(n,gates,summits);
    }
    public static void main(String[] args) {
//        int[][] paths = {{1,4,4}, {1,6,1}, {1,7,3}, {2, 5, 2}, {3, 7,4}, {5,6,6}};
//        int[][] paths = {{1,2,3}, {2,3,5}, {2,4,2}, {2, 5, 4}, {3, 4,4}, {4,5,3},{4,6,1}, {5,6,1}};
        int[][] paths = {{1,3,10}, {1,4,20}, {2,3,4}, {2,4,6}, {3,5,20}, {4,5,6}};

        int[] gates = {1,2};
        int[] summits = {5,};
        solution(5, paths, gates, summits);
    }
}

//
//import java.util.*;
//
//public class algo141 {
//    /*
//    프로그래머스 : 등산코스 정하기
//     */
//    static HashMap<Integer, Integer> summitMap = new HashMap<>();
//    static HashMap<Integer, Integer> gateMap = new HashMap<>();
//    static ArrayList<Node>[] array;
//    static boolean[] visited;
//    static class Node{
//        int end;
//        int value;
//        Node(int end, int value){
//            this.end = end;
//            this.value = value;
//        }
//    }
//    public static void bfs(int start, int value){
//        Queue<Node> queue = new LinkedList<>();
//        queue.add(new Node(start, 0));
//        visited[start] = true;
//
//        while(!queue.isEmpty()){
//            Node temp = queue.poll();
//            int startS = temp.end;
//            if(summitMap.containsKey(startS)){
//                summitMap.put(startS, Math.min(summitMap.get(startS), temp.value));
//                visited[startS] = false;
//                continue;
//            }
//            for(int i = 0 ; i < array[startS].size(); i++){
//                if(visited[array[startS].get(i).end] == true)
//                    continue;
//                // 만약 돌다가 출구이면 continue;
//                if(gateMap.containsKey(array[startS].get(i).end))
//                    continue;
//
//                if(!summitMap.containsKey(array[startS].get(i).end))
//                    visited[array[startS].get(i).end] = true;
//                queue.add(new Node(array[startS].get(i).end, Math.max(temp.value, array[startS].get(i).value)));
//            }
//        }
//    }
//    //    public static void dfs(int start, int value){
////        visited[start] = true;
////        if(summitMap.containsKey(start)){
////            //만약 산정상에 도착햇으면 그올라온 value중 더작은값으로 넣자
////            summitMap.put(start, Math.min(summitMap.get(start), value));
////            return ;
////        }
////        for(int i = 0 ; i < array[start].size(); i++){
////
////            if(visited[array[start].get(i).end] == true)
////                continue;
////            // 만약 돌다가 출구이면 continue;
////            if(gateMap.containsKey(array[start].get(i).end))
////                continue;
////            visited[array[start].get(i).end] = true;
////            dfs(array[start].get(i).end, Math.max(value, array[start].get(i).value));
////            visited[array[start].get(i).end] = false;
////
////        }
////    }
//    private static boolean isGate(int num, int[] gates) {
//        for (int gate : gates) {
//            if (num == gate) return true;
//        }
//        return false;
//    }
//
//    // num이 산봉우리인지 확인
//    private static boolean isSummit(int num, int[] submits) {
//        for (int submit : submits) {
//            if (num == submit) return true;
//        }
//        return false;
//    }
//    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
//        int[] answer = {};
//
//        // map에 산봉우리 index 넣기
//        for(int i : summits)
//            summitMap.put(i, Integer.MAX_VALUE);
//
//        // map에 gate index 넣기
//        for(int i : gates)
//            gateMap.put(i, 1);
//
//        array = new ArrayList[n + 1];
//        for(int i = 0; i < n + 1; i++)
//            array[i] = new ArrayList<>();
//
//        for(int i = 0; i < paths.length; i++){
//            int start = paths[i][0];
//            int end = paths[i][1];
//            int weight = paths[i][2];
//
//
//            //출발지랑 산봉우리는 한방향으로만표시
//            // 나머지 쉼터는 양뱡향
//            if(isGate(start, gates) || isSummit(end, summits)){
//                array[start].add(new Node(end, weight));
//            }else if(isGate(end, gates) || isSummit(start, summits)){
//                array[end].add(new Node(start, weight));
//            }else {
//                array[start].add(new Node(end, weight));
//                array[end].add(new Node(start, weight));
//            }
//
//        }
//
//        // 출입구 두지역에서 출발하자.
//        for(int i : gates){
//            visited = new boolean[n + 1];
//            bfs(i, 0);
//        }
//        //
//        int[][] temp = new int[summits.length][2];
//        int count = 0;
//        for(int i : summitMap.keySet()){
//            temp[count][0] = i;
//            temp[count][1] = summitMap.get(i);
//            count++;
//        }
//
//        Arrays.sort(temp, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                int num1 = o1[1];
//                int num2 = o2[1];
//                if(num1 == num2){
//                    return o1[0] - o2[0];
//                }else
//                    return o1[1] - o2[1];
//            }
//        });
//
//        answer = new int[2];
//        answer[0] = temp[0][0];
//        answer[1] = temp[0][1];
//
//        return answer;
//    }
//    public static void main(String[] args) {
////        int[][] paths = {{1,4,4}, {1,6,1}, {1,7,3}, {2, 5, 2}, {3, 7,4}, {5,6,6}};
////        int[][] paths = {{1,2,3}, {2,3,5}, {2,4,2}, {2, 5, 4}, {3, 4,4}, {4,5,3},{4,6,1}, {5,6,1}};
//        int[][] paths = {{1,3,10}, {1,4,20}, {2,3,4}, {2,4,6}, {3,5,20}, {4,5,6}};
//
//        int[] gates = {1,2};
//        int[] summits = {5,};
//        solution(5, paths, gates, summits);
//    }
//}
//
//
