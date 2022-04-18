package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_17780 {
	
	static class Horse {
		int idx;
		int r, c;
		int d;
		Horse child = null;
		Horse parent = null;
		
		
		
		public Horse(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
		
		public void backward() {
			if(d <= 1) {
				d = 1 - d;
			}
			else {
				d = 5 - d;
			}
		}
		
		public void move(int d) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];
			r = nr;
			c = nc;
			
			if(parent != null && !(parent.r == this.r && parent.c == this.c)) {
				parent.child = null;
				this.parent = null;
			}
			if(child != null) {
				child.move(d);
			}
		}
		
		public Horse getParent() {
			if(parent == null) {
				return this;
			}
			else {
				return parent.getParent();
			}
		}

		public void ride(Horse horse) {
			if(child == null) {
				Horse horseParent = horse.getParent();
				child = horseParent;
				horseParent.parent = this;
			}
			else {
				child.ride(horse);
			}
		}
		
		public void reverse() {
			if(child != null)
				child.reverse();
			Horse myChild = child;
			child = parent;
			parent = myChild;
		}
		
		public int getCnt() {
			return getChildCnt() + getParentCnt() + 1;
		}
		
		private int getChildCnt() {
			if(child == null) {
				return 0;
			}
			else {
				return child.getChildCnt() + 1;
			}
		}
		private int getParentCnt() {
			if(parent == null) {
				return 0;
			}
			else {
				return parent.getParentCnt() + 1;
			}
		}

		@Override
		public String toString() {
			return "Horse [r=" + r + ", c=" + c + ", d=" + d + "]";
		}
		
		
		
		
	}
	
	final static int WHITE = 0;
	final static int RED = 1;
	final static int BLUE = 2;
	final static int[][] deltas = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; // 우 좌 상 하

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 체스판과 말을 이용, 크기가 N N 인 체스판에서 진행
		// 말은 원판모양이며 하나의 말 위에 다른 말을 올릴 수 있다.
		// 체스판의 칸은 흰, 빨, 파 중 하나
		// 체스판 위에 K개의 말(1~K번)을 놓고 시작
		// 말들은 이동방향이 정해져있다(상, 하, 좌, 우)
		// 턴 한 번에 1~K번 말이 순서대로 이동,
		// 한 말이 이동할 떄 위에 올려져 있는 말도 함께 움직인다.
		//
		// 말이 4개 이상 쌓이는 순간 게임 종료 (값이 1000보다 크거나 종료되지 않으면 -1)
		
		// 흰색칸으로 이동하는 경우
		//     이동하고 말이 이미 있다면 이동한 말을 가장위에 놓는다
		// 빨간색칸으로 이동하는 경우
		//     이동한 후, 쌓여있는 말의 순서를 반대로 바꾼다
		//     A, B, C -> C, B, A
		//     A, B, C가 이동하고 칸에 D, E, F가 있는 경우 -> D, E, F, C, B, A
		// 파란색칸으로 이동하는 경우
		//     이동방향을 반대로 하고 한 칸 이동한다.
		//     바꾼 후에도 파란색인 경우에는 이동하지 않고 가만히 있는다.
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 4 ~ 12
		int K = Integer.parseInt(st.nextToken()); // 4 ~ 10
		
		int[][] board = new int[N][N];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken()); // 0:흰, 1:빨, 2:파
			}
		}
		
		Horse[] horses = new Horse[K];
		Horse[][] horseByBoard = new Horse[N][N];
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken())-1;
			horseByBoard[r][c] = horses[k] = new Horse(r, c, d);
			horses[k].idx = k;
		}
		int turnCnt;
		turnBreak:
		for(turnCnt = 1; turnCnt <= 1000; turnCnt++) {
			for(int k = 0; k < K; k++) {
				Horse cur = horses[k];
				if(cur.getParent() != cur) continue;

				int nr = cur.r + deltas[cur.d][0];
				int nc = cur.c + deltas[cur.d][1];
				
				if(!isIn(nr, nc, N) || board[nr][nc] == BLUE){
					cur.backward();
					nr = cur.r + deltas[cur.d][0];
					nc = cur.c + deltas[cur.d][1];
				}
				
				if(isIn(nr, nc, N) && board[nr][nc] != BLUE) {					
					if(board[nr][nc] == WHITE) {
						horseByBoard[cur.r][cur.c] = cur.parent;
						cur.move(cur.d);
						if(horseByBoard[nr][nc] != null) {
							horseByBoard[nr][nc].ride(cur);
						}
						else {
							horseByBoard[nr][nc] = cur;
						}
					} else if(board[nr][nc] == RED) {
						horseByBoard[cur.r][cur.c] = cur.parent;
						cur.move(cur.d);
						cur.reverse();
						if(horseByBoard[nr][nc] != null) {
							horseByBoard[nr][nc].ride(cur.getParent());
						}
						else {
							horseByBoard[nr][nc] = cur.getParent();
						}
					} 
				}

				if(cur.getCnt() >= 4) {
					break turnBreak;
				}
			}
		}
		
		
		System.out.println((turnCnt > 1000) ? -1 : turnCnt);
		

		

	}
	public static boolean isIn(int r, int c, int N) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}

}