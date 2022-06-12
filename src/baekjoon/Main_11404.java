package baekjoon;

import java.io.*;
import java.util.*;

public class Main_11404 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int[][] graph = new int[N + 1][N + 1];

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			if(graph[from][to] == 0 || graph[from][to] > dist)
				graph[from][to] = dist;
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(i == j)
						continue;
					if (graph[i][k] != 0 && graph[k][j] != 0) {
						if (graph[i][j] == 0 || graph[i][j] > graph[i][k] + graph[k][j]) {
							graph[i][j] = graph[i][k] + graph[k][j];
						}
					}
				}
			}
		}
		
		for(int i = 1; i <= N; i++) {
			Arrays.stream(Arrays.copyOfRange(graph[i], 1, N+1))
				.forEach(o -> {System.out.print(o+" ");});
			System.out.println();
		}

		br.close();

	}

}
