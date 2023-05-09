import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class algo201 {


    static HashMap<String, Node> map = new HashMap<>();
    static class Node{
        int idx;
        int findWord;
        ArrayList<String> array;
        Node(int idx, int findWord, ArrayList<String> array){
            this.idx = idx;
            this.findWord = findWord;
            this.array = array;
        }
    }

    public static int solution(String word, String[] pages) {
        int answer = 0;

        // page를 나누자
        for(int T = 0 ; T < pages.length; T++){
            String tempStr = pages[T];
            StringTokenizer st = new StringTokenizer(tempStr, " ");
            String content = "";
            String href = "";
            while(st.hasMoreTokens()){
                String str = st.nextToken();
                // 먼저 자기 URl이나오면
                if(str.contains("content")){
                    // content="http://a.com"|>
                    // "로 구분해보자.
                    StringTokenizer st1 = new StringTokenizer(str, "\"");
                    st1.nextToken();
                    content = st1.nextToken();
                    map.put(content, new Node(T, 0, new ArrayList<>()));

                }else if(str.contains("href")){
                    StringTokenizer st1 = new StringTokenizer(str, "\"");
                    st1.nextToken();
                    href = st1.nextToken();

                    //외부로 연결된 링크가 나올시 해당 content의 array에 외부링크넣자.
                    map.get(content).array.add(href);
                }else{
                    // 대소문자 구분하지 않고 찾자.
                    // word랑 str둘다 소문자로 바꿔서 비교해보자
                    int count = 0;
                    String lowCaseStr = str.toLowerCase();
                    String wordLowCase = word.toLowerCase();
                    if(lowCaseStr.contains(wordLowCase)){
                        // 대소문자 구분하지않고 일치하는 단어가 포함된다면..
                        // 단어가 몇개인지 카운트하자.

                        // 2. 단어는 알파벳을 제외한 다른 모든 문자로 구분한다.
                        // 일단 str에 word가 포함되어있으면 조사시작.

                        int startIdx = 0;
                        int check = word.length();
                        while( (startIdx = lowCaseStr.indexOf(wordLowCase)) != -1){
                            if(startIdx == 0){
                                // 만약 첫번째 idx가 0 이면
                                // check 인덱스번째가 알파벳이아니면 count++;

                                // 만약 lowCaseStr 길이랑 == Word길이가 같으면 count ++ 하고 break
                                if(lowCaseStr.length() == check){
                                    count++;
                                    break;
                                }
                                if( 0 <= (lowCaseStr.charAt(check) - 'a') && (lowCaseStr.charAt(check) - 'a') <= 26){
                                    continue;
                                }else
                                    count++;

                                // 발견한 word만큼 삭제해버리자.
                                lowCaseStr = lowCaseStr.substring(check);
                            }else{
                                // 만약 첫번째 idx가 0이아니면
                                // 뒤에 idx, 마지막 idx +1이 문자인지 확인해야한다.

                                if(0 <= (lowCaseStr.charAt(startIdx - 1) - 'a') && (lowCaseStr.charAt(startIdx - 1) - 'a') <= 26
                                        && 0 <= (lowCaseStr.charAt(startIdx + check) - 'a') && (lowCaseStr.charAt(startIdx + check) - 'a') <= 26){
                                    continue;
                                }else
                                    count++;
                                lowCaseStr = lowCaseStr.substring(startIdx + check);

                            }
                        }

                    }
                    if(map.containsKey(content))
                        map.get(content).findWord += count;
                }
            }
        }
        for(String key : map.keySet()){
            System.out.println(map.get(key).idx + " " + map.get(key).findWord + " " + map.get(key).array.size() );
        }

        return answer;
    }
    public static void main(String[] args) throws IOException {
        String word = "blind";
        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"};
        solution(word, pages);
    }
}
