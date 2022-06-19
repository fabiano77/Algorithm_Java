package baekjoon;

import java.io.*;
import java.util.*;

public class Main_17779 {

	static int N;
	static int total;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				total += map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int min = Integer.MAX_VALUE;

		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				for (int d1 = 1; d1 < N; d1++) {
					for (int d2 = 1; d2 < N; d2++) {
						if (check(x, y, d1, d2)) {
							min = Math.min(min, solve(x, y, d1, d2, map));
						}
					}
				}
			}
		}

		System.out.println(min);

		br.close();
	}

	private static int solve(int x, int y, int d1, int d2, int[][] map) {
		int[][] dr = { { 1, -1 }, { 1, 1 } };
		boolean[][] area5 = new boolean[N][N];
		// 5 경계선 그리기
		for (int d = 0; d < d1 + 1; d++) {
			int nr = x + dr[0][0] * d;
			int nc = y + dr[0][1] * d;
			area5[nr][nc] = true;
		}
		for (int d = 0; d < d2 + 1; d++) {
			int nr = x + dr[1][0] * d;
			int nc = y + dr[1][1] * d;
			area5[nr][nc] = true;
		}
		for (int d = 0; d < d2 + 1; d++) {
			int nr = x + d1 + dr[1][0] * d;
			int nc = y - d1 + dr[1][1] * d;
			area5[nr][nc] = true;
		}
		for (int d = 0; d < d1 + 1; d++) {
			int nr = x + d2 + dr[0][0] * d;
			int nc = y + d2 + dr[0][1] * d;
			area5[nr][nc] = true;
		}


		int sum[] = new int[5+1];
		int sum1234 = 0;
		
		// 1구역
		for (int i = 0; i < x + d1; i++) {
			for (int j = 0; j <= y; j++) {
				if (area5[i][j]) break;
				sum[1] += map[i][j];
			}
		}
		sum1234 += sum[1];
		
		// 2구역
		for (int i = 0; i <= x + d2; i++) {
			for (int j = N - 1; j > y; j--) {
				if (area5[i][j]) break;
				sum[2] += map[i][j];
			}
		}
		sum1234 += sum[2];
		
		// 3구역
		for (int i = N - 1; i >= x + d1; i--) {
			for (int j = 0; j < y - d1 + d2; j++) {
				if (area5[i][j]) break;
				sum[3] += map[i][j];
			}
		}
		sum1234 += sum[3];
		
		// 4구역 
		for (int i = N - 1; i > x + d2; i--) {
			for (int j = N - 1; j >= y - d1 + d2; j--) {
				if (area5[i][j]) break;
				sum[4] += map[i][j];
			}
		}
		sum1234 += sum[4];
		
		// 5구역
		sum[5] = total - sum1234;
		

		Arrays.sort(sum);
		int result = sum[5] - sum[1];
		return result;
	}

	private static boolean check(int x, int y, int d1, int d2) {
		return x + d1 < N && y - d1 >= 0 && x + d2 < N && y + d2 < N && x + d1 + d2 < N && y - d1 + d2 < N;
	}

}
