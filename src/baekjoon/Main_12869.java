package baekjoon;

import java.io.*;
import java.util.*;

public class Main_12869 {
	static int N;
	static int answer;
	static int[] damages = {9, 3, 1};
	static int[][][] DP = new int[61][61][61];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // ~3 제한
		st = new StringTokenizer(br.readLine());
		
		int[] hp = new int[3];
		for(int i = 0; i < N; i++) {
			hp[i] = Integer.parseInt(st.nextToken());
		}
		
		answer = Integer.MAX_VALUE;
		
		solve(hp[0], hp[1], hp[2], 0);
		
		System.out.println(answer);

	}
	
	public static void solve(int hp1, int hp2, int hp3, int attackCnt) {
		if(hp1 <= 0 && hp2 <= 0 && hp3 <= 0) {
			answer = Math.min(answer, attackCnt);
			return;
		}
		
		/** DP 적용 **/
		hp1 = (hp1 < 0) ? 0 : hp1;
		hp2 = (hp2 < 0) ? 0 : hp2;
		hp3 = (hp3 < 0) ? 0 : hp3;
		if((hp1 != 0 || hp2 != 0 || hp3 != 0)&& DP[hp1][hp2][hp3] != 0 && DP[hp1][hp2][hp3] <= attackCnt) {
			return;
		}
		DP[hp1][hp2][hp3] = attackCnt;
		
		// 한번에 3마리 순서있게 공격하기
		solve(hp1-9, hp2-3, hp3-1, attackCnt+1);
		solve(hp1-9, hp2-1, hp3-3, attackCnt+1);
		solve(hp1-3, hp2-9, hp3-1, attackCnt+1);
		solve(hp1-3, hp2-1, hp3-9, attackCnt+1);
		solve(hp1-1, hp2-9, hp3-3, attackCnt+1);
		solve(hp1-1, hp2-3, hp3-9, attackCnt+1);
		
		
	}

}