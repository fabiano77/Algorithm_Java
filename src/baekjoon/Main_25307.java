package baekjoon;

import java.io.*;
import java.util.*;

public class Main_25307 {
	
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N;
	static int M;
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken()); // 마네킹과의 거리
		
		int[][] map = new int[N][M];
		Point start = null;
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 4) {
					start = new Point(r, c);
				}
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] == 3) {
					diffusion(map, r, c, K);
				}
			}
		}
		
		
		System.out.println(bfs(map, start));
		

		br.close();
	}
	
	
	public static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
	public static int bfs(int[][] map, Point start) {
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		boolean[][] visited = new boolean[N][M];
		visited[start.r][start.c] = true;
		
		int dist = 0;
		while(!q.isEmpty()) {
			int qSize = q.size();
			while(qSize-- > 0) {				
				Point cur = q.poll();
				if(map[cur.r][cur.c] == 2) {
					return dist;
				}
				for(int[] delta : deltas) {
					int nr = cur.r + delta[0];
					int nc = cur.c + delta[1];
					if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] != 1) {
						q.offer(new Point(nr, nc));
						visited[nr][nc] = true;
					}
				}
			}
			dist++;
		}
		return -1;
	}
	
	public static void diffusion(int[][] map, int r, int c, int K) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(r, c));
		boolean[][] visited = new boolean[N][M];
		visited[r][c] = true;
		int dist = 0;
		while(!q.isEmpty() && dist <= K) {
			int qSize = q.size();
			while(qSize-- > 0) {				
				Point cur = q.poll();
				if(map[cur.r][cur.c] < 3) {
					map[cur.r][cur.c] = 1;
				}
				for(int[] delta : deltas) {
					int nr = cur.r + delta[0];
					int nc = cur.c + delta[1];
					if(isIn(nr, nc) && !visited[nr][nc]) {
						q.offer(new Point(nr, nc));
						visited[nr][nc] = true;
					}
				}
			}
			dist++;
		}
		
	}

}
