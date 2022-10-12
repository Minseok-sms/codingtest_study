public class algo66 {




    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;
        int [][] prefix_arr = new int[n+1][m+1];

        for(int [] row : skill)
        {
            int type = row[0];
            int degree = row[5];
            int r1 = row[1];
            int c1 = row[2];
            int r2 = row[3];
            int c2 = row[4];


            if(type == 1) {
                prefix_arr[r1][c1] += -degree;
                prefix_arr[r1][c2 + 1] += degree;
                prefix_arr[r2 + 1][c1] += degree;
                prefix_arr[r2 + 1][c2 + 1] += -degree;
            }else{
                prefix_arr[r1][c1] += degree;
                prefix_arr[r1][c2 + 1] += -degree;
                prefix_arr[r2 + 1][c1] += -degree;
                prefix_arr[r2 + 1][c2 + 1] += degree;
            }


        }
        for(int i = 0 ; i < n + 1; i++){
            int sum = 0;
            for(int j = 0; j < m + 1; j++){
                sum += prefix_arr[i][j];
                prefix_arr[i][j] = sum;
            }
        }
        for(int i = 0 ; i < m; i++){
            int sum = 0;
            for(int j = 0; j < n; j++){
                sum += prefix_arr[i][j];
                prefix_arr[i][j] = sum;
            }
        }

        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < m; j++){
                if(prefix_arr[i][j] + board[i][j] > 0)
                    answer++;
            }
        }

        return answer;
    }


    public static void main(String[] args) {
        int[][] board = {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
        int[][] skill = {{1,0,0,3,4,4}, {1,2,0,2,3,2}, {2,1,0,3,1,2}, {1,0,1,3,3,1}};
        solution(board, skill);
    }
}
