package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_18500 {
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int R, C;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R+1][];
		for(int r = R; r >= 1; r--) {
			map[r] = br.readLine().toCharArray();
		}
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		boolean left = true;
		
		for(int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());
			
			throwing(map, height, left);
			fall(map);
				
			left = !left;
		}
		
		printMap(map);
		
		br.close();
	}
	
	public static void throwing(char[][] map, int height, boolean left) {
		int c 		= (left) ? 0 	: C-1;
		int delta 	= (left) ? 1	: -1 ;
		
		for(; 0 <= c && c < C; c += delta) { 
			if(map[height][c] == 'x') {
				map[height][c] = '.';
				return;
			}
		}
		
	}
	
	public static void fall(char[][] map) {
		// 땅과 붙어있는 미네랄들을 체크한다.
		boolean[][] ground = new boolean[R+1][C];
		for(int c = 0; c < C; c++) {
			if(map[1][c]=='x' && !ground[1][c]) dfs(map, ground, 1, c);
		}
		
		
		// ground가 false이고 map[r][c]이 'x'이라면 떨어져야 하는 미네랄
		
		
		// 공중에 떠있는 클러스터가 떨어져야할 높이 계산
		int minHeight = R;
		for(int c = 0; c < C; c++) {
			int height = 0;
			for(int r = 1; r <= R; r++) {
				// 땅과 붙어있는 클러스터라면 height를 0으로 초기화
				if(ground[r][c]) {
					height = 0;
				}
				// 땅과 붙어있지 않은데 미네랄이면 떨어져야 한다. 
				else if(map[r][c] == 'x') {
					minHeight = Math.min(minHeight, height);
				}
				// 공기라면 떨어져야할 높이 +1 증가시킨다.
				else {
					height++;
				}
			}
		}
		
		// 떨어져야할 높이가 R일 경우 => 떨어져야할 클러스터가 없을 경우 return
		if(minHeight == R) return;
		
		// 떨어질 클러스터가 있다면 minHeight 만큼 아래로 떨어뜨린다.
		for(int r = 1; r + minHeight <= R; r++) {
			int nr = r + minHeight;
			for(int c = 0; c < C; c++) {
				if(map[nr][c] == 'x' && !ground[nr][c]) {
					map[r][c] = 'x';
					map[nr][c] = '.';
				}
			}
		}
	}
	
	public static void dfs(char[][] map, boolean[][] ground, int r, int c) {
		ground[r][c] = true;
		for(int[] delta : deltas) {
			int nr = r + delta[0];
			int nc = c + delta[1];
			if(1 <= nr && nr < R+1 && 0 <= nc && nc < C && map[nr][nc]=='x' && !ground[nr][nc]) {
				dfs(map, ground, nr, nc);
			}
		}
	}
	
	public static void printMap(char[][] map) {
		StringBuilder output = new StringBuilder();
		for(int r = R; r >= 1; r--) {
			for(int c = 0; c < C; c++) {
				output.append(map[r][c]);
			}
			output.append('\n');
		}
		System.out.println(output);
	}

}
