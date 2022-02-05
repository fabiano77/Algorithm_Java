package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9252 {
	static StringBuilder sb = new StringBuilder();
	static int[][] dp;
	static String str1;
	static String str2;

	public static void main(String[] args) throws IOException{
		System.setIn(Main_9252.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str1 = br.readLine();
		str2 = br.readLine();
		
		dp();
		trace();

		// 최장 공통 수열 출력
		System.out.println(dp[str1.length()][str2.length()]);
		// 경로 출력
		System.out.println(sb.reverse());
		
	}
	
	private static void dp() {
		dp = new int[str1.length()+1][str2.length()+1];
		
		for(int i = 1; i <= str1.length(); i++) {
			for(int j = 1; j <= str2.length(); j++) {
				
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				// 현재 같은 문자일 경우, 좌측 대각선 위보다 1 증가시킨다.
				if(str1.charAt(i-1)==str2.charAt(j-1)) {
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]+1);					
				}
				
			}
		}
	}
	
	private static void trace() {
		int r = str1.length();
		int c = str2.length();
		while(dp[r][c] >= 1) {
			if(dp[r-1][c-1] == dp[r][c]) {
				r--; c--;
			} else if(dp[r-1][c] == dp[r][c]) {
				r--;
			} else if(dp[r][c-1] == dp[r][c]) {
				c--;
			} else {
				// 현재 왼쪽, 위쪽, 좌상 대각선중 가장 클 경우, 자신이 공통수열이며
				// 좌상 대각선으로 이동
				sb.append(str1.charAt(r-1));
				r--; c--;
			}
		}
	}

}
