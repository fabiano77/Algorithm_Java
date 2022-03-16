package baekjoon;

import java.io.*;
import java.util.*;

public class Main_3687 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//			 0  1  2  3  4  5  6  7  8  9
		int[] arr = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
		
		// 2랑 7만쓰면 큰 수 만들수 있음
		
		// 작은 수
		// 성냥개비 idx개를 사용하여 만들 수 있는 작은 수
		long[] dp = new long[101];
		
		// 사용하는 성냥개비의 수
		for(int i = 0; i <= 100; i++) {				
			for(int num = 0; num <= 9; num++) {
				// i가 0이아니고 dp[i]가 0이라면 수가 만들어지지 않은 경우
				if(i != 0 && dp[i] == 0) continue;
				// 사용할 수 있는 성냥개수를 초과할 경우.
				if(i+arr[num] > 100) continue;
				long currentNumber = dp[i] * 10 + num;
				if(dp[i+arr[num]] != 0) {
					dp[i+arr[num]] = Math.min(dp[i+arr[num]], currentNumber); 
				}
				else {
					dp[i+arr[num]] = currentNumber;
				}
			}
		}
		
		int T = Integer.parseInt(br.readLine());		// 테케 수 ~ 100
		
		// 테케 수 만큼 반복
		while(T-- > 0) {
			
			// 성냥개비 수
			int N = Integer.parseInt(br.readLine());
			
			// 작은 수
			sb.append(dp[N]).append(" ");
			
			// 큰 수
			sb.append( (N % 2 == 1) ? 7 : 1);
			for(int i = 1; i < N / 2; i++) {
				sb.append(1);
			}
			sb.append("\n");
		}
		
		// 결과 출력
		System.out.println(sb);

	}

}
