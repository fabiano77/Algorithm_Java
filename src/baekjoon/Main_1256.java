package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1256 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int M;
	static int K;
	static int cnt;
	static int[] answer;
	static int[][] dp;
	static int cur;
	static boolean isSolved;

	public static void main(String[] args) throws IOException{
		//System.setIn(Main_1256.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		answer = new int[N+M];
		dp = new int[N+M+1][N+M+1];
		
		int combiValue = dpCombi(N+M, M);
		if(combiValue < K) {
			System.out.println(-1);
		}
		else {
			solve(K);
		}
		

	}
	
	// n개중 k를 순서없이 뽑는 경우의수
	private static int dpCombi(int n, int k) {
		for(int i = 1; i <= k; i++) {
			dp[i][i] = 1;
		}
		for(int i = 1; i <= n; i++) {
			dp[i][0] = 1;
		}
		
		for(int i = 2; i <= n; i++) {
			for(int j = 1; j < i; j++) {
				dp[i][j] = dp[i-1][j-1]+dp[i-1][j]; 
			}
		}
		
		return dp[n][k];
	}
	
	private static void solve(int index) {
		int n = N+M;
		int k = M;
		while(true) {
			// z를 선택
			System.out.println("n = "+n+", k = "+k+", index = "+index);
			if(index > dp[n-1][k-1]) {
				index -= dp[n-1][k-1];
				n--;
				sb.append('z');
			}
			// a를 선택
			else {
				n--;
				k--;
				sb.append('a');
			}
			if(dp[n][k] == 1) {
				if(n == k) {
					for(int i = 0; i < n; i++) {
						sb.append('z');
					}
				}
				else {
					sb.append('a');
				}
				break;
			}
			
		}
		System.out.println(sb);
	}
}