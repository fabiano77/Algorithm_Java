package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_18428 {
	static int N;
	static String[][] map;
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static boolean avoid;
	static boolean clear;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new String[N][];
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().split(" ");
		}

		// 장애물 설치
		outer: for(int x = 0; x < N; x++) {
			for(int y = 0; y < N; y++) {
				installWatch(x, y, 1);
				if(clear) break outer;
			}
		}
		

		
		// 감시 피했음
		if(clear) System.out.println("YES");
		else System.out.println("NO");
		

	}
	
	static void printMap() {
		for(String[] row : map) System.out.println(Arrays.toString(row));
		System.out.println();
	}
	
	static void installWatch(int startR, int startC, int cnt) {
		//System.out.println("startR = "+startR + ", startC = "+startC + ", cnt = "+cnt);
		// 장애물 설치할 위치 아니라면 종료
		if(!map[startR][startC].equals("X") || clear) {
			return;
		}
		
		// 장애물 설치
		map[startR][startC] = "O";
		
		// 장애물 3개 설치했다면
		if(cnt == 3) {
			if(!watch()) clear = true;
			map[startR][startC] = "X";
			return;
		}else {
			for(int i = startR; i < N; i++) {
				int startColumn = (i == startR) ? startC + 1: 0;
				for(int j = startColumn; j < N; j++) {
					installWatch(i, j, cnt+1);
				}
			}
			
			// 장애물 취소
			map[startR][startC] = "X";
			
			return;			
		}
		
				
	}
	
	// 감시 당하면 true 반환
	static boolean watch() {
		// 선생이 감시하기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j].equals("T")) {
					// 감시
					int nr, nc;
					for(int d = 0; d < 4; d++) {
						for(int dist = 1; dist <= N; dist++) {
							nr = i+deltas[d][0]*dist;
							nc = j+deltas[d][1]*dist;
							
							// 범위 벗어나면 현재방향 종료
							if(!isIn(nr,nc)) break;
							
							// 장애물에 막힘
							if(map[nr][nc].equals("O")) {
								break;			//현재방향 종료
							}
							// 적발시
							else if(map[nr][nc].equals("S")) {
								return true;
							}
						}
					}
					
				}
			}
		}
		return false;
	}
	
	static boolean isIn(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N);
	}
}
