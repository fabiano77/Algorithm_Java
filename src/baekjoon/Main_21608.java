package baekjoon;

import java.io.*;
import java.util.*;

public class Main_21608 {
	
	static class Point implements Comparable<Point>{
		int r, c;
		int adjFavoriteCnt;
		int adjBlankCnt;
		
		public Point(int r, int c, int adjFavorites, int adjBlanks) {
			super();
			this.r = r;
			this.c = c;
			this.adjFavoriteCnt = adjFavorites;
			this.adjBlankCnt = adjBlanks;
		}
		
		@Override
		public int compareTo(Point o) {
			int ret = Integer.compare(adjFavoriteCnt, o.adjFavoriteCnt);
			if(ret == 0) ret = Integer.compare(adjBlankCnt, o.adjBlankCnt);
			if(ret == 0) ret = Integer.compare(r, o.r) * -1;
			if(ret == 0) ret = Integer.compare(c, o.c) * -1;
			return ret;
		}
		
	}
	
	static int N;
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int[][] mat = new int[N][N]; 
		
		Map<Integer, Set<Integer>> map = new HashMap<>();		
		
		int loopSize = N*N;
		for(int i = 0; i < loopSize; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			Set<Integer> set = new HashSet<>();
			for(int j = 0; j < 4; j++) {
				set.add(Integer.parseInt(st.nextToken()));
			}
			map.put(num, set);
			Point p = findPoint(mat, num, set);
			mat[p.r][p.c] = num; 
		}
		
		int ans = 0;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				int adjCnt = 0;
				for(int[] delta : deltas) {
					int nr = r + delta[0];
					int nc = c + delta[1];
					if(isIn(nr, nc) && map.get(mat[r][c]).contains(mat[nr][nc])) {
						adjCnt++;	
					}
				}
				ans += Math.pow(10, (adjCnt-1));
			}
		}
		
		System.out.println(ans);

		br.close();
	}
	
	public static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
	
	public static Point findPoint(int[][] mat, int num, Set<Integer> set) {
		Point maxPoint = null;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(mat[r][c] != 0) continue;
				int adjBlankCnt = 0;
				int adjFavoriteCnt = 0;
				for(int[] delta : deltas) {
					int nr = r + delta[0];
					int nc = c + delta[1];
					if(isIn(nr, nc)) {
						if(mat[nr][nc] == 0) {
							adjBlankCnt++;
						}
						else if(set.contains(mat[nr][nc])) {
							adjFavoriteCnt++;
						}
					}
				}
				Point curPoint = new Point(r, c, adjFavoriteCnt, adjBlankCnt);
				if(maxPoint == null || curPoint.compareTo(maxPoint) > 0) {
					maxPoint = curPoint;
				}
			}
		}
		return maxPoint;
	}

}
