package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17136 {
	static int[] colorPaperCnt = {0, 5, 5, 5, 5, 5};
	static int[][] map = new int[10][10];
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve(0, 0);

		if(ans == Integer.MAX_VALUE) 	System.out.println(-1);
		else 							System.out.println(ans);
		
	}

	
	public static void solve(int idx, int cnt) {
		// 백트래킹(가지치기)
		if(cnt >= ans) {
			return;
		}
		// 기저 파트
		if(idx == 100) {
			if(isClearMap()) {
				ans = Math.min(ans, cnt);
			}
			return;
		}
		
		// 유도 파트
		int r = idx / 10;
		int c = idx % 10;
		if(map[r][c] == 0) solve(idx+1, cnt);
		for(int size = 1; size <= 5 ; size++) {
			if(check(r, c, size)) {
				// 색종이 붙이기
				attach(r, c, size, 0);
				colorPaperCnt[size]--;
				
				// 재귀적 완전 탐색
				solve(idx+1, cnt+1);
				
				// 색종이 떼기
				attach(r, c, size, 1);
				colorPaperCnt[size]++;
			}
		}
	}
	
	// r, c 로부터 size 크기의 색종이를 붙일수 있는지 확인
	public static boolean check(int r, int c, int size) {
		if(r+size > 10 || c+size > 10 || colorPaperCnt[size] <= 0) return false;
		for(int tr = r; tr < r+size; tr++) {
			for(int tc = c; tc < c+size; tc++) {
				if(map[tr][tc] == 0) {
					return false;
				}
			}
		}			
		return true;
	}
	
	// r, c 로부터 size 크기의 색종이를 붙이거나 뗀다
	public static void attach(int r, int c, int size, int to) {
		for(int tr = r; tr < r+size; tr++) {
			for(int tc = c; tc < c+size; tc++) {
				map[tr][tc] = to;
			}
		}
	}
	
	// 종이를 확인하여, 클리어했다면 true 반환
	public static boolean isClearMap() {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(map[i][j] == 1) return false;
			}
		}
		return true;
	}
	
	public static void printMap() {
		for(int[] row : map) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
	}


	
}
