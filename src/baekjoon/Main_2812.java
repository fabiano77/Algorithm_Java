package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_2812 {
	static int N;
	static int K;

	public static void main(String[] args) throws IOException{
		System.setIn(Main_2812.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		
		int[] numbers = br.readLine().chars().map((c)->c-'0').toArray();
		
		// 스택을 만든다
		Stack<Integer> stack = new Stack<>();
		
		//지운 횟수를 세는 cnt 변수 생성
		int cnt = 0;
 
		// 두 번째 숫자부터 끝 숫자까지
		for(int i = 0; i < N; i++) {
			// 지울 횟수가 남았고 && 스택에 원소가있고 && 스택의 top숫자가 다음에 올 숫자보다 작으면
			while(cnt < K && !stack.isEmpty()  && stack.peek() < numbers[i]) {
				// 스택에서 하나를 버린다.
				stack.pop();
				cnt++;
			}
			stack.push(numbers[i]);			
		}
		
		// 지울 횟수가 남았다면 스택에서 pop
		while(cnt < K) {
			stack.pop();
			cnt++;
		}
		
		// 스택에 있는 숫자를 먼저 들어온 순서대로 출력
		stack.forEach(System.out::print);
		
		br.close();
	}

}
