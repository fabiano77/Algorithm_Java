package baekjoon;

import java.io.*;
import java.util.*;

public class Main_15988 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		long dp[] = new long[1000001];
		
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		for(int i = 4; i < 1000001; i++) {
			dp[i] = (dp[i-3] + dp[i-2] + dp[i-1]) % 1000000009;
		}
		
		int tc = Integer.parseInt(br.readLine());
		
		while(tc-- > 0) {
			int num = Integer.parseInt(br.readLine());
			System.out.println(dp[num]);
		}
		

		br.close();
	}

}
