package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1719 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// ~ 200
		int M = Integer.parseInt(st.nextToken());	// ~ 10,000
		
		int[][] graph = new int[N+1][N+1];
		int[][] result = new int[N+1][N+1];
		
		for(int i = 0; i <= N; i++) {
			Arrays.fill(graph[i], 1_000_000);
		}
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			graph[a][b] = graph[b][a] = d;
			result[a][b] = b;
			result[b][a] = a;
		}
		
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(graph[i][k] + graph[k][j] < graph[i][j]) {
						graph[i][j] = graph[i][k] + graph[k][j];
						result[i][j] = result[i][k];
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j)
					sb.append('-');
				else {
					sb.append(result[i][j]);
					while(result[i][j] == 0) {
						sb.append(" ");
					}
				}
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		

		br.close();
		

	}

}
