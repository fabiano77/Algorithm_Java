package baekjoon;

import java.io.*;
import java.util.Stack;

public class Main_10799 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		int ans = 0;
		Stack<Character> stack = new Stack<>();
		
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i)=='(') {
				stack.push('(');
			}
			else {
				stack.pop();
				if(str.charAt(i-1)=='(') {
					ans += stack.size();
				}
				else {
					ans += 1;
				}
			}
		}
		System.out.println(ans);
		br.close();
	}

}
