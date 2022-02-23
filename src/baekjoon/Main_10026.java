package baekjoon;

import java.io.*;
import java.util.*;

public class Main_10026 {
	static int N;
	static final int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static char[][] map;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		for(int i = 0; i < N; i++) {
			String row = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = row.charAt(j);
			}
		}
		boolean[][] visited1 = new boolean[N][N];
		boolean[][] visited2 = new boolean[N][N];
		int ans1 = 0;
		int ans2 = 0;

		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(dfs(r, c, visited1, 1, map[r][c])) ans1++;
				if(dfs(r, c, visited2, 2, map[r][c])) ans2++;
			}
		}
		System.out.print(ans1 + " " +ans2);
		

	}
	
	public static boolean dfs(int r, int c, boolean[][] visited, int mode, char color) {
		if(visited[r][c]) return false;
		visited[r][c] = true;
		
		for(int[] delta : deltas) {
			int nr = r + delta[0];
			int nc = c + delta[1];
			if(isIn(nr, nc)) {
				// 정상
				if(map[nr][nc] == color) {
					dfs(nr, nc, visited, mode, color);										
				}
				// 색맹일 경우
				else if(mode == 2) {
					if((map[nr][nc] == 'R' || map[nr][nc] == 'G')&&(color == 'R' || color == 'G')) {
						dfs(nr, nc, visited, mode, color);	
					}
				}
			}
		}
		
		return true;
	}

	public static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}

