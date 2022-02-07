package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main_1256 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int M;
	static int K;
	static int cnt;
	static int[] answer;
	static BigInteger[][] dp;
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
		dp = new BigInteger[N+M+1][N+M+1];
		
		BigInteger combiValue = dpCombi(N+M, M);
		
		// 사전에 수록되어있는 문자열의 개수가 K보다 작으면 -1 출력
		if(combiValue.compareTo(new BigInteger(""+K)) < 0) {
			System.out.println(-1);
		}
		else {
			solve(new BigInteger(""+K));
		}
		

	}
	
	// n개중 k를 순서없이 뽑는 조합을 DP로 해결
	private static BigInteger dpCombi(int n, int k) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n;j++) {
				dp[i][j] = new BigInteger("0");
			}
		}
		for(int i = 1; i <= k; i++) {
			dp[i][i] = new BigInteger("1");
		}
		for(int i = 1; i <= n; i++) {
			dp[i][0] = new BigInteger("1");
		}
		
		for(int i = 2; i <= n; i++) {
			for(int j = 1; j < i && j <= k; j++) {
				dp[i][j] = dp[i-1][j-1].add(dp[i-1][j]); 
			}
		}
		
		return dp[n][k];
	}
	
	// index를 통해 경로 역추적
	private static void solve(BigInteger index) {
		int n = N+M;
		int k = M;
		while(n != 0 || k != 0) {
			// System.out.println("n = "+n +", k = "+k +", index = "+index);
			
			// k == 0 이거나 index <= dp[n-1][k] 이면 n-1 C k를 선택하고 a를 추가
			if(k == 0 || index.compareTo(dp[n-1][k]) <= 0) {
				sb.append('a');
				n--;
			}
			// index > dp[n-1][k] 이면 n-1 C k-1를 선택하고 z를 추가
			else {
				index = index.subtract(dp[n-1][k]);
				sb.append('z');
				n--;
				k--;
			}
			
		}
		System.out.println(sb);
	}
	
}