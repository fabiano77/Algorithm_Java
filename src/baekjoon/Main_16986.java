package baekjoon;

import java.io.*;
import java.util.*;

public class Main_16986 {
	static int N;
	static int K;
	static int[][] match;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 손동작 수
		K = Integer.parseInt(st.nextToken());	// 필요 승 수
		
		match = new int[N][N];
		// 상성에 대한 정보 [i][j] 0이면 i가 지고 1이면 비기고 2일때 i가 이긴다.
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				match[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] info = new int[3][20];
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for(int i = 0; i < 20; i++) {			
			info[1][i] = Integer.parseInt(st1.nextToken()) - 1;	// 경희
			info[2][i] = Integer.parseInt(st2.nextToken()) - 1;	// 민호
		}
		

		int[] scores = new int[3];
		boolean[] isSelected = new boolean[N];
		int[] indexes = new int[3];
		
		boolean possible = play(0, 1, scores, isSelected, indexes, info);
		
		System.out.println(possible ? 1 : 0);
		
	}
	

	
	public static boolean play(int playerA, int playerB, int[] scores, boolean[] isSelected, int[] indexes, int[][] info) {
		// playerA가 playerB보다 항상 더 빠른 번호
		
		for(int player = 0; player < 3; player++) {
			if(scores[player] == K) {
				// 지우가 우승한 경우 -> true 반환하여 탐색 중단
				if(player == 0) return true;
				// 우승 못하면 탐색 반복
				return false;
			}
		}
		
		// 지우가 참가하는 경우
		if(playerA == 0) {
			for(int i = 0; i < N; i++) {
				// 다른 경우의수로 이겨야하므로
				if(isSelected[i]) continue;
				
				int winner = -1;
				int result = match[i][  info[playerB][indexes[playerB]]  ];

				// playerA가 이긴경우
				if(result == 2) winner = playerA;
				// 비겨도 뒤순서가 이기므로 playerB가 이긴 경우
				else 			winner = playerB;
				
				int nextPlayer = 3 - playerB;
				int first = Math.min(winner, nextPlayer);
				int second = Math.max(winner, nextPlayer);
				
				isSelected[i] = true;
				scores[winner]++;
				indexes[playerB]++;
				// 지우가 다른거 내서 우승했다면 true 반환하고 탈출
				if(play(first, second, scores, isSelected, indexes, info)) return true;
				indexes[playerB]--;
				scores[winner]--;
				isSelected[i] = false;
			}
		}
		else {
			int winner = -1;
			int result = match[  info[playerA][indexes[playerA]]  ][  info[playerB][indexes[playerB]]  ];
			// playerA가 이긴경우
			if(result == 2) 	winner = playerA;
			// 비겨도 뒤순서가 이기므로 playerB가 이긴 경우
			else				winner = playerB;
			
			scores[winner]++;
			indexes[playerA]++;
			indexes[playerB]++;
			// 지우가 다른거 내서 우승했다면 true 반환하고 탈출
			if(play(0, winner, scores, isSelected, indexes, info)) return true;
			indexes[playerA]--;
			indexes[playerB]--;
			scores[winner]--;
		}
		
		// false 반환은 탐색을 계속하라는 것
		return false;
	}

}
