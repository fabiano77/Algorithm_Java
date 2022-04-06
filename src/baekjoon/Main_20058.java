package baekjoon;

import java.io.*;
import java.util.*;

public class Main_20058 {
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int len;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		len = (int)Math.pow(2, N);
		int[][] map = new int[len][len];
		for(int r = 0; r < len; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < len; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		while(Q-- > 0) {
			map = fireStorm(map, Integer.parseInt(st.nextToken()));
			melt(map);
		}

		System.out.println(count(map));
		System.out.println(getCluster(map));
		br.close();
	}

	
	static class Point{
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static int[][] fireStorm(int[][] map, int L) {
		int [][] nextMap = new int[len][len];
		int squareUnit = (int)Math.pow(2, L);
		
		for(int startR = 0; startR < len; startR += squareUnit) {
			
			for(int startC = 0; startC < len; startC += squareUnit) {
				int endC = startC + squareUnit;
				
				for(int r = 0; r < squareUnit; r++) {
					for(int c = 0; c < squareUnit; c++) {
						int nr = startR + c;
						int nc = endC - r - 1;

						nextMap[nr][nc] = map[startR+r][startC+c];

					}
				}
				
			}
		}
		
		return nextMap;
	}
	
	public static void melt(int[][] map) {
		List<Point> list = new ArrayList<>();
		for(int r = 0; r < len; r++) {
			for(int c = 0; c < len; c++) {
				// 얼음이 아닌경우 skip
				if(map[r][c] == 0) continue;
				
				// 인접한 얼음의 개수
				int iceCnt = 0;
				for(int[] delta : deltas) {
					int nr = r + delta[0];
					int nc = c + delta[1];
					if(isIn(nr, nc) && map[nr][nc] != 0) {
						iceCnt++;
					}
				}
				// 인접한 얼음의 개수가 3개 미만이면
				if(iceCnt < 3) {
					list.add(new Point(r, c));
				}
			}
		}
		
		// 얼음 양 1씩 감소
		list.forEach((p)->map[p.r][p.c]--);
	}
	
	public static int count(int[][] map) {
		int cnt = 0;
		for(int r = 0; r < len; r++) {
			for(int c = 0; c < len; c++) {
				cnt += map[r][c];
			}
		}
		return cnt;
	}
	
	public static int getCluster(int[][] map) {
		boolean[][] visited = new boolean[len][len];
		
		int max = 0;
		for(int r = 0; r < len; r++) {
			for(int c = 0; c < len; c++) {
				if(visited[r][c] || map[r][c] == 0) continue;
				max = Math.max(max, dfs(map, visited, r, c));
			}
		}
		
		return max;
	}
	
	public static boolean isIn(int r, int c) {
		return 0 <= r && r < len && 0 <= c && c < len;
	}
	
	public static int dfs(int[][] map, boolean[][] visited, int r, int c) {
		visited[r][c] = true;
		int cnt = 1;
		for(int[] delta : deltas) {
			int nr = r + delta[0];
			int nc = c + delta[1];
			if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] != 0) {
				visited[nr][nc] = true;
				cnt += dfs(map, visited, nr, nc);
			}
		}
		
		return cnt;
	}

}
