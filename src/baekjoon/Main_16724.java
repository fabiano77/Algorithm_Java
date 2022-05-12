package baekjoon;

import java.io.*;
import java.util.*;

public class Main_16724 {
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static char[] dirs = {'D', 'L', 'U', 'R'};
	static int cnt = 0;
	static int totalCnt;
	static int R;
	static int C;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		totalCnt = R * C; 
		
		char[][] map = new char[R][];
		
		for(int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}
		
		boolean[][] visited = new boolean[R][C];
		int safeZone = 0;
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(visited[r][c]) continue;
				bfs(r, c, visited, map);
				safeZone++;
			}
		}
		
		System.out.println(safeZone);
	}
	
	static class Point{
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void bfs(int r, int c, boolean[][] visited, char[][] map) {
		//Queue<Point> q = new LinkedList<>();	// 시간: 388ms, 메모리: 70232KB
		Queue<Point> q = new ArrayDeque<>();	// 시간: 376ms, 메모리: 85128KB
		visited[r][c] = true;
		q.offer(new Point(r, c));
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			// 내가 가리키고 있는 곳 탐색
			{
				int curDir = "URDL".indexOf(map[cur.r][cur.c]); 
				int nr = cur.r + deltas[curDir][0];
				int nc = cur.c + deltas[curDir][1];
				if(!visited[nr][nc]) {
					visited[nr][nc] = true;
					q.offer(new Point(nr, nc));
				}
			}
			
			// 현재 cur를 사방탐색하여 cur를 가리키고 있는 화살표도 탐색함.
			for(int dir = 0; dir < 4; dir++) {
				int nr = cur.r + deltas[dir][0];
				int nc = cur.c + deltas[dir][1];
				if(0 <= nr && nr < R && 0 <= nc && nc < C &&	// 맵 내부범위이고 
						!visited[nr][nc] && 					// 방문하지 않았고
						map[nr][nc] == dirs[dir])				// 나를 가리키고있다면 
				{				
					visited[nr][nc] = true;
					q.offer(new Point(nr, nc));
				}
				
			}
			
		}
		
	}
	

}

