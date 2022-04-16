package baekjoon;

import java.io.*;
import java.util.*;

public class Main_9328 {
	static class Point{
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
		
		
	}
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	static int myKey;
	static int ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			char[][] map = new char[R][C];
			for(int r = 0; r < R; r++) {
				String line = br.readLine();
				for(int c = 0; c < C; c++) {
					map[r][c] = line.charAt(c);
				}	
			}
			myKey = 0;
			ans = 0;
			String key = br.readLine();
			if(!key.equals("0")) {
				for(char k : key.toCharArray()) {
					myKey = addKey(myKey, k);
				}
			}
			boolean[][] visited = new boolean[R][C];
			Queue<Point> q = new LinkedList<>();
			Queue<Point> doorQ = new LinkedList<>();
			for(int r = 0; r < R; r++) {
				int[] cList = {0, C-1};
				for(int c : cList) {					
					visit(r, c, q, doorQ, visited, map);
				}
			}
			for(int c = 0; c < C; c++) {
				int[] rList = {0, R-1};
				for(int r : rList) {					
					visit(r, c, q, doorQ, visited, map);
				}
			}
			
			boolean open = true;
			while(open) {
				while(!q.isEmpty()) {
					Point cur = q.poll();
					for(int[] delta : deltas) {
						int nr = cur.r + delta[0];
						int nc = cur.c + delta[1];
						if(isIn(nr, nc, R, C)) {
							visit(nr, nc, q, doorQ, visited, map);
						}
					}
				}
				int doorQ_Size = doorQ.size();
				open = false;
				while(doorQ_Size-- > 0) {
					Point cur = doorQ.poll();
					if(isOpen(myKey, map[cur.r][cur.c])) {
						open = true;
						for(int[] delta : deltas) {
							int nr = cur.r + delta[0];
							int nc = cur.c + delta[1];
							if(isIn(nr, nc, R, C)) {
								visit(nr, nc, q, doorQ, visited, map);
							}
						}
					}
					else {
						doorQ.offer(cur);
					}
				}
			}
			System.out.println(ans);
		}
		br.close();
	}
	
	public static boolean isIn(int r, int c, int R, int C) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
	
	public static void visit(int r, int c, Queue<Point> q, Queue<Point> doorQ, boolean[][] visited, char[][] map) {
		if(!visited[r][c] && map[r][c] != '*') {
			if(Character.isLowerCase(map[r][c])) {
				myKey = addKey(myKey, map[r][c]);
				q.offer(new Point(r, c));
			}
			else if(Character.isUpperCase(map[r][c])) {
				doorQ.add(new Point(r, c));
			}
			else if(map[r][c] == '$') {
				q.offer(new Point(r, c));
				ans++;
			}
			else {
				q.offer(new Point(r, c));
			}
			visited[r][c] = true;
		}
	}
	
	final static int gap = 'A' - 'a';
	public static int addKey(int myKey, char addKey) {
		myKey |= 1 << (addKey - 'a');
		return myKey;
	}
	public static boolean isOpen(int key, char door) {
		int searchKey = 1 << ((door-gap) - 'a');
		return (key & searchKey) > 0;
	}

}
