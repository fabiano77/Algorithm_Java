package baekjoon;

import java.io.*;
import java.util.*;

public class Main_18808 {
	static int N, M, K;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[][] notebook = new int[N][M];
		int[][][] stickers = new int[K][][];
		
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			stickers[k] = new int[R][C];
			
			for(int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < C; c++) {
					stickers[k][r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
		}
		
		
		nextSticker : for(int k = 0; k < K; k++) {
			
			// 90도씩 회전 4방향
			for(int d = 0; d < 4; d++) {
				int R = stickers[k].length;
				int C = stickers[k][0].length;
				
				// 스티커를 붙일 수 있는 위치 반환
				Point point = check(notebook, stickers[k]);
				
				// 널이 아니면 붙일 수 있다는 의미
				if(point != null) {
					// 스티커를 붙인다
					attach(notebook, point, stickers[k]);
					// 다음 스티커로 이동
					continue nextSticker;
				}
				else {					
					// 회전한다
					stickers[k] = rotate(stickers[k]);
				}
			}
		}
		
		// 노르북에 스티커 붙여진 칸수 세기
		int cnt = 0;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(notebook[r][c] == 1) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
		
	}
	static class Point{
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static int[][] rotate(int[][] sticker){
		int R = sticker.length;
		int C = sticker[0].length;
		int[][] rotated = new int[C][R];
		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				rotated[c][(R-1)-r] = sticker[r][c];
			}
		}
		
		return rotated;
	}
	
	public static Point check(int[][] notebook, int[][] sticker) {
		int R = sticker.length;
		int C = sticker[0].length;
		
		// startR, startC 를 기준으로 스티커가 붙여지는지 확인
		for(int startR = 0; startR <= N - R; startR++) {
			next : for(int startC = 0; startC <= M - C; startC++) {

				// 붙여본다
				for(int r = 0; r < R; r++) {
					for(int c = 0; c < C; c++) {
						// 안붙여진다.
						if(sticker[r][c] == 1 && notebook[r+startR][c+startC] == 1) {
							continue next;
						}
					}
				}
				// 붙여진다
				return new Point(startR, startC);
				
			}
		}
		
		return null;
	}
	
	public static void attach(int[][] notebook, Point point, int[][] sticker) {
		int R = sticker.length;
		int C = sticker[0].length;
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(sticker[r][c] == 1) notebook[r+point.r][c+point.c] = 1;
			}
		}
	}

}
