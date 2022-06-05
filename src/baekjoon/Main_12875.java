package baekjoon;

import java.io.*;
import java.util.*;

public class Main_12875 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken()); // 무게 한계
		
		int[][] arr = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken()); // 무게
			int v = Integer.parseInt(st.nextToken()); // 가치
			arr[i][0] = w;
			arr[i][1] = v;
		}
		
		int[][] dp = new int[N+1][K+1];
		for(int n = 1; n <= N; n++) {
			for(int w = 1; w <= K; w++) {
				// 담을수 있다면
				if(arr[n-1][0] <= w) {
					dp[n][w] = Math.max(arr[n-1][1] + dp[n-1][w-arr[n-1][0]], dp[n-1][w]);					
				}
				else {
					dp[n][w] = dp[n-1][w];
				}
			}
		}
		
		
		
		System.out.println(dp[N][K]);

		br.close();
		

	}

}
