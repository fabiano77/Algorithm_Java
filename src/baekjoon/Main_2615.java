package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2615 {
	static int N = 20;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 0포함 20x20크기의 바둑판 생성
		int [][] table = new int[N][N];
		for(int r = 1; r < N; r++) {
			// 입력을 한 줄씩 받아 공백으로 쪼갠다.
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int c = 1; c < N; c++) {
				table[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 8방 탐색 중, 왼쪽의 점부터 시작하는 4개의 방향 설정
		int [][] dirs = {{1, 0}, {1, 1}, {0, 1}, {-1, 1}};
		
		// 모든 점에 대해 탐색 좌표계는 1~19, 1~19
		// 좌측 줄부터 탐색하기 위해 column, row 순서로 탐색
		for(int c = 1; c < N; c++) {
			for(int r = 1; r < N; r++) {
				// 돌이 놓이지 않았을 경우 스킵
				if(table[r][c] == 0) continue;
				// 현재 돌의 색 저장
				int currentItem = table[r][c];
				
				// 4개의 방향에 대해 탐색
				for(int[] dir : dirs) {
					// 현재 돌부터 시작하여 개수세기
					int cnt = 1;
					// 정방향 탐색
					int currentR = r;
					int currentC = c;
					while(true) {
						int nextR = currentR+dir[0];
						int nextC = currentC+dir[1];
						// 바둑판을 넘어간 경우 탐색 중단
						if(nextR < 1 || nextR > 19 || nextC < 1 || nextC > 19) {
							break;
						}
						// 같은 색의 돌일 경우 숫자를 세고 그쪽 방향으로 탐색
						if(table[nextR][nextC] == currentItem) {
							cnt++;
							currentR = nextR;
							currentC = nextC;
						}
						// 다른 색의 돌일 경우 탐색 중단
						else {
							break;
						}
	
					}
					// 바둑알 5개 카운트시 역방향 탐색(6개 바둑알 방지)
					int currentR_2 = r;
					int currentC_2 = c;
					while(cnt == 5) {
						int nextR = currentR_2-dir[0];
						int nextC = currentC_2-dir[1];
						// 바둑판을 넘어간 경우 탐색 중단
						if(nextR < 1 || nextR > 19 || nextC < 1 || nextC > 19) {
							break;
						}
						// 같은 색의 돌일 경우 숫자를 세고 그쪽 방향으로 탐색
						if(table[nextR][nextC] == currentItem) {
							cnt++;
							currentR_2 = nextR;
							currentC_2 = nextC;
						}
						// 다른 색의 돌일 경우 탐색 중단
						else {
							break;
						}
	
					}
					// 연속되어있는 돌의 개수가 5개일 때만 출력하고 프로그램을 끝냄.
					if(cnt == 5) {
						System.out.println(currentItem);
						System.out.println(r+" "+c);
						return;
					}
					
				}
				
			}
		}
		
		// 승자가 나오지 않았을 경우 0을 출력하고 종료.
		System.out.println(0);
	}

}
