package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1495 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());	// 시작 볼륨
		int M = Integer.parseInt(st.nextToken());	// 볼륨 최대값
		
		boolean[][] possible = new boolean[N+1][M+1];
		possible[0][S] = true;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int dif = Integer.parseInt(st.nextToken());
			for(int vol = 0; vol <= M; vol++) {
				if(possible[i][vol]) {
					if(vol-dif >= 0) possible[i+1][vol-dif] = true;
					if(vol+dif <= M) possible[i+1][vol+dif] = true;
				}
			}
		}
		
		int max = -1;
		for(int vol = 0; vol <= M; vol++) {
			if(possible[N][vol]) {				
				max = Math.max(max, vol);
			}
		}
		System.out.println(max);
		

	}

}
