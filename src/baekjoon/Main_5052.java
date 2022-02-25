package baekjoon;

import java.io.*;
import java.util.*;

public class Main_5052 {
	
	// 자식을 10개 가지는 트리
	static class Tree{
		Tree[] child = new Tree[10];
	}
	
	static int T;
	static int N;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {			
			N = Integer.parseInt(br.readLine());
			String[] phoneNum = new String[N]; 
			for(int i = 0; i < N; i++) {
				phoneNum[i] = br.readLine().trim();
			}
			
			// 문자열 길이 긴 순으로 정렬
			Arrays.sort(phoneNum, (s1, s2)->{return s2.length() - s1.length();});
			
			// 기본 트리 생성
			Tree tree = new Tree();
			boolean YES = true;
			
			// 길이 긴 전화번호부터 반복
			for(String n : phoneNum) {
				// 전화번호이 Tree를 span하지 못한다면, 중복됐다는 것
				if(!span(tree, n, 0)) {
					YES = false;
					break;
				}
			}
			
			if(YES) System.out.println("YES");
			else System.out.println("NO");
		}

	}
	
	// 전화번호의 앞 숫자에 따라 
	public static boolean span(Tree tree, String phoneNum, int idx) {
		// 전부 span했다면 반환
		if(phoneNum.length() == idx) return false;
		
		// 전화번호에서 현재 index에 해당하는 숫자
		int curNum = phoneNum.charAt(idx)-'0';
		// 중복되지 않았음을 나타내는 논리값
		boolean isSpan = false;
		// 해당 child가 없어 스팬해야 한다면 
		if(tree.child[curNum] == null) {
			// 스팬하고, 스팬 했다는 것을 기록함
			tree.child[curNum] = new Tree();
			isSpan = true;
			// 나중 문자를 위해 끝까지 스팬
			span(tree.child[curNum], phoneNum, idx+1);
		}
		else {
			// 중복되었다면, 다음 자식에서 스팬하는지 기록함.
			isSpan |= span(tree.child[curNum], phoneNum, idx+1);
		}
		return isSpan;
	}
}
