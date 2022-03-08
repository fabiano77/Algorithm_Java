package baekjoon;

import java.io.*;
import java.util.*;

public class Main_17404 {
	static int N;
 
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N+1][3];
		int[][] dp = new int[N+1][3];	// 번호, 자신색, 이전색
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		
		int ans = Integer.MAX_VALUE;
		
		// 첫 집의 컬러를 하나씩 고정해서 반복해존다
		for(int k = 0; k < 3; k++) {
			Arrays.fill(dp[1], 100_000_000);
			dp[1][k] = arr[1][k];
			
			// 집 마다 이전 집과 겹치지 않는 색을 칠해본다.
			for(int i = 2; i <= N; i++) {
				dp[i][0] = arr[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
				dp[i][1] = arr[i][1] + Math.min(dp[i-1][2], dp[i-1][0]);
				dp[i][2] = arr[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
			}
			
			for(int j = 0; j < 3; j++) {
				if(k == j) continue;
				ans = Math.min(ans, dp[N][j]);
			}
		}
		System.out.println(ans);

	}

}
