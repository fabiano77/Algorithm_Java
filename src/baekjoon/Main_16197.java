package baekjoon;

import java.io.*;
import java.util.*;

public class Main_16197 {
	
	static class Point{
		static int N, M;
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public boolean isOut() {
			return !(0 <= r && r < N && 0 <= c && c < M);
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
	
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Point.N = N;
		Point.M = M;
		
		char[][] map = new char[N][M];
		
		List<Point> coins = new ArrayList<>(2);
		
		for(int r = 0; r < N; r++) {
			String line = br.readLine();
			for(int c = 0; c < M; c++) {
				map[r][c] = line.charAt(c);
				if(map[r][c] == 'o') {
					coins.add(new Point(r, c));
					map[r][c] = '.';
				}
			}
		}
		
		Set<List<Point>> visited = new HashSet<>();
		Queue<List<Point>> q = new LinkedList<>();
		visited.add(coins);
		q.add(coins);
		
		int ans = -1;
		int time = 0;
		bfs:
		while(!q.isEmpty()) {
			int qSize = q.size();
			time++;
			if(time > 10) break bfs;
			for(int i = 0; i < qSize; i++) {				
				List<Point> curCoins = q.poll();
				
				// 4방 탐색
				for(int[] delta : deltas) {
					List<Point> nextCoins = new ArrayList<>(2);
					// 2개 동전에 대해 이동
					for(Point coin : curCoins) {
						int nr = coin.r + delta[0];
						int nc = coin.c + delta[1];
						// 동전의 다음 지점이 맵 내이고 벽이라면
						if(0 <= nr && nr < N && 0 <= nc && nc < M && map[nr][nc] == '#') {
							nextCoins.add(coin);
						}
						// 맵 밖이거나 벽이 아니라면
						else {							
							nextCoins.add(new Point(nr, nc));
						}
					}
					
					// 동전이 겹쳐졌거나 둘다 아웃이라면
					if(nextCoins.get(0).equals(nextCoins.get(1)) || (nextCoins.get(0).isOut()) && nextCoins.get(1).isOut()) {
						continue;
					}
					// 둘중 하나만 아웃이라면
					else if(nextCoins.get(0).isOut() || nextCoins.get(1).isOut()) {
						ans = time;
						break bfs;
					}
					
					// 방문안한 경우라면
					if(!visited.contains(nextCoins)) {
						visited.add(nextCoins);
						q.add(nextCoins);
					}
				}
			}
		}
		
		System.out.println(ans);

	}

}
