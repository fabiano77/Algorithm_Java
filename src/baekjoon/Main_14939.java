package baekjoon;

import java.io.*;
import java.util.*;

public class Main_14939 {
	static int ans = Integer.MAX_VALUE;
	static char[][] originMap;
	static char[][] map;
	static int[][] deltas = {{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new char[10][10];
		originMap = new char[10][10];
		for(int i = 0; i < 10; i++) {
			String row = br.readLine();
			for(int j = 0; j < 10; j++) {
				originMap[i][j] = row.charAt(j);
			}
		}
		
		for(int flag = 0; flag < (1 << 10); flag++) {			
			copy(originMap, map);
			solve(flag);
		}
		if(ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
	
	public static void solve(int flag) {
		int cnt = 0;
		// 첫 행에서 누르는 경우의수
		for(int c = 0; c < 10; c++) {
			if((flag & (1 << c))> 0) {
				cnt++;
				toggle(0, c);
			}
		}
		

		for(int r = 1; r < 10; r++) {
			for(int c = 0; c < 10; c++) {
				if(map[r-1][c] == 'O') {
					toggle(r, c);
					cnt++;
				}
			}
		}

		for(int c = 0; c < 10; c++) {
			if(map[9][c] == 'O') return;
		}
		
		ans = Math.min(ans, cnt);


	}
	
	
	public static void toggle(int r, int c) {
		int nr, nc;
		for(int[] delta : deltas) {
			nr = r + delta[0];
			nc = c + delta[1];
			if(isIn(nr, nc)) map[nr][nc] = (map[nr][nc] == 'O')? '#':'O';
		}
	}
	
	public static boolean isIn(int r, int c) {
		return 0 <= r && r < 10 && 0 <= c && c < 10;
	}
	public static boolean isClear(int r) {
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < 10; j++) {
				if(map[i][j] == 'O') return false;
			}
		}
		return true;
	}
	public static void copy(char[][] src, char[][] dst) {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				dst[i][j] = src[i][j];
			}
		}
	}
	
	public static void printMap() {
		for(char[] row : map) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
	}

}
