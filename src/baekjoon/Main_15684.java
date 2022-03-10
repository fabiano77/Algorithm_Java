package baekjoon;

import java.io.*;
import java.util.*;

public class Main_15684 {
	static int N, M, H;
	static int[] colSum;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 세로선의 개수
		M = Integer.parseInt(st.nextToken());	// 존재하는 가로선의 개수
		H = Integer.parseInt(st.nextToken());	// 가로선 높을 수 있는 위치 개수
		colSum = new int[N+1];

		int[][] ladder = new int[H][N+1];
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	// 높이(가로줄)
			int b = Integer.parseInt(st.nextToken());	// 세로줄
			ladder[a-1][b] = 1;
			colSum[b]++;	// 각 열마다 놓아진 가로선의 개수
		}
		
		// 추가하는 가로선 개수
		for(int addCnt = 0; addCnt <= 3; addCnt++) {
			
			// addCnt개 추가해서 모두 자기자신으로 돌아와진다면,
			if(makeCombination(addCnt, ladder, 0, 1)) {
				System.out.println(addCnt);
				return;
			}
		}
		System.out.println(-1);

	}
	
	public static boolean makeCombination(int toChoose, int[][] ladder, int r, int c) {
		if(toChoose == 0) {
			for(int col = 1; col < N; col++) {
				if(colSum[col] == 1) {
					return false;
				}
			}
			return simulate(ladder);
		}
		


		for( ; r < H; c++) {
			if(c == N) {
				c = 0;
				r++;
				continue;
			}

			// 사다리의 가로선이 이미 있을 경우
			if(ladder[r][c] == 1 || ladder[r][c-1] == 1 || ladder[r][c+1] == 1) continue;
			ladder[r][c] = 1;
			colSum[c]++;
			if(makeCombination(toChoose-1, ladder, r, c+1)) {
				return true;
			}
			colSum[c]--;
			ladder[r][c] = 0;
		}
		
		return false;
	}
	
	public static boolean simulate(int[][] ladder) {
		
		// 시작 세로선 번호
		for(int startC = 1; startC <= N; startC++) {
			// 출발 세로선 번호 기억
			int currentC = startC;
			// 위에서부터 사다리를 탄다
			for(int r = 0; r < H; r++) {
				// 왼쪽으로 이어져있을 경우 세로선 -1
				if(ladder[r][currentC-1] == 1) {
					currentC--;
				}
				// 오른쪽으로 이어져있을 경우 세로선 +1
				else if(ladder[r][currentC] == 1) {
					currentC++;
				}
			}
			// 사다리를 탄 이후 출발 세로선과 현재 세로선이 같지 않다면
			if(currentC != startC) {
				return false;
			}
		}
		
		// 모든 세로선이 자기자신으로 돌아올경우
		return true;
	}

}
