package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1956 {
	

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[][] graph = new int[V+1][V+1];
		
		final int INF = 100_000_000;
		
		for(int i = 0; i <= V; i++) {
			Arrays.fill(graph[i], INF);
		}
		
		for(int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			graph[a][b] = d;
		}
		
		for(int i = 1; i <= V; i++) {
			for(int j = 1; j <= V; j++) {
				for(int k = 1; k <= V; k++) {
					if(i == j) continue;
					
					if(graph[i][j] > graph[i][k] + graph[k][j]) {
						graph[i][j] = graph[i][k] + graph[k][j];
					}
				}
			}
		}

		int ans = INF;
		
		for(int i = 1; i <= V; i++) {
			for(int j = 1; j <= V; j++) {
				if(i == j) {
					continue;
				}
				
				if(graph[i][j] != INF && graph[j][i] != INF) {
					ans = Math.min(ans, graph[i][j] + graph[j][i]);
				}
			}
		}
		
		System.out.println((ans == INF)? -1 : ans);
		
		
		br.close();
	}

}
