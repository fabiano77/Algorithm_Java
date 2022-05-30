package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1726 {
	static class Robot {
		int r, c;
		int d;
		public Robot(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + d;
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
			Robot other = (Robot) obj;
			if (c != other.c)
				return false;
			if (d != other.d)
				return false;
			if (r != other.r)
				return false;
			return true;
		}
		public Robot go(int n) {
			int nr = -1;
			int nc = -1;
			for(int i = 1; i <= n; i++) {
				nr = r + deltas[d][0] * i;
				nc = c + deltas[d][1] * i;
				if(0 > nr || nr >= R || 0 > nc || nc >= C || map[nr][nc] == 1) {
					return null;
				}
			}
			return new Robot(nr, nc, d);
		}
		public Robot turn(boolean right) {
			int nd;
			if(right) {
				switch (d) {
				case 1:
					nd = 3;
					break;
				case 2:
					nd = 4;
					break;
				case 3:
					nd = 2;
					break;
				case 4:
					nd = 1;
					break;
				default:
					nd = -1;
					break;
				}
			}
			else {
				switch (d) {
				case 1:
					nd = 4;
					break;
				case 2:
					nd = 3;
					break;
				case 3:
					nd = 1;
					break;
				case 4:
					nd = 2;
					break;
				default:
					nd = -1;
					break;
				}
				
			}
			return new Robot(r, c, nd);
		}
		@Override
		public String toString() {
			return "Robot [r=" + r + ", c=" + c + ", d=" + d + "]";
		}
		
	}
	
	static int[][] deltas = {{}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 동 서 남 북
	static int[][] map;
	static int R;
	static int C;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken()); // ~100
		C = Integer.parseInt(st.nextToken()); // ~100
		
		map = new int[R][C];
		
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		Robot start;
		st = new StringTokenizer(br.readLine());
		{			
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken());
			start = new Robot(r, c, d);
		}
		Robot end;
		st = new StringTokenizer(br.readLine());
		{			
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken());
			end = new Robot(r, c, d);
		}
		
		// 원하는 위치, 방향 되도록 최소 몇번의 명령이 필요한지
		
		// 명령1 : go 1,2,3
		// 명령2 : turn left, right
		Set<Robot> visited = new HashSet<>();
		Queue<Robot> q = new LinkedList<>();
		q.offer(start);
		visited.add(start);
		int cnt = 0;
		bfs:
		while(!q.isEmpty()) {
			int qSize = q.size();
			while(qSize-- > 0) {
				Robot cur = q.poll();
				if(end.equals(cur)) {
					System.out.println(cnt);
					break bfs;
				}
				for(int i = 1; i <= 3; i++) {
					Robot next = cur.go(i);
					if(next != null && !visited.contains(next)) {
						visited.add(next);
						q.offer(next);
					}
				}
				for(boolean b : new boolean[]{true, false}) {
					Robot next = cur.turn(b);
					if(next != null && !visited.contains(next)) {
						visited.add(next);
						q.offer(next);
					}
				}
			}
			cnt++;
		}

	}

}
