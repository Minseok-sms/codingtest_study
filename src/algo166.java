import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
import java.io.*;

public class algo166 {

    static ArrayList<Node>[] array;
    static int N;
    static class Node{
        int end;
        int value;
        Node(int end, int value){
            this.end = end;
            this.value = value;
        }
    }
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       N = Integer.parseInt(br.readLine());
       array = new ArrayList[N + 1];

       for(int i = 0 ; i < N + 1; i++)
           array[i] = new ArrayList<>();

       for(int i = 0 ; i < N - 1; i ++){
           StringTokenizer st = new StringTokenizer(br.readLine(), " ");
           int start = Integer.parseInt(st.nextToken());
           int end = Integer.parseInt(st.nextToken());
           int value = Integer.parseInt(st.nextToken());
           array[start].add(new Node(end, value));
           array[end].add(new Node(start, value));
       }

       for(int i = 1 ; i <= N; i++){

       }
    }
}
