package baekjoon;

import java.io.*;
import java.util.*;

public class Main_14500 {
	static int N, M;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int [N][M];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		// 테트로미노종류, 회전대칭모든경우, 
		int[][][] tetrominoes = {
					{{0, 0}, {0, 1}, {0, 2}, {0, 3}}, 
					{{0, 0}, {0, 1}, {1, 0}, {1, 1}},
					{{0, 0}, {0, 1}, {0, 2}, {1, 2}},
					{{0, 0}, {0, 1}, {1, 1}, {1, 2}},
					{{0, 0}, {1, 0}, {1, 1}, {2, 0}},
		};
		
		int max = 0;
		for(int[][] tetromino : tetrominoes) {
			for(int f = 0; f < 2; f++) {
				tetromino = flip(tetromino);
				for(int d = 0; d < 4; d++) {
					tetromino = rotate(tetromino);
					max = Math.max(max, getMax(map, tetromino));
				}
			}
		}
		
		System.out.println(max);
		
		

	}
	public static int getMax(int[][] map, int[][] tetromino) {
		int max = 0;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				max = Math.max(max, getSum(map, tetromino, r, c));
			}
		}
		return max;
	}
	
	public static int getSum(int[][] map, int[][] tetromino, int r, int c) {
		int sum = 0;
		
		for(int[] delta : tetromino) {
			int curR = r + delta[0];
			int curC = c + delta[1];
			if(!isIn(curR, curC)) return -1;
			sum += map[curR][curC];
		}
		
		return sum;
	}
	
	 public static boolean isIn(int r, int c) {
		 return 0 <= r && r < N && 0 <= c && c < M;
	 }
	 
	 private static int[][] rotate(int[][] tetromino){
		 int[][] rotated = new int[4][2];
		 for(int i = 0; i < 4; i++) {
			 rotated[i][0] = tetromino[i][1];
			 rotated[i][1] = -tetromino[i][0];
		 }
		 
		 return rotated;
	 }
	 
	 private static int[][] flip(int[][] tetromino){
		 int[][] flipped = new int[4][2];
		 
		 for(int i = 0; i < 4; i++) {
			 flipped[i][0] = tetromino[i][0];
			 flipped[i][1] = -tetromino[i][1];
		 }
		 
		 return flipped;
	 }

}
