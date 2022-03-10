package baekjoon;

import java.io.*;
import java.util.*;

public class Main_9935 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		String bomb = br.readLine();
		
		Stack<Pair> stack = new Stack<>();
		
		for(char c : str.toCharArray()) {
			Pair pair = new Pair(c, 0);
			// 스택에 이미 문자가 있고
			// 스택에 쌓은 문자와 이어지는 폭탄문자열이라면
			if(!stack.isEmpty() && c == bomb.charAt(stack.peek().len)) {
				// 폭발
				if(stack.peek().len == bomb.length()-1) {
					for(int i = 0; i < bomb.length()-1; i++) {
						stack.pop();
					}
					continue;
				}
				// 길이 추가하여 스택에 쌀기
				else {
					pair.len = stack.peek().len+1;
				}
			}
			// 이어지지는 않지만 새로 폭탄문자열의 시작일경우
			else if(c == bomb.charAt(0)) {
				if(bomb.length()==1) {
					continue;
				}
				pair.len = 1;
			}

			stack.add(pair);
			
		}
		
		if(stack.isEmpty()) {
			System.out.println("FRULA");
		}
		else {			
			StringBuilder sb = new StringBuilder();
			for(Pair pair: stack) {
				sb.append(pair.ch);
			}
			System.out.println(sb);
		}
		
	}
	
	static class Pair{
		char ch;
		int len;
		public Pair(char ch, int len) {
			this.ch = ch;
			this.len = len;
		}
		@Override
		public String toString() {
			return "Pair [ch=" + ch + ", len=" + len + "]";
		}
		
		
	}

}