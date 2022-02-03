package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1103 {
	static final int INF = (int)1e9;
	static final int H = 24;
	static final int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; 
	static int N;
	static int M;
	static int[][] board;
	static int[][] dp;
	static boolean[][] visited;
	static int answer;

	public static void main(String[] args) throws IOException{
		System.setIn(Main_1103.class.getResourceAsStream("input2.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// board정보와 dp, visited 초기화
		board = new int[N][];
		dp = new int[N][M];
		visited = new boolean[N][M];
		
		// 한 행씩 board 정보 입력받기
		for(int i = 0; i < N; i++) {
			board[i] = in.readLine()	// String[]
						.chars()		// IntStream(char)
						.map(c -> c-'0')// IntStream(int)
						.toArray();		// int[]
		}
		
		dfs(0, 0, 1);

		if(answer == INF) {
			System.out.println(-1);
		}
		else {
			System.out.println(answer);
		}
	}
	
	static void dfs(int r, int c, int cnt) {
		
		// 현재 경로를 알기위한 방문처리
		visited[r][c] = true;

		// 사방 탐색 DFS
		for(int[] delta : deltas) {
			int nr = r + delta[0]*board[r][c];
			int nc = c + delta[1]*board[r][c];
			// 보드의 바깥이거나 구멍이거나
			if(nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr][nc] == H) {
				continue;
			}
			// 현재 경로에서 방문한 점을 재 방문 할 경우 
			if(visited[nr][nc] == true) {
				dp[nr][nc] = INF;
				answer = INF;
				continue;
			}
			// 이미 다른 경로에서 더 길게 방문했을 경우 dp 갱신하지 않도록 스킵
			if(dp[nr][nc] > cnt) {
				continue;
			}
			// 처음 방문하는 경우		

			dfs(nr, nc, cnt+1);
		}
		
		// 현재 경로 말고 다른 경로로 방문할 수도 있으므로 방문 해제
		visited[r][c] = false;
		
		// 자기의 cnt를 기록한다.
		dp[r][c] = cnt;
		
		// 
		answer = Math.max(answer, cnt);
	}

}
