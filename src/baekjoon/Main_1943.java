package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1943 {
	
	static class Coin {
		int price;
		int cnt;
		public Coin(int price, int cnt) {
			this.price = price;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//돈을 똑같이 둘로 나누어 가지고 싶어한다.
		
		for(int tc = 0; tc < 3; tc++) {			
			// 동전의 종류 ~100
			int N = Integer.parseInt(br.readLine());
			List<Coin> coins = new ArrayList<>(N+1);
			coins.add(new Coin(0, 0));
			int total = 0;
			for(int i = 0; i < N; i++) {
				//금액과 개수
				st = new StringTokenizer(br.readLine());
				int price = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				total += price * cnt;
				coins.add(new Coin(price, cnt));
			}
			// 반으로 나누는 것이 가능하면 1, 아니면 0
			
			if(total % 2 == 1) {
				System.out.println(0);
				continue;
			}
			int half = (total/2); 
			int[] dp = new int[half+1];
			dp[0] = 1;
			for(int i = 0; i <= N; i++) {
				for(int c = half; c >= coins.get(i).price; c--) {
					// i번 동전이 남아있고
					// 해당 동전을 몇개 쓸 수 있는지
					if(dp[c - coins.get(i).price] == 1) {
						for(int k = 0; k < coins.get(i).cnt && c + k * coins.get(i).price <= half; k++) {
							dp[c + k * coins.get(i).price] = 1;
						}
					}
				}
			}
			System.out.println(dp[half]);
			
			
			
		}
		
		
		
		
	}

}