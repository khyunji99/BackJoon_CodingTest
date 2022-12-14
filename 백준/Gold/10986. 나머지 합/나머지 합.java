import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        // N개의 수 A1, A2, A3, ..., An이 주어졌을 때 연속된 부분의 합이 M으로 나누어떨어지는 구간의 개수를 구하는 프로그램을 작성하라.
        // 즉, Ai + ... + Aj (i<=j)의 합이 M으로 나누어떨어지는 (i,j) 쌍의 개수를 구하라.

        // [ 핵심 아이디어 ]
        // (A+B)%C = ((A%C)+(B%C))%C
        // 특정 구간 수들의 나머지 연산을 더해 나머지 연산을 한 값과 이 구간 합의 나머지 연산을 한 값은 동일하다.
        // S[j] - S[i] = 원본 배열의 j+1부터 i 까지의 구간의 합
        // S[i] % M 값과 S[j] % M 값이 같다면, (S[i] - S[j]) % M = 0 이다.
        // 즉, 구간 합 배열의 원소를 M으로 나눈 나머지로 업데이트하고 S[i]와 S[j]가 같은 (i,j)쌍을 찾으면
        // 원본 배열에서 j+1부터 i 까지의 구간 합이 M으로 나누어떨어진다는 것을 알 수 있다.

        // [ 구간의 합 알고리즘 사용하기 ]
        // N : 수열의 개수 ( 입력받기 )
        // M : 나누어떨어져야 하는 수 ( 입력받기 )
        // S : 합배열
        // C : 같은 나머지 값의 인덱스 카운트하는 배열
        // (즉, 업데이트한 합배열 S` 의 2,3번 인덱스가 나머지값 1로 같다. 이러면 C의 배열의 1번 인덱스(같은 나머지값을 C 배열의 인덱스로)를 +2 해주는 것이다.)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] S = new long[N];
        long[] C = new long[M]; // C 배열은 M의 나머지 값을 인덱스로 가지는 배열이기 때문에 M이 가지는 나머지의 개수는 0 ~ M-1 까지로 M 개이다.
        long answer = 0; // 우리가 원하는 경우의 수 값


        // 1. 합배열 S 만들기
        st = new StringTokenizer(br.readLine());
        S[0] = Integer.parseInt(st.nextToken()); // 맨 처음에 입력받는 원소
        for (int i = 1; i < N; i++) {
            S[i] = S[i-1] + Integer.parseInt(st.nextToken()); // 두번째부터 입력 받는 값으로 합배열을 만든다.
        }

        // 2. 합배열 S 를 M 으로 나누고 난 나머지의 값에 해당하는 C 배열의 인덱스를 1씩 증가한다.
        //   ex) S = [1, 3, 5, 7, 9] -- %M -->  S` = [1, 0, 2, 1, 0] --> C = [2, 2, 1]
        //       M으로 나눈 나머지의 값이 총 0값 2개, 1값 2개, 2값 1개로 이루어진다.
        //       그러면 C 배열의 0인덱스를 +2, 1인덱스를 +2, 2인덱스를 +1 해주는 것이다.

        for (int i = 0; i < N; i++) {
            int r = (int)(S[i] % M); // r : 합배열의 원소를 M으로 나머지 연산한 나머지 값

            if (r == 0)
                answer++; // 이미 나머지가 0으로 나누어떨어지므로 얘네들 먼저 우리가 원하는 경우의 수 answer 에 ++ 해주자.

            C[r]++; // 나머지값에 해당하는 C 배열의 인덱스 배열에 1 증가시켜준다.
                    // --> 이렇게 하다보면 나머지 0인게 몇개인지, 나머지 1인게 몇개인지 ,... 나머지 M-1인게 몇개인지 알 수 있다.
                    // 이렇게 해서 나머지값이 같은 것들에서 2개를 뽑는 경우의 수까지 answer 에 더해주면 우리가 원허는 최종 경우의 수를 구할 수 있다.
        }

        // 3. C 배열의 값 중 2 이상인 애들의 인덱스값을 가지로 2개를 뽑는 경우의 수를 정답 answer 에 더해준다.
        //    ex) M = 3, C = [4, 2, 1]
        //    0번 인덱스의 값이 4 라는 말은 합배열의 원소를 M=3 으로 나머지연산 했을 때 나머지 값이 0인 애들이 4개가 된다는 말.
        //    그래서 나머지 값이 같은 4개 중에서 2개를 뽑는 경우의 수 4C2 의 값인 6을 answer 에 더해준다.

        for (int i = 0; i < M; i++) {
            if(C[i] > 1)
                answer += C[i] * (C[i] - 1) / 2; // 조합의 공식을 간단하게 나타내면 n(n-1)/2 가 된다,,,
        }

        System.out.print(answer);


    }
}
