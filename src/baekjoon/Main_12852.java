package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Main_12852 {
	static int N;
	static int[] dp;
	static int[] dp2;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dp = new int[N+1];
		dp2 = new int[N+1];
//		for(int i = 1; i <= N; i++) {
//			dfs(i);
//		}
		dp();
		trace(N);
		System.out.println(dp[N]);
		System.out.println(sb);
	}
	
	private static void dp() {
		
		for(int i = 2; i <= N; i++) {
			dp[i] = dp[i-1] + 1;
			dp2[i] = 1;
			if(i%2 == 0 && dp[i/2] + 1 < dp[i]) {
				dp[i] = dp[i/2] + 1;			
				dp2[i] = 2;
			}
			if(i%3 == 0 && dp[i/3] + 1 < dp[i]) {
				dp[i] = dp[i/3] + 1;			
				dp2[i] = 3;
			}
		}

	}
	private static void trace(int n) {
		sb.append(n+" ");
		if (n == 1) {
			return;
		}
		if(dp2[n] == 1) {
			trace(n-1);
		} else if (dp2[n] == 2) {
			trace(n/2);
		} else {
			trace(n/3);
		}
		return;
		
	}

}
