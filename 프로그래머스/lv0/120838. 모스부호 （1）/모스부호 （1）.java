class Solution {
    public String solution(String letter) {
        // 1. 주어지는 문자열 letter를 모두 소문자로 만든다음 공백 단위로 문자열 배열 strArr에 넣는다.
        // 2. strArr 배열의 원소 하나를 morse 문자열 배열 원소 하나씩과 같은지 비교.
        // 3. 같으면 morse 배열의 원소 가져와서 문자열 변수 answer 에 붙이기
        // 4. 문자열 letter의 길이가 끝날때까지 반복확인하기
        String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        String[] strArr = letter.toLowerCase().split(" ");
        String answer = "";
        
        for(int i = 0; i < strArr.length; i++){
            for(int j = 0; j < morse.length; j++){
                if(strArr[i].equals(morse[j])){
                    answer += String.valueOf((char)(97+j));
                }
            }
        }
        // 소문자 a = 97, 소문자 z = 122
        // 숫자 문자로 형변환 char ch = (char)num;
        // String.valueOf(char) : 문자를 문자열로 변환
        
        
        return answer;
    }
}