package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1194 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R][C];
		Point start = null;
		for(int r = 0; r < R; r++) {
			String line = br.readLine();
			for(int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c);
				if(map[r][c] == '0') {
					start = new Point(r, c, 0, 0);
					map[r][c] = '.';
				}
				
			}
		}
		
		System.out.println(bfs(map, R, C, start));
		

		
		
	}
	static class Point {
		int r, c;
		int d;
		int keys;

		public Point(int r, int c, int d, int keys) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.keys = keys;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", d=" + d + ", keys=" + keys + "]";
		}
		
		
	}
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public static boolean isIn(int r, int c, int R, int C) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
	public static int setKey(int key, int keys) {
		key -= 'a';
		keys |= 1<<key;
		return keys;
	}
	
	public static boolean isOpen(char door, int keys) {
		int doorBit = 1 << (door-'A');
		return (keys & doorBit) > 0;
	}
	public static boolean haveKey(char key, int keys) {
		int keyBit = 1 << (key-'a');
		return (keys & keyBit) > 0;
	}
	
	public static int bfs(char[][] map, int R, int C, Point start) {
		boolean[][][] visited = new boolean[R][C][1<<7];
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		visited[start.r][start.c][0] = true;
		int ans = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			Point cur = q.poll();				
			
			for(int[] delta : deltas) {
				int nr = cur.r + delta[0];
				int nc = cur.c + delta[1];
				if(!isIn(nr, nc, R, C) || visited[nr][nc][cur.keys]) {
					continue;
				}
				
				if(map[nr][nc] == '1') {
					return cur.d+1;
				}
				else if(Character.isLowerCase(map[nr][nc])) {
					// 키인 경우
					visited[nr][nc][cur.keys] = true;
					q.offer(new Point(nr, nc, cur.d+1, setKey(map[nr][nc], cur.keys)));
				}
				else if(map[nr][nc] == '.' || (Character.isUpperCase(map[nr][nc]) && isOpen(map[nr][nc], cur.keys))) {
					// 길이거나 문인데 키를 가지고 있는 경우
					visited[nr][nc][cur.keys] = true;
					q.offer(new Point(nr, nc, cur.d+1, cur.keys));
				}
			}

		}
		if(ans == Integer.MAX_VALUE) return -1;
		return ans;
	}

}
