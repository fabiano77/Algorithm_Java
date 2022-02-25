package baekjoon;

import java.io.*;
import java.util.*;

public class Main_7576 {
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N, M;
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int cnt;
	static int max;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs(map);
		
		if(cnt == N*M) System.out.println(max-1);
		else System.out.println(-1);

	}
	
	public static void bfs(int[][] map) {
		Queue<Point> q = new LinkedList<>();
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] == 1) {
					q.offer(new Point(r, c));
				}
				else if(map[r][c] == -1) {
					cnt++;
				}
			}
		}
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			cnt++;
			int day = map[p.x][p.y];
			if(day > max) max = day;
			for(int[] delta : deltas) {
				int nx = p.x + delta[0];
				int ny = p.y + delta[1];
				if(nx >= 0 && nx <= N-1 && ny >= 0 && ny <= M-1) {
					if(map[nx][ny] > day + 1 || map[nx][ny] == 0) {
						q.offer(new Point(nx, ny));
						map[nx][ny] = day+1;
					}
				}
			}
		}
		
		
	}

}


