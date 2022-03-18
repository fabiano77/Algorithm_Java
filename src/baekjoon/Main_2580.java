package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2580 {
	static boolean solved;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] map = new int[9][9];
		
		for(int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		dfs(map, 0);

		StringBuilder sb = new StringBuilder();
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {				
				sb.append(map[r][c]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}
	public static void dfs(int[][] map, int idx) {
		if(idx == 81) {
			solved = true;
			return;
		}
		int r = idx / 9;
		int c = idx % 9;
		
		if(map[r][c] == 0) {
			
			for(int i = 1; i <= 9; i++) {
				if(checkRow(map, r, i) && checkCol(map, c, i) && checkSquare(map, r, c, i)) {
					map[r][c] = i;					
					dfs(map, idx+1);
					if(solved) return;
					map[r][c] = 0;
				}
			}
		}
		else {
			dfs(map, idx+1);			
		}
	}
	
	public static boolean checkRow(int[][] map, int r, int value) {
		for(int c = 0; c < 9; c++) {
			if(map[r][c] == value) return false;
		}
		return true;
	}
	
	public static boolean checkCol(int[][] map, int c, int value) {
		for(int r = 0; r < 9; r++) {
			if(map[r][c] == value) return false;
		}
		return true;
	}
	
	public static boolean checkSquare(int[][] map, int r, int c, int value) {
		int startR = r - r % 3;
		int startC = c - c % 3;
		for(int tr = startR; tr < startR + 3; tr++) {
			for(int tc = startC; tc < startC + 3; tc++) {
				if(map[tr][tc] == value) return false;
			}
		}
		return true;
	}

}
