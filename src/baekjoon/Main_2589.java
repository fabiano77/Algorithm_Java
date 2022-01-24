package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2589 {
	static int N;
	static int M;
	static char[][] map;
	static int[][] result;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(Main_2589.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input, " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][];

		for (int r = 0; r < N; r++) {
			map[r] = br.readLine().toCharArray();
		}
		int answer = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				int cnt = bfs(r, c);
				if(cnt > answer) answer = cnt;
			}
		}
		System.out.println(answer);
		
		
		br.close();
	}

	public static int bfs(int r, int c) {
		if (map[r][c] == 'W') {
			return 0;
		}
		boolean[][] visited = new boolean[N][M];
		Queue<Integer[]> queue = new LinkedList<>();
		visited[r][c] = true;
		queue.add(new Integer[] { r, c });
		int maxVal = 0;
		int[][] counts = new int[N][M];
		for(int[] row : counts) {
			Arrays.fill(row, N*M);
		}
		counts[r][c] = 0;

		while (!queue.isEmpty()) {
			Integer[] item = queue.poll();
			int x = item[0];
			int y = item[1];
			for (int[] delta : deltas) {
				int nx = x + delta[0];
				int ny = y + delta[1];
				int cnt = counts[x][y];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
					continue;
				}
				if (map[nx][ny] == 'W' || cnt+1 >= counts[nx][ny]) {
					continue;
				}
				queue.add(new Integer[] { nx, ny });
				visited[nx][ny] = true;
				counts[nx][ny] = cnt+1;
				if(cnt+1 > maxVal) maxVal = cnt+1;
			}
		}

		return maxVal;
	}

}
