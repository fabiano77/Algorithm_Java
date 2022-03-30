package baekjoon;

import java.io.*;
import java.util.*;

public class Main_17281 {
	static int N;
	static int ans;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][9];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());	
			}
		}
		
		// 아인타는 자신이 가장 좋아하는 선수인 1번 선수를 4번 타자로 미리 결정했다.
		boolean[] isSelected = new boolean[9];
		isSelected[0] = true;
		int[] selected = new int[9];
		selected[3] = 0;
		
		permutation(0, isSelected, selected);

		System.out.println(ans);
		
	}
	
	public static void permutation(int order, boolean[] isSelected,  int[] selected) {
		// 기저조건
		if(order == 9) {
			// 이닝별
			
			int playerIdx = 0;
			int score = 0;
			for(int i = 0; i < N; i++) {
				int outCnt = 0;
				boolean[] base = new boolean[4];
				// 3아웃 될 때까지
				while(outCnt < 3) {
					int player = selected[playerIdx];
					int hit = arr[i][player];
					
					if(hit == 0) outCnt++;
					else {						
						base[0] = true;
						for(int baseIdx = 3; baseIdx >= 0; baseIdx--) {
							if(base[baseIdx]) {
								if(baseIdx + hit >= 4) {
									score++;
								}
								else {
									base[baseIdx + hit] = true;
								}
								base[baseIdx] = false;
							}
							
						}
					}
					
					
					playerIdx = ++playerIdx % 9;
				}
			}
			
			ans = Math.max(ans, score);
			
			return;
		}
		// 4번타자는 1번으로 고정
		if(order == 3) {
			permutation(order + 1, isSelected, selected);
			return;
		}
		// 유도조건
		// 선수 뽑기
		for(int player = 0; player < 9; player++) {
		
			// 이미 고른 선수 패스
			if(isSelected[player]) continue;
			
			isSelected[player] = true;
			selected[order] = player;
			permutation(order + 1, isSelected, selected);
			
			isSelected[player] = false;

		}
		
	}

}
