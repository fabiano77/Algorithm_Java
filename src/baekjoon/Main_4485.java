package baekjoon;

import java.io.*;
import java.util.*;

public class Main_4485 {
	

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		for(int tc = 1; N != 0; tc++) {
			int[][] map = new int[N][N];
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			

			
			sb.append("Problem ").append(tc).append(": ").append(dijkstra(map, N)).append("\n");
			N = Integer.parseInt(br.readLine());
		}
		System.out.println(sb);

		

	}
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; 
	static class Point implements Comparable<Point>{
		int r, c;
		int val;
		public Point(int r, int c, int val) {
			this.r = r;
			this.c = c;
			this.val = val;
		}
		@Override
		public int compareTo(Point o) {
			return this.val - o.val;
		}
		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", val=" + val + "]";
		}
		
	}
	
	public static int dijkstra(int[][] map, int N) {
		int[][] distance = new int[N][N];
		for(int i = 0; i < N; i++) Arrays.fill(distance[i], 100_000);
		
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(0, 0, map[0][0]));
		while(distance[N-1][N-1] == 100_000) {
			Point minP = pq.poll();
			distance[minP.r][minP.c] = minP.val;
			for(int[] delta : deltas) {
				int nr = minP.r + delta[0];
				int nc = minP.c + delta[1];
				if(!isIn(nr, nc, N)) continue;
				if(distance[nr][nc] != 100_000) continue;
				pq.offer(new Point(nr, nc, minP.val+map[nr][nc]));
			}
		}
		
		return distance[N-1][N-1];
		
	}
	
	public static boolean isIn(int r, int c, int N) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}

}
