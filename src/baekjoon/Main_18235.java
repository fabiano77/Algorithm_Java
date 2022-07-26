package baekjoon;

import java.io.*;

public class Main_18235 {
	
	static final int R = 5;
	static final int C = 9;
	static final int RC = R*C;
	static final int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	static int pinCnt;
	static int moveCnt;
	static int pinMin;
	static int moveMin;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		while(tc-- > 0) {
			
			char[][] map = new char[R][];
			for(int r = 0; r < R; r++) 
				map[r] = br.readLine().toCharArray();
			
			pinCnt = getCnt(map);
			pinMin = Integer.MAX_VALUE;
			moveCnt = 0;
			moveMin = Integer.MAX_VALUE;
			
			// 핀 옮기기 시작
			solve(map, 0);
			
			System.out.println(pinMin+" "+moveMin);
			
			// 테케사이 공백 제거
			if(tc > 0) br.readLine();
		}

		br.close();

	}
	
	public static int getCnt(char[][] map) {
		int cnt = 0;
		for(char[] line : map) {
			for(char c : line) {
				if(c == 'o') cnt++;
			}
		}
		return cnt;
	}
	
	public static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
	
	public static void solve(char[][] map, int idx) {
		// 종료조건
		if(idx == RC) {
			if(pinCnt < pinMin) {
				pinMin = pinCnt;
				moveMin = moveCnt;
			}
			else if(pinCnt == pinMin) {
				moveMin = Math.min(moveMin, moveCnt);
			}
			return;
		}
		
		
		int r = idx / C;
		int c = idx % C;
		
		
		// 핀일경우
		if(map[r][c] == 'o') {
			for(int[] delta : deltas) {
				int nnr = r + delta[0] * 2;
				int nnc = c + delta[1] * 2;
				if(!isIn(nnr, nnc))
					continue;
				int nr = r + delta[0];
				int nc = c + delta[1];
				
				// 인접 핀을 건너뛸수 있다면
				if(map[nr][nc] == 'o' && map[nnr][nnc] == '.') {
					map[r][c] = '.';
					map[nr][nc] = '.';
					map[nnr][nnc] = 'o';
					pinCnt--;
					moveCnt++;
					solve(map, 0);
					pinCnt++;
					moveCnt--;
					map[r][c] = 'o';
					map[nr][nc] = 'o';
					map[nnr][nnc] = '.';
				}
			}
		}
		
		solve(map, idx+1);
	}

}
