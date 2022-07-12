package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2174 {
	
	static class Robot {
		static String state = "OK";
		static boolean end;
		static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		static Robot[][] map;
		int no;
		int r, c;
		int dir;
		public Robot(int no, int dir, int r, int c) {
			r--; c--;
			this.no = no;
			this.r = r;
			this.c = c;
			this.dir = dir;
			map[r][c] = this;
		}
		
		private boolean isIn(int r, int c) {
			return 0 <= r && r < map.length && 0 <= c && c < map[0].length;
		}
		
		public void move(int cnt) {
			while(cnt-- > 0) {
				int nr = r + deltas[dir][0];
				int nc = c + deltas[dir][1];
				if(!isIn(nr, nc)) {
					Robot.end = true;
					state = String.format("Robot %d crashes into the wall", no);
					return;
				}
				else if(map[nr][nc] != null) {
					Robot.end = true;
					state = String.format("Robot %d crashes into robot %d", no, map[nr][nc].no);
					return;
				}
				map[r][c] = null;
				map[nr][nc] = this;
				r = nr;
				c = nc;
				
			}
		}
		
		public void rotate(boolean right, int cnt) {
			if(!right) 	dir = (dir+cnt)%4;
			else		dir = (dir+100-cnt)%4;
		}
		
		
		
	}

	static String dirs = "SENW"; 
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		Robot.map = new Robot[R][C];
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Robot[] robots = new Robot[N+1];
		// 로봇 정보
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int dir = dirs.indexOf(st.nextToken());
			
			robots[i] = new Robot(i, dir, r, c);
		}
		
		// 명령 정보
		for(int i = 0; i < M && !Robot.end; i++) {
			st = new StringTokenizer(br.readLine());
			int no = Integer.parseInt(st.nextToken());
			char type = st.nextToken().charAt(0);
			int cnt = Integer.parseInt(st.nextToken());
			
			if(type == 'F') {
				robots[no].move(cnt);
			}
			else {
				robots[no].rotate(type=='R', cnt);
			}
		}
		
		System.out.println(Robot.state);
		

		br.close();
		

	}

}
