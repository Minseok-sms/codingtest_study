import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class algo162 {

    static class Node{
        int row;
        int col;
        int time;
        Node(int row, int col, int time){
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
    public static void main(String[] args) {
        ArrayList<Node> array = new ArrayList<>();

        array.add(new Node(2, 1, 1));

        array.add(new Node(3, 1, 1));
        array.add(new Node(3, 0, 1));
        array.add(new Node(3, 0, 3));
        array.add(new Node(4, 0, 3));
        array.add(new Node(4, 1, 1));


        Collections.sort(array, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.time > o2.time){
                    return o1.time - o2.time;
                }else if(o1.time == o2.time){
                    if(o1.row == o2.row){
                        return o1.col - o2.col;
                    }else
                        return o1.row - o2.row;
                }
                return -1;
            }
        });
        System.out.println(array.get(0).row + " " + array.get(0).col + " " + array.get(0).time);
    }
}
