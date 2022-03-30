package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1600 {
	static int[][] horseMove = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
	static int[][] normalMove = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int ans;
	static int W, H, K;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

K = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[H][W];
		for(int r = 0; r < H; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < W; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		
		int ans = bfs(map);
		if(ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {			
			System.out.println(bfs(map));
		}
	}
	
	static class Point{
		int r, c;
		int k;
		public Point(int r, int c, int k) {
			this.r = r;
			this.c = c;
			this.k = k;
		}
		
		public boolean isIn() {
			return 0 <= r && r < H && 0 <= c && c < W;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", k=" + k + "]";
		}
		
	}
	
	public static int bfs(int[][] map) {
		boolean[][][] visited = new boolean[H][W][K+1];

		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0, K));
		visited[0][0][K] = true;
		int cnt = 0;
		while(!q.isEmpty()) {
			int qSize = q.size();
			for(int i = 0; i < qSize; i++) {
				Point cur = q.poll();
				if(cur.r == H-1 && cur.c == W-1) return cnt;
				Point next;
				if(cur.k > 0) {
					for(int[] move : horseMove) {
						next = new Point(cur.r+move[0], cur.c+move[1], cur.k-1);
						if(!next.isIn() || map[next.r][next.c] == 1 ) continue;
						if(visited[next.r][next.c][next.k]) continue;
						visited[next.r][next.c][next.k] = true;
						q.offer(next);
					}
				}
				for(int[] move : normalMove) {
					next = new Point(cur.r+move[0], cur.c+move[1], cur.k);
					if(!next.isIn() || map[next.r][next.c] == 1 ) continue;
					if(visited[next.r][next.c][next.k]) continue;
					visited[next.r][next.c][next.k] = true;
					q.offer(next);
				}

			}
			cnt++;
		}

		
		return -1;
	}

}