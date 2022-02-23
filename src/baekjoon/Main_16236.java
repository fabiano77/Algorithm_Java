package baekjoon;

import java.io.*;
import java.util.*;

public class Main_16236 {
	static class Point implements Comparable<Point>{
		public int r;
		public int c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public int compareTo(Point o) {
			if(this.r == o.r) return this.c - o.c;
			return this.r - o.r;
		}
		
	}
	
	static final int[][] deltas = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	static int N;
	static int myR, myC, mySize;
	static int requireNum;
	static int time;

	public static void main(String[] args)throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					map[i][j] = 0;
					myR = i;
					myC = j;
					mySize = 2;
					requireNum = 2;
				}
			}
		}
		// 크기가 작으면 먹는다. 크기가 같으면 지나갈 수 있다 크기가. 크면 지나갈 수 없다.

		boolean[][] visited = new boolean[N][N];
		while(bfs(map, visited)) {
			visited = new boolean[N][N];
		}
		
		System.out.println(time);
		
	}
	
	public static boolean bfs(int[][] map, boolean[][] visited) {
		// BFS 위한 큐
		Queue<Point> q = new LinkedList<>();
		// 거리가 같은 먹을 수 있는 것 중 가장 위, 왼쪽 우선순위로 먹는 큐
		PriorityQueue<Point> consume = new PriorityQueue<>();
		
		q.offer(new Point(myR, myC));
		visited[myR][myC] = true;
		
		int r, c, nr, nc;
		int curTime = 0;
		while(!q.isEmpty()) {
			int qSize = q.size();
			curTime++;
			while(qSize-- > 0) {				
				r = q.peek().r;
				c = q.poll().c;
				for(int[] delta : deltas) {
					nr = r + delta[0];
					nc = c + delta[1];
					if(isIn(nr, nc) && !visited[nr][nc]) {
						if(map[nr][nc] == 0 || map[nr][nc] == mySize) {
							visited[nr][nc] = true;
							q.offer(new Point(nr, nc));
						}
						else if(map[nr][nc] < mySize) {
							// 먹을 예정인 큐에 추가
							consume.offer(new Point(nr, nc));
						}
						else if (map[nr][nc] > mySize) {
							continue;
						}
					}
				}
			}
			// 먹을 수 있는 물고기가 있다면
			while (!consume.isEmpty()) {
				myR = consume.peek().r;
				myC = consume.peek().c;
				map[myR][myC] = 0;
				if(--requireNum == 0) {
					mySize++;
					requireNum = mySize;
				}
				time += curTime;
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
	
	public static void printMap(int[][] map) {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(r == myR && c == myC)System.out.print("* ");
				else System.out.print(map[r][c]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
