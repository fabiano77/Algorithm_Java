package baekjoon;

import java.io.*;
import java.util.*;

public class Main_17370 {
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //상 우 하 좌
	static int[][] dirs = {{1, 2, 3}, {0, 1, 3}};	// 짝(하, 좌, 우), 홀(상, 좌, 우)
	static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		
		if ( N >= 5 ) {
			boolean[][] visited = new boolean[60][60];
			visited[31][30] = true;
			
			dfs(31, 30, 30, 30, 0, N, visited);
		}
		
		System.out.println(ans);
		
		br.close();
	}
	
	public static void dfs(int pr, int pc, int r, int c, int length, int maxLength, boolean[][] visited) {
		if(visited[r][c]) {
			if(length == maxLength) {
				ans++;
			}
			return;
		}
		if(length == maxLength) return;
		
		
		visited[r][c] = true;			
		for(int d : dirs[(r+c)%2]) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];
			if(nr == pr && nc == pc) continue;
			
			dfs(r, c, nr, nc, length+1, maxLength, visited);
			
		}
		visited[r][c] = false;
		
		
	}
	
}
