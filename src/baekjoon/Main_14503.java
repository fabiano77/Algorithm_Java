package baekjoon;

import java.io.*;
import java.util.*;

public class Main_14503 {
	
	static class RoboticCleaner {
		static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		static int[][] map;
		int r, c;	// 좌표
		int d;		// 방향 0:북, 1:동, 2:남, 3:서
		int N, M;
		int cnt;
		boolean[][] cleaned;
		
		public RoboticCleaner(int r, int c, int d, int N, int M) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.N = N;
			this.M = M;
			this.cleaned = new boolean[N][M];
		}
		
		private boolean goBackward() {
			int backD = (d + 2) % 4;
			int nr = r + deltas[backD][0];
			int nc = c + deltas[backD][1];
			// 후진할 위치가 벽인 경우
			if(map[nr][nc] == 1) return false;
			else {				
				// 벽이 아니라면 이동후 true
				r = nr;
				c = nc;
				return true;
			}
		}

		private void rotate() {
			if(--d < 0) d = 3;
		}
		
		private boolean forwardCheck() {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];
			return map[nr][nc] == 0 && !cleaned[nr][nc];
		}
		
		private void goforward() {
			r += deltas[d][0];
			c += deltas[d][1];
		}
		
		public int clean() {
			cnt = 1;
			while(true) {
				// 1.현재 위치 청소
				cleaned[r][c] = true;				
				// 2 현재 위치에서 다음을 반복하면서 인접한 칸을 탐색한다.
				boolean isCleaning = false;				
				for(int i = 0; i < 4; i++) {
					rotate();
					// 빈공간이고 쳥소하지 않았을 경우
					if(forwardCheck()) {
						// 이동한다.
						goforward();
						isCleaning = true;
						break;
					}
				}
				
				// 2.청소할 곳을 못찾은 경우 한 칸 후진한다, (벽이라면 종료한다)
				if(isCleaning) {
					cnt++;
				}
				else{
					boolean checkBack = goBackward();
					if(!checkBack) return cnt;
				}
				
			}
			
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int robotR = Integer.parseInt(st.nextToken());
		int robotC = Integer.parseInt(st.nextToken());
		int robotD = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		

		RoboticCleaner.map = map;
		
		RoboticCleaner roboticCleaner = new RoboticCleaner(robotR, robotC, robotD, N, M);
		int ans = roboticCleaner.clean();
		System.out.println(ans);
		

		
	}
	
}
