package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9177 {
	static int T;
	static boolean[][] visited;
	static boolean yes;
	static String str1, str2, target;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		System.setIn(Main_9177.class.getResourceAsStream("input3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			sb.append("Data set ").append(t).append(": ");
			
			// 문자열1, 문자열2 입력 처리
			StringTokenizer st = new StringTokenizer(br.readLine());
			str1 = st.nextToken();
			str2 = st.nextToken();
			target = st.nextToken();
			
			// 재귀 호출의 불필요한 반복을 막기 위한 방문처리 2차원 배열
			visited = new boolean[str1.length()+1][str2.length()+1];
			
			// true 반환할 시 yes, 아니면 no 출력
			if(solve(0, 0, 0)) sb.append("yes\n");				
			else 	sb.append("no\n");
		}
		
		// 결과 출력하고 종료
		System.out.println(sb);
		br.close();
	}
	
	private static boolean solve(int ptr1, int ptr2, int ptrTarget) {
		// 재귀에서 불필요한 반복을 차단함
		if(visited[ptr1][ptr2]) {
			return false;
		}
		
		while(ptrTarget < target.length()) {
			// 현재 탐색점 방문처리
			visited[ptr1][ptr2] = true;
			
			// 현재 포인터1과 포인터2가 가리키는 문자 둘다 가능한경우
			if(ptr1 < str1.length() && ptr2 < str2.length()								// 두 포인터가 문자열 내에 있고 &&  
									&& str1.charAt(ptr1) == str2.charAt(ptr2) 			// 포인터가 가리키는 두 문자가 같으며
									&& target.charAt(ptrTarget) == str1.charAt(ptr1)){	// 타겟 문자와도 같다
				
				// 두가지 경우를 재귀적 호출하며 어떤거든 true이면 true를 반환한다.
				return solve(ptr1 + 1, ptr2, ptrTarget + 1) || solve(ptr1, ptr2 + 1, ptrTarget + 1);
			}
			// 포인터1가 가리키는 문자가 타겟문자를 만들 수 있는경우 
			else if(ptr1 < str1.length() && target.charAt(ptrTarget) == str1.charAt(ptr1)) {
				ptr1++;
				ptrTarget++;
			}
			// 포인터2가 가리키는 문자가 타겟문자를 만들 수 있는경우
			else if (ptr2 < str2.length() && target.charAt(ptrTarget) == str2.charAt(ptr2)) {
				ptr2++;
				ptrTarget++;
			}
			// 타겟 문자를 만들지 못할경우 false 반환.
			else {
				return false;
			}
		}
		
		//while문이 정상적으로 끝나는 경우 -> ptrTarget == target.length()	//
		return true;
		
	}

}
