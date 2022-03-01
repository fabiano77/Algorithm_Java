package baekjoon;

import java.io.*;
import java.util.*;

public class Main_7579 {
	static int N, M;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 앱이 사용중인 메모리
		int[] mem = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			mem[i] = Integer.parseInt(st.nextToken());
		}
		
		// 비활성화 비용
		int[] cost = new int[N];
		int costSum = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			costSum += cost[i];
		}
		
		int[] dp = new int[costSum+1];
		for(int i = 0; i < N; i++) {
			for(int j = costSum; j >= cost[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - cost[i]] + mem[i]); 
			}
		}

		int minCost = 0;
		for(minCost = 0; minCost < costSum && dp[minCost] < M; minCost++);
		
		System.out.println(minCost);
		

	}

}
