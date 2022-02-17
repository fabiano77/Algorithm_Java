package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1987 {
	static int R;
	static int C;
	static int Length = 'Z'-'A'+1;
	static boolean[] visitedAlpha = new boolean[Length];
	static char[][] map;
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int answer;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		for(int r = 0; r < R; r++) map[r] = br.readLine().toCharArray();

		dfs(0, 0, 1);
		
		System.out.println(answer);

	}

	public static void dfs(int r, int c, int cnt) {
		if(visitedAlpha[map[r][c]-'A']) {
			return;
		}
		
		answer = Math.max(answer, cnt);
		
		visitedAlpha[map[r][c]-'A'] = true;
		
		for(int[] delta : deltas) {
			int nr = r + delta[0];
			int nc = c + delta[1];
			if(isIn(nr, nc)) {
				dfs(nr, nc, cnt+1);
			}
		}
		
		visitedAlpha[map[r][c]-'A'] = false;
	}
	
	public static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
	
}
