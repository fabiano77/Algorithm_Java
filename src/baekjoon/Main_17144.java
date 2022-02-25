package baekjoon;

import java.io.*;
import java.util.*;

public class Main_17144 {
	static int R, C, T;
	static int purifierRow;
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};	//

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R][C];
		
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
			if(map[r][0] == -1) {
				purifierRow = r;
			}
		}
		
		// T초동안 확산과 공기순환을 반복
		while(T-- > 0) {
			map = diffuse(map);
			purify(map);
		}

		// 남아있는 미세먼지 양 출력
		System.out.println(check(map));

	}
	
	// 2차원 배열 공간 안에 있는지 논리값 반환
	public static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C ;
	}
	
	// 확산 가능한지 논리값 반환
	public static boolean isValid(int r, int c) {
		if(isIn(r, c)) {
			if(c == 0 && (r == purifierRow || r == purifierRow-1)) {
				return false;
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	// 미세먼지 확산
	public static int[][] diffuse(int[][] map) {
		int[][] nextMap = new int[R][C];
		nextMap[purifierRow-1][0] = -1;
		nextMap[purifierRow][0] = -1;
		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(c == 0 && (r == purifierRow || r == purifierRow-1)) continue;
				int amount = map[r][c]/5;
				
				for(int[] delta : deltas) {
					int nr = r + delta[0];
					int nc = c + delta[1];
					if(isValid(nr, nc)) {
						nextMap[nr][nc] += amount;
						map[r][c] -= amount;
					}
				}
				nextMap[r][c] += map[r][c];
			}
		}
		
		return nextMap;
	}
	
	// 공기청정기 작동
	public static void purify(int[][] map) {

		// top
		airCirculate(purifierRow-1, 0, 0, purifierRow-1, new int[] {0, 1, 2, 3}, map);
		
		// bottom
		airCirculate(purifierRow, 0, purifierRow, R-1, new int[] {2, 1, 0, 3}, map);

	}
	
	// 공기 순환
	public static void airCirculate(int r, int c, int upperR, int lowerR, int[] dirs, int[][] map) {
		int nr, nc; 
		for(int d : dirs) {
			while(true) {
				nr = r + deltas[d][0];
				nc = c + deltas[d][1];
				if(!(0 <= nc && nc < C && upperR <= nr && nr <= lowerR)) break;
				
				if(map[nr][nc] == -1) {
					map[r][c] = 0;
					return;
				}
				if(!( c == 0 && (r == purifierRow || r == purifierRow-1))) {
					map[r][c] = map[nr][nc];					
				}
				
				r = nr;
				c = nc;
			}
		}
	}
	
	// map의 미세먼지 총합 반환
	public static int check(int[][] map) {
		int cnt = 0;
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] != -1) {
					cnt += map[r][c];
				}
			}
		}
		
		return cnt;
	}

}
