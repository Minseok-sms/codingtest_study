import java.util.ArrayList;

public class test {



    static class Node{
        int num1;
        int num2;
        Node(int num1, int num2){
            this.num1 = num1;
            this.num2 = num2;
        }
    }
    public static void main(String[] args) {
        ArrayList<Node> array = new ArrayList<>();
        array.add(new Node(1, 2));
        for(int i = 0 ; i < array.size(); i++){
            System.out.println(array.get(i).num1 + " " + array.get(i).num2);
        }
    }
}
