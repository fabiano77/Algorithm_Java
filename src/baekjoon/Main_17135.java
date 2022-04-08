package baekjoon;

import java.io.*;
import java.util.*;

public class Main_17135 {
	static int N, M, D;
	static int ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		combi(map, 3, 0, new boolean[M], new int[3]);
		System.out.println(ans);
		

	}
	
	static class Point implements Comparable<Point>{
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		@Override
		public int compareTo(Point o) {
			return this.c-o.c;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
		
		
		
		
	}
	
	public static void combi(int[][] map, int toChoose, int idx, boolean[]isSelected, int[] select) {
		if(toChoose == 0) {
			int score = play(map, select);
			ans = Math.max(ans, score);
			return;
		}
		
		for(int i = idx; i < M; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			select[3-toChoose] = i;
			combi(map, toChoose-1, i+1, isSelected, select);
			isSelected[i] = false;
		}
		
	}
	
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public static boolean isIn(int r, int c, int time) {
		return 0 <= r && r < N-time && 0 <= c && c < M;
	}
	
	public static Point bfs(int[][] map, int r, int c, int d, int time) {
		Queue<Point> q = new LinkedList<>();
		PriorityQueue<Point> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][M];
		visited[r][c] = true;
		q.offer(new Point(r, c));
		
		int distance = 0;
		while(!q.isEmpty() && distance <= d) {
			int qSize = q.size();
			while(qSize-- > 0) {
				Point cur = q.poll();
				if(map[cur.r][cur.c] == 1) {
					pq.offer(cur);
				}
				if(!pq.isEmpty()) continue;
				for(int[] delta : deltas) {
					int nr = cur.r+delta[0];
					int nc = cur.c+delta[1];
					if(isIn(nr, nc, time) && !visited[nr][nc]) {
						visited[nr][nc] = true;
						q.offer(new Point(nr, nc));

					}
				}
			}
			if(!pq.isEmpty()) {
				return pq.poll();
			}
			distance++;
		}
		
		return null;
	}
	
	public static int play(int[][] map, int[] selected) {
		int[][] copyMap = new int[N][M];
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				copyMap[r][c] = map[r][c];
			}
		}
		int cnt = 0;
		for(int time = 0; time < N; time++) {		
			Set<Point> set = new HashSet<>();
			for(int col : selected) {
				set.add(bfs(copyMap, N-1-time, col, D-1, time));
			}
			for(Point p : set) {
				if(p != null) {
					copyMap[p.r][p.c] = 0; 
					cnt++;
				}
			}
		}
		return cnt;
	}
	

}
