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
		
		int[][] arr = new int[3][20];
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for(int i = 0; i < 20; i++) {			
			arr[1][i] = Integer.parseInt(st1.nextToken()) - 1;	// 경희
			arr[2][i] = Integer.parseInt(st2.nextToken()) - 1;	// 민호
		}
		
		// 지우(나) vs 경희 -> 승자 vs 민호 순서.
		

		boolean possible = play(0, 1, new int[3], new boolean[N], new int[3], arr);
		
		System.out.println(possible ? 1 : 0);
		
	}
	

	
	public static boolean play(int playerA, int playerB, int[] scores, boolean[] isSelected, int[] indexes, int[][] arr) {
		
		// playerA가 playerB보다 항상 더 빠른 번호
		//System.out.println("점수 "+Arrays.toString(scores));
		
		
		for(int player = 0; player < 3; player++) {
			if(scores[player] == K) {
				// 다르게 내서 우승 할 수 있는 경우
				if(player == 0) return true;
				return false;
			}
		}
		
		// 지우가 참가하는 경우
		if(playerA == 0) {
			for(int i = 0; i < N; i++) {
				if(isSelected[i]) continue;
				isSelected[i] = true;
				int winner = -1;
				int result = match[i][arr[playerB][indexes[playerB]]];
				if(result == 2) {
					// 지우가 이긴 경우
					winner = playerA;
				}
				else {
					// 지우가 진 경우
					winner = playerB;;
				}
				int nextPlayer = 3 - playerB;
				int first = Math.min(winner, nextPlayer);
				int second = Math.max(winner, nextPlayer);
				scores[winner]++;
				indexes[playerB]++;
				if(play(first, second, scores, isSelected, indexes, arr)) return true;
				indexes[playerB]--;
				scores[winner]--;
				isSelected[i] = false;
			}
		}
		else {
			int winner = -1;
			//System.out.println("pA = "+playerA +", indexs[playerA] = "+indexes[playerA]+", pB = "+playerB+", indexes[playerB] = "+indexes[playerB]);
			int result = match[arr[playerA][indexes[playerA]]][arr[playerB][indexes[playerB]]];
			if(result == 2) {
				// playerA가 이긴경우
				winner = playerA;
			}
			else {
				// 비겨도 뒤순서가 이기므로 playerB가 이긴 경우
				winner = playerB;
			}
			scores[winner]++;
			indexes[playerA]++;
			indexes[playerB]++;
			if(play(0, winner, scores, isSelected, indexes, arr)) return true;
			indexes[playerA]--;
			indexes[playerB]--;
			scores[winner]--;
		}
		
		
		
		
		
		return false;
	}

}
