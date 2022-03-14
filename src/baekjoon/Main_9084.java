package baekjoon;

import java.io.*;
import java.util.*;

public class Main_9084 {
	static int T;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int coinNum = Integer.parseInt(br.readLine());
			int[] coins = new int[coinNum];
			st = new StringTokenizer(br.readLine());
			// 오름차순 정렬되어 주어짐
			for(int i = 0; i < coinNum; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			
			int money = Integer.parseInt(br.readLine());
			int[] dp = new int[money+1];
			
			dp[0] = 1;
			for(int coin : coins) {
				for(int i = 1; i <= money; i++) {
					// 현재 만들어야하는 금액 i보다 동전이 더 클경우
					if(coin > i) continue;
					
					dp[i] += dp[i - coin];
				}
			}
			
			System.out.println(dp[money]);
			
			
			
			
			
		}

		

	}

}
