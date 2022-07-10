package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2169 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());	//~1,000
		int C = Integer.parseInt(st.nextToken());	//~1,000
		
		int[][] map = new int[R][C];
		
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[R][C];
		dp[0][0] = map[0][0]; 
		
		for(int c = 1; c < C; c++) {
			dp[0][c] = map[0][c] + dp[0][c-1];
		}
		
		for(int r = 1; r < R; r++) {
			int[] fromLeft = new int[C];
			for(int c = 0; c < C; c++) {
				if(c == 0) {
					fromLeft[c] = dp[r-1][c] + map[r][c];
				}
				else {
					fromLeft[c] = Math.max(dp[r-1][c], fromLeft[c-1]) + map[r][c];
				}
			}
			int[] fromRight = new int[C];
			for(int c = C-1; c >= 0; c--) {
				if(c == C-1) {
					fromRight[c] = dp[r-1][c] + map[r][c];
				}
				else {
					fromRight[c] = Math.max(dp[r-1][c], fromRight[c+1]) + map[r][c];
				}
			}

			for(int c = 0; c < C; c++) {
				dp[r][c] = Math.max(fromLeft[c], fromRight[c]);
			}

		}
		System.out.println(dp[R-1][C-1]);
		

		br.close();
		
	}

}
