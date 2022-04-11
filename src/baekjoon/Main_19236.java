package baekjoon;

import java.io.*;
import java.util.*;

public class Main_19236 {
	
	static class Fish {
		public int size;
		private Stack<Integer> direction;
		public int r, c;

		public Fish(int size, int direction, int r, int c) {
			this.size = size;
			this.direction = new Stack<>();
			this.direction.push(direction);
			this.r = r;
			this.c = c;
		}
		
		public int getDirection() {
			return direction.peek();
		}
		
		public void setNextDirection(int direction) {
			this.direction.push(direction);
		}
		
		public void backupDirection() {
			direction.pop();
		}

		@Override
		public String toString() {
			return "Fish [size=" + size + ", direction=" + direction + ", r=" + r + ", c=" + c + "]";
		}
		
		
	}
	
	static int[][] deltas = {{}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
	
	static boolean first;
	static int ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Fish[][] fishMap = new Fish[4][4];
		Fish[] fishBySize = new Fish[16+1];
		boolean[] isAlive = new boolean[16+1];
		for(int r = 0; r < 4; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < 4; c++) {
				int size = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken());
				isAlive[size] = true;
				
				fishMap[r][c] = fishBySize[size] = new Fish(size, direction, r, c); 
			}
		}
		
		first = true;
		sharkMove(0, 0, 0, 0, fishMap, fishBySize, isAlive);
		System.out.println(ans);
	}
	
	
	public static boolean isIn(int r, int c) {
		return 0 <= r && r < 4 && 0 <= c && c < 4;
	}
	
	public static void print(Fish[][] map) {
		for(int r = 0; r < 4; r++) {
			for(int c = 0; c < 4; c++) {
				if(map[r][c] == null) System.out.print("\tnull \tnull");
				else System.out.print("\t(s:"+map[r][c].size+"\t d:"+map[r][c].getDirection()+")");
			}
			System.out.println();
		}
	}
	
	public static void sharkMove(int sharkR, int sharkC, int sharkD, int sharkCnt, Fish[][] fishMap, Fish[] fishBySize, boolean[] isAlive) {
		if(sharkCnt > ans) {
			ans = sharkCnt;
		}
		if(first) {
			first = false;
			// 초기위치 0, 0
			sharkR = 0;
			sharkC = 0;
			// 물고기 먹음
			sharkCnt += fishMap[0][0].size;
			sharkD = fishMap[0][0].getDirection();
			isAlive[fishMap[0][0].size] = false;
			fishMap[0][0] = null;
			
			fishMove(sharkR, sharkC, fishMap, fishBySize, isAlive, false);
			sharkMove(0, 0, sharkD, sharkCnt, fishMap, fishBySize, isAlive);
		}
		else {
			for(int nr = sharkR, nc = sharkC; isIn(nr, nc); nr += deltas[sharkD][0], nc += deltas[sharkD][1] ) {
				// 먹을 물고기 없다면 스킵
				if(fishMap[nr][nc] == null) continue;
				
				// 물고기 먹음
				Fish prey = fishMap[nr][nc];
				
				isAlive[prey.size] = false;
				fishMap[nr][nc] = null;
				fishMove(nr, nc, fishMap, fishBySize, isAlive, false);
				sharkMove(nr, nc, prey.getDirection(), sharkCnt + prey.size, fishMap, fishBySize, isAlive);
				fishMove(nr, nc, fishMap, fishBySize, isAlive, true);
				fishMap[nr][nc] = prey;
				isAlive[prey.size] = true;
			}
		}
		
	}
	
	public static void swapFish(Fish[][] fishMap, Fish a, Fish b, boolean reverse) {
		if(b == null) {
			// 빈 자리 가는 경우
			int direction = a.getDirection();
			if(reverse) direction = (direction + 3) % 8 + 1;
			int beforeR = a.r;
			int beforeC = a.c;
			a.r += deltas[direction][0];
			a.c += deltas[direction][1];
			fishMap[a.r][a.c] = a;
			fishMap[beforeR][beforeC] = null;
		}
		else {		
			// 맞바꾸는 경우.
			int ar = a.r;
			int ac = a.c;
			a.r = b.r;
			a.c = b.c;
			b.r = ar;
			b.c = ac;
			fishMap[a.r][a.c] = a;
			fishMap[b.r][b.c] = b; 
		}
	}
	
	public static void fishMove(int sharkR, int sharkC, Fish[][] fishMap,  Fish[] fishBySize, boolean[] isAlive, boolean reverse) {
		int size = -1, delta = 0;
		
		if(reverse) {
			size = 16; delta = -1;
		}
		else {
			size = 1;  delta = 1;
		}
		
		while(1 <= size && size <= 16) {
			if(!isAlive[size]) {
				size += delta;
				continue;
			}
			Fish curFish = fishBySize[size];
			int nr = -1;
			int nc = -1;
			int direction = curFish.getDirection();
			while(!reverse) {
				// 물고기가 보고있는 방향을 살펴보고
				nr = curFish.r + deltas[direction][0];
				nc = curFish.c + deltas[direction][1];
				// 갈 수 있는 방향이면 방향확정 <- 상어 아닌 조건 추가해야함
				if(isIn(nr, nc) && !(nr == sharkR && nc == sharkC)) break;
				// 갈 수 없다면 반시계방향 45도 회전
				direction = (direction % 8) + 1;
			}
			
			// 거꾸로 돌리는 경우 방향을 반대편으로 보정함
			if(reverse) {
				int backward = ((curFish.getDirection()+3) % 8) + 1;
				nr = curFish.r + deltas[backward][0];
				nc = curFish.c + deltas[backward][1];
			}
			else {
				curFish.setNextDirection(direction);
			}
			
			Fish targetFish = fishMap[nr][nc];
			swapFish(fishMap, curFish, targetFish, reverse);
			
			if(reverse) curFish.backupDirection();
			
			// reverse에 따라 증가, 감소
			size += delta;
		}
	}

}
