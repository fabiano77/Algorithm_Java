package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1937 {
	static int N;
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; 

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N][N];
		
		int ans = 0;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				ans = Math.max(ans, dfs(r, c, map, dp));
			}
		}
		
		System.out.println(ans);

	}

	
	public static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
	
	public static int dfs(int r, int c, int[][] map, int[][] dp) {
		if(dp[r][c] != 0) return dp[r][c];
		
		dp[r][c] = 1;
		for(int[] delta : deltas) {
			int nr = r + delta[0];
			int nc = c + delta[1];
			
			if(isIn(nr, nc) && map[nr][nc] > map[r][c]) {
				dp[r][c] = Math.max(dp[r][c], dfs(nr, nc, map, dp) + 1);
			}
		}

		return dp[r][c];
		
	}

}
