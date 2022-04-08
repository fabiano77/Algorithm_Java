package baekjoon;

import java.io.*;
import java.util.*;

public class Main_14502 {
	static int safe;
	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
			
		}

		solve(0, 0);
		System.out.println(safe);

	}
	
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public static void print() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				System.out.print(map[r][c]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
	public static void dfs(int[][] map, int r, int c) {
		map[r][c] = 2;
		for(int[] delta : deltas) {
			int nr = r + delta[0];
			int nc = c + delta[1];
			
			if(isIn(nr, nc) && map[nr][nc] == 0) {
				dfs(map, nr, nc);
			}
		}
	}
	
	public static int count(int[][] map) {
		int cnt = 0;
		int[][] copyMap = new int[N][M];
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				copyMap[r][c] = map[r][c];
			}
		}
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(copyMap[r][c] == 2) dfs(copyMap, r, c);
			}
		}
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(copyMap[r][c] == 0) cnt++;
			}
		}
		
		return cnt;
	}
	
	public static void solve(int idx, int cnt) {
		if(cnt == 3) {
			safe = Math.max(safe, count(map));
			return;
		}
		
		if(idx == N * M) return;
		
		int r = idx / M;
		int c = idx % M;
		
		if(map[r][c] == 0) {
			map[r][c] = 1;
			solve(idx+1, cnt+1);
			map[r][c] = 0;
		}
		solve(idx+1, cnt);
		
		
	}

}
