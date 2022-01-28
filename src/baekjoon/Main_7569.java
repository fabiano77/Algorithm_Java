package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7569 {
	
	static class Point {
		public int x;
		public int y;
		public int z;
		public Point(int z, int x, int y) {
			this.z = z;
			this.x = x;
			this.y = y;
			// TODO Auto-generated constructor stub
		}
	}

	static int M;
	static int N;
	static int H;
	static int[][][] tomatoes;
	static int[][] deltas = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};

	public static void main(String[] args) throws IOException{
		System.setIn(Main_7569.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// M, N, H 순 입력
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		tomatoes = new int[H][N][M];
		
		// 토마토 입력받음
		for(int h = 0; h < H; h++) {
			for(int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int m = 0; m < M; m++) {
					tomatoes[h][n][m] = Integer.parseInt(st.nextToken());
				}
			}
		}

		// bfs 반환결과 출력
		System.out.println(bfs());
		
		br.close();
	}
	
	// 너비 우선 탐색
	static int bfs() {
		int day = 0;
		Queue<Point> q = new LinkedList<>();
		// 최초의 익은 토마토들 queue에 담는다
		for(int h = 0; h < H; h++) {
			for(int n = 0; n < N; n++) {
				for(int m = 0; m < M; m++) {
					if(tomatoes[h][n][m] == 1) {
						q.add(new Point(h, n, m));
					}
				}
			}
		}
		
		// 큐가 비어있지 않으면 반복
		while(!q.isEmpty()) {
			//하나라도 익었으면 유효한 day
			boolean valid = false;
			
			// 현재 큐에 들어있는 원소만 반복(한 칸씩 탐색)
			int cnt = q.size();
			while(cnt-- > 0) {
				Point currentPoint = q.poll();
				for(int[] delta : deltas) {
					int nz = currentPoint.z + delta[0];
					int nx = currentPoint.x + delta[1];
					int ny = currentPoint.y + delta[2];
					if(0 <= nz && nz < H && 0 <= nx && nx < N && 0 <= ny && ny < M) {
						if(tomatoes[nz][nx][ny] == 0) {
							valid = true;
							tomatoes[nz][nx][ny] = 1;
							q.add(new Point(nz, nx, ny));
						}
					}
				}				
			}
			// 다른토마토를 익게했다면 day를 증가시킨다
			if(valid) {
				day++;				
			}
		}
		
		// 모든 토마토가 익었으면 day 반환
		if(allCheck()) {
			return day;			
		}
		// 안 익은 토마토가 있을 경우 -1 반환
		return -1;
	}
	
	// 모든 토마토가 익었느냐?
	static boolean allCheck() {
		for(int h = 0; h < H; h++) {
			for(int n = 0; n < N; n++) {
				for(int m = 0; m < M; m++) {
					if(tomatoes[h][n][m] == 0) {
						return false;
					}
				}
			}
		}
		return true;
	}

}
