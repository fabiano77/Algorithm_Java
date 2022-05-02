package baekjoon;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main_1113 {
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int R, C;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R][C];
		
		for(int r = 0; r < R; r++){
			String line = br.readLine();
			for(int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c) - '0';
			}
		}
		
		int ans = 0;
		for(int level = 1; level <= 9; level++) {
			boolean[][] border = borderScan(map, level);
			
			ans += poolScan(map, border, level);
			
			borderUp(map, border);
		}

		System.out.println(ans);

		br.close();
	}
	
	static class Point{
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static int poolScan(int[][] map, boolean[][] border, int level) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if((0 < r && r < R-1) && (0 < c && c < C-1)) continue;
				if(border[r][c]) {
					q.offer(new Point(r, c));
					visited[r][c] = true;
				}
			}
		}
		
		int retVal = 0;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			for(int[] delta : deltas) {
				int nr = cur.r + delta[0];
				int nc = cur.c + delta[1];
				if(0 <= nr && nr < R && 0 <= nc && nc < C && !visited[nr][nc] && map[nr][nc] <= level) {
					q.offer(new Point(nr, nc));
					visited[nr][nc] = true;
					border[nr][nc] = true;
					retVal += level - map[nr][nc];
					map[nr][nc] = level;
					
				}
			}
		}
			
		
		
		return retVal;
	}
	
	public static void borderUp(int[][] map, boolean[][] border) {
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(border[r][c]) map[r][c]++; 
			}
		}
	}
	
	public static boolean[][] borderScan(int[][] map, int level) {
		boolean[][] border = new boolean[R][C];
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if((0 < r && r < R-1) && (0 < c && c < C-1)) continue;
				if(map[r][c] == level) {
					dfs(map, border, level, r, c);
				}
			}
		}
		return border;
	}
	
	public static void dfs(int[][] map, boolean[][] border, int level, int r, int c) {
		border[r][c] = true;
		for(int[] delta : deltas) {
			int nr = r + delta[0];
			int nc = c + delta[1];
			if(0 <= nr && nr < R && 0 <= nc && nc < C && !border[nr][nc] && map[nr][nc] == level) {
				dfs(map, border, level, nr, nc);
			}
		}
	}

}
