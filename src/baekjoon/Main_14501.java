package baekjoon;

import java.io.*;
import java.util.*;

public class Main_14501 {
	
	static class Pair {
		int t, p;

		public Pair(int t, int p) {
			this.t = t;
			this.p = p;
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Pair[] arr = new Pair[N+1];
		int[] dp = new int[N+6];
		
		for(int i = 0; i < N; i++) {			
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			arr[i] = new Pair(t, p);
		}
		arr[N] = new Pair(0, 0);
		
		int max = 0;
		for(int i = 0; i <= N; i++) {
			dp[i] = Math.max(dp[i], max);
			// i번째 일이 끝나는 날의 최대 수익
			// 이 일을 하거나 안하거나
			dp[arr[i].t + i] = Math.max(dp[arr[i].t + i], arr[i].p + dp[i]);
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
		
		
		

	}

}
