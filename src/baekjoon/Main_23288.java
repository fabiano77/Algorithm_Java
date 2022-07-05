package baekjoon;

import java.io.*;
import java.util.*;

public class Main_23288 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	
	static class Dice {
		static class Piece{
			int num;
		}
		
		static int[][] map;
		static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		static int N, M;
		Piece[] vertical;
		Piece[] horizontal;
		int dir = 1; //북 동 남 서
		int r, c;
		
		public Dice() {
			Piece[] pieces = new Piece[6];
			int[] nums = {2, 6, 5, 1, 4, 3};
			for(int i = 0; i < 6; i++) {
				pieces[i] = new Piece();
				pieces[i].num = nums[i];
			}
			
			vertical   = new Piece[]{pieces[1], pieces[2], pieces[3], pieces[0]};
			horizontal = new Piece[]{pieces[4], pieces[1], pieces[5], pieces[3]};
			
			this.r = 0;
			this.c = 0;
		}
		
		private boolean isIn(int r, int c) {
			return 0 <= r && r < N && 0 <= c && c < M;
		}
		
		private int getNum() {
			return vertical[0].num;
		}
		
		public void move() {
			int nr = r + deltas[dir][0];
			int nc = c + deltas[dir][1];
			// 범위 밖이라면 반대방향으로 move
			if(!isIn(nr, nc)) {
				dir = (dir+2) % 4;
				move();
				return;
			}
			r = nr;
			c = nc;
			
			// 주사위를 굴린다
			Piece[] line = (dir % 2 == 0)? vertical : horizontal;
			Deque<Integer> deque = new ArrayDeque<>();
			for(Piece el : line) {
				deque.add(el.num);
			}
			
			if(dir == 1 || dir == 2) {
				deque.addLast(deque.pollFirst());
			}
			else {
				deque.addFirst(deque.pollLast());
			}
			
			for(Piece el : line) {
				el.num = deque.pollFirst();
			}
			
			//방향을 회전할지 결정한다
			int A = this.getNum();
			int B = map[r][c];
			
			if(A > B) {
				dir++;
				if(dir > 3) dir = 0;
			}
			else if(A < B) {
				dir--;
				if(dir < 0) dir = 3;
			}
			
		}
		
		
		public int getScore() {
			return bfs() * map[r][c];
		}
		
		private int bfs() {
			Queue<Point> q = new LinkedList<>();
			boolean[][] visited = new boolean[N][M];
			q.offer(new Point(r, c));
			visited[r][c] = true;
			int num = map[r][c];
			int cnt = 1;
			while(!q.isEmpty()) {
				Point cur = q.poll();
				for(int[] delta : deltas) {
					int nr = cur.r + delta[0];
					int nc = cur.c + delta[1];
					if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == num) {
						visited[nr][nc] = true;
						cnt++;
						q.offer(new Point(nr, nc));
					}
				}
			}
			return cnt;
		}

	}
	
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int N, M;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
	
		
		int[][] map = new int[N][M];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		Dice.map = map;
		Dice.N = N;
		Dice.M = M;
		
		int ans = 0;
		Dice dice = new Dice();
		while(K-- > 0) {
			dice.move();
			ans += dice.getScore();
		}
		
		System.out.println(ans);
		
		br.close();
		
	}


}
