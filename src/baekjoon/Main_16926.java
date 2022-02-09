package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16926 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int M;
	static int R;
	static String[][] arr;
	static int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	

	public static void main(String[] args) throws IOException{
		System.setIn(Main_16926.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new String[N][M];
		for(int i = 0; i < N; i++)	{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = st.nextToken();	
			}
		}
		
		int limit = Math.min(N/2, M/2);
		for(int i = 0; i < limit; i++) {
			for(int cnt = 0; cnt < R; cnt++) {
				int r = i;
				int c = i;
				String temp = arr[r][c];
				for(int[] delta : deltas) {
					while(0+i <= r+delta[0] && r+delta[0] < N-i && 0+i <= c+delta[1] && c+delta[1] < M-i) {
						arr[r][c] = arr[r+delta[0]][c+delta[1]];
						r = r+delta[0];
						c = c+delta[1];
					}				
				}
				arr[r+1][c] = temp;
				
			}
		}
		for(String[] row : arr) {
			for(String item : row) {
				sb.append(item).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		

	}

}
