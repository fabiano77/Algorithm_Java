package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2240 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());	// 떨어지는 시간 ~ 1,000
		int W = Integer.parseInt(st.nextToken());	// 움직이고 싶은 최대 횟수 ~ 30
		
		// 2개의 나무 중 하나의 나무에서 열매가 떨어지게 된다.
		// 떨어지는 순간 자두가 나무 아래 있다면 열매를 받아먹을 수 있다.
		
		int[] arr = new int[T];
		for(int i = 0; i < T; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		// 자두 시작위치 1
		
		int[][] dp = new int[W+1][T];
		int ans = 0;
		
		for(int w = 0; w <= W; w++) {
			for(int t = 0; t < T; t++) {
				if(t - 1 >= 0) {
					if(w - 1 >= 0) {
						dp[w][t] = Math.max(dp[w-1][t-1], dp[w][t-1]);
					}
					else {
						dp[w][t] = dp[w][t-1];						
					}
				}
				// 움직인 횟수가 짝수 -> 1에 있다
				if(w % 2 == 0) {
					if(arr[t] == 1) dp[w][t]++;
				}
				// 움직인 횟수가 홀수 -> 2에 있다.
				else {
					if(arr[t] == 2) dp[w][t]++;
				}
			}
			ans = Math.max(ans, dp[w][T-1]);
		}
		
		System.out.println(ans);
		
		

	}

}
