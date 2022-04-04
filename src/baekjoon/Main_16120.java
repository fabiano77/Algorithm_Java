package baekjoon;

import java.io.*;
import java.util.*;

public class Main_16120 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
	
		boolean isPPAP = true;
		Stack<Character> stack = new Stack<>();
		
		try {			
			for(int i = 0; i < str.length(); i++) {
				char cur = str.charAt(i);

				// 'A'가 등장할때마다 스택을 이용하여 앞뒤를 살핀다.
				if(cur == 'A') {
					// stack에 존재하지 않는다면 예외를 일으킴
					char second = stack.pop();
					char first = stack.pop();
					// 다음 문자열이 존재하지 않는다면 예외를 일으킴
					char next = str.charAt(++i);
					
					// 현재 cur는 이미 cur == 'A'를 확인했으므로
					if(first == 'P' && second == 'P' && next == 'P') {
						stack.push('P');
					}
					else {
						// PPAP가 아니라면
						isPPAP = false;
						break;
					}
				}
				else {
					// P라면 스택에 넣는다.
					stack.push(cur);
				}
			}
		} catch (EmptyStackException | StringIndexOutOfBoundsException e) {
			// stack 예외, StringIndex 예외가 발생하면, PPAP가 아닌 것
			isPPAP = false;
		}

		// PPAP만 나왔고, stack에 'P' 하나만 남았을 때
		if(isPPAP && stack.size() == 1 && stack.pop() == 'P') {
			System.out.println("PPAP");
		}
		else {
			System.out.println("NP");
		}
		

	}

}
