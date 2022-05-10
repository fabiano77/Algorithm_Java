package baekjoon;

import java.io.*;
import java.util.*;

public class Main_14600 {
	static int idx = 0;
	static int[][] map;
	static int N;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		N = (int)Math.pow(2, K);
		map = new int[N][N];

		
		int targetC = Integer.parseInt(st.nextToken());
		int targetR = Integer.parseInt(st.nextToken());
		
		targetR = N - targetR;
		targetC = targetC - 1;
		
		
		divide(0, 0, N, targetR, targetC, -1);
		
		StringBuilder sb = new StringBuilder();
		for(int a = 0; a < N; a++) {
			for(int b = 0; b < N; b++) {
				sb.append(map[a][b]).append("\t");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
		br.close();
	}
	
	public static void divide(int r, int c, int size, int targetR, int targetC, int value) {
		idx++;
		int curIdx = idx;
		if(size == 2) {
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < 2; j++) {
					if(i == targetR && j == targetC) 	map[r+i][c+j] = value;
					else								map[r+i][c+j] = idx; 
				}
			}
			return;
		}
		int half = size / 2;
		if(0 <= targetR && targetR < half && 0 <= targetC && targetC < half) {
			divide(r, 		c, 		half, targetR, targetC, value);	
		}
		else {
			divide(r, 		c, 		half, half-1, half-1, curIdx);			
		}
		if(0 <= targetR && targetR < half && half <= targetC && targetC < size) {
			divide(r, 		c+half, half, targetR, targetC-half, value);
		}
		else {			
			divide(r, 		c+half, half, half-1, 0		, curIdx);
		}
		if(half <= targetR && targetR < size && 0 <= targetC && targetC < half) {
			divide(r+half, 	c, 		half, targetR-half, targetC, value);
		}
		else {			
			divide(r+half, 	c, 		half, 0,	  half-1, curIdx);
		}
		if(half <= targetR && targetR < size && half <= targetC && targetC < size) {
			divide(r+half, 	c+half, half, targetR-half, targetC-half, value);
		}
		else {			
			divide(r+half, 	c+half, half, 0,	  0,	 curIdx);
		}
		
	}

}
