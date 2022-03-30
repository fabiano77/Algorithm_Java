package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2636 {
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int R;
	static int C;
	static int lastCnt;
	static int cheeseCnt;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R][C];
		
		cheeseCnt = 0;
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				// 치즈 칸수 누적
				if(map[r][c] == 1) cheeseCnt++;
			}
		}
		lastCnt = cheeseCnt;

		
		int t = 0;
		while(melt(map)){
			t++;
			if(cheeseCnt != 0) lastCnt = cheeseCnt;
		}
		
		System.out.println(t);
		System.out.println(lastCnt);

	}
	
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public boolean isIn() {
			return 0 <= r && r < R && 0 <= c && c < C;
		}
	}
	
	
	public static boolean melt(int[][] map){
		boolean[][] visited = new boolean[R][C]; 
		Queue<Point> target = new LinkedList<>();
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0));
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int[] delta : deltas) {
				Point next = new Point(cur.r+delta[0], cur.c+delta[1]);
				
				// 범위 바깥
				if(!next.isIn()) continue;
				// 이미 방문한경우
				if(visited[next.r][next.c]) continue;
				
				visited[next.r][next.c] = true;
				
				// 치즈 가장자리
				if(map[next.r][next.c] == 1) {
					target.offer(next);
				}
				// 치즈 없는부분
				else {
					q.offer(next);
				}
			}
		}
		boolean isMelting = target.size() > 0;
		// 가장자리 치즈를 녹여준다
		while(!target.isEmpty()) {
			Point p = target.poll();
			map[p.r][p.c] = 0;
			cheeseCnt--;
		}
		
		
		return isMelting;
	}
	

}
