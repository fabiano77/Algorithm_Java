package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2011 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String code = br.readLine();
		int N = code.length();
		
		int[] dp = new int[N+1];
		dp[0] = 1;
		int ans = 0;
		
		for(int end = 1; end <= N; end++) {
			for(int len = 1; len <= 2; len++) {
				if(end-len >= 0) {
					int num = Integer.parseInt(code.substring(end-len, end));
					if(code.charAt(end-len) == '0') {
						continue;
					}
					if(num >= 1 && num <= 26) {
						dp[end] = (dp[end] + dp[end-len]) % 1_000_000;
					}
				}
			}
		}

		System.out.println(dp[N]);

		br.close();
		

	}

}
