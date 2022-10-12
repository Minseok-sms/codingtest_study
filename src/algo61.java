import java.util.*;

public class algo61 {

/*
프로그래머스 : 길찾기 게임
 */


    static int[][] result;
    static Node[] node;
    static int idx = 0;
    static class Node{
        int x;
        int y;
        int value;
        Node left;
        Node right;
        Node(int x, int y, int value, Node left, Node right){
            this.x = x;
            this.y = y;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static void insertNode(Node parent, Node child){
        if(parent.x > child.x){
            if(parent.left == null) parent.left = child;
            else insertNode(parent.left, child);
        }else{
            if(parent.right == null) parent.right = child;
            else insertNode(parent.right, child);
        }
    }
    public static void preOrder(Node root){
        if(root != null){
            result[0][idx++] = root.value;
            preOrder(root.left);
            preOrder(root.right);
        }
    }
    public static void postOrder(Node root){
        if(root != null){
            postOrder(root.left);
            postOrder(root.right);
            result[1][idx++] = root.value;
        }
    }
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};
        node = new Node[nodeinfo.length];
        result = new int[2][nodeinfo.length];
        for(int i = 0; i < nodeinfo.length; i++)
            node[i] = new Node(nodeinfo[i][0], nodeinfo[i][1],i+1, null, null);
        Arrays.sort(node, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.y == o2.y){
                    return o1.x - o2.x;
                }else
                    return o2.y - o1.y;
            }
        });
        Node root = node[0];
        for(int i = 1; i < node.length; i++){
            insertNode(root, node[i]);
        }
        preOrder(root);
        idx = 0;
        postOrder(root);
        answer = result;
        return answer;
    }
    public static void main(String[] args) {

    }
}
