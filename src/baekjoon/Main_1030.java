package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1030 {
	static int s, N, K, R1, R2, C1, C2;
	static int white;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		s = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R1 = Integer.parseInt(st.nextToken());
		R2 = Integer.parseInt(st.nextToken());
		C1 = Integer.parseInt(st.nextToken());
		C2 = Integer.parseInt(st.nextToken());

		white = (N-K)/2;
		
		StringBuilder sb = new StringBuilder();
		
		for(int r = R1; r <= R2; r++) {
			for(int c = C1; c <= C2; c++) {
				sb.append(solve(s, r, c));
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int solve(int s, int r, int c) {
		if(s == 0) return 0;
		// 현재 N^s x N^s 크기
		
		// 이전 s에서 1(검정색)일 경우
		int prevR = r/N;
		int prevC = c/N;
		if(solve(s-1, prevR, prevC) == 1) {
			return 1;
		}
		else {
			int curR = r%N;
			int curC = c%N;
			if(white <= curR && curR < N-white && white <= curC && curC < N-white) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}

}
