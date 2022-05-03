package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1799 {
	static int[] rDelta = {-1, -1, +1, +1};
	static int[] cDelta = {-1, +1, -1, +1};
	static int N;
	static int whiteAns;
	static int blackAns;
	static int endIdx;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		endIdx = N * N;
		map = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0, true);
		dfs(1, 0, false);
		
		System.out.println(whiteAns + blackAns);
		br.close();
	}
	
	public static void dfs(int idx, int cnt, boolean white) {
		if(idx >= endIdx) {
			if(white) {
				whiteAns = Math.max(whiteAns, cnt); 
			}
			else {
				blackAns = Math.max(blackAns, cnt);
			}
			return;
		}
		int r = idx / N;
		int c = idx % N;
		if((((r + c)%2 == 0)&& white) || (((r + c)%2 == 1)&& !white)) {			
			// 비숍 놓을 수 있는 경우
			if(map[r][c] == 1) {
				diagonal(r, c, -1);
				dfs(idx+1, cnt+1, white);
				diagonal(r, c, +1);
			}
		}
		
		dfs(idx+1, cnt, white);
	}
	public static void diagonal(int r, int c, int delta) {
		for(int d = 0; d < 4; d++) {			
			for(int nr = r, nc = c; 	// 초기식
					0 <= nr && nr < N && 0 <= nc && nc < N;	// 조건식 
					nr += rDelta[d], nc += cDelta[d]) 
			{		// 증감식
				map[nr][nc] += delta;
			}
		}
	}

}
