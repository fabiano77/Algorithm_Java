package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9251 {

	public static void main(String[] args) throws IOException{
		System.setIn(Main_9251.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String strA = br.readLine();
		String strB = br.readLine();
		int lenA = strA.length();
		int lenB = strB.length();

		int[][] dp = new int[lenA+1][lenB+1];
		
		for(int i = 1; i <= lenA; i++) {
			for(int j = 1; j <= strB.length(); j++) {
				if(strA.charAt(i-1) == strB.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1]+1;
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		

		System.out.println(dp[lenA][lenB]);
	}

}
