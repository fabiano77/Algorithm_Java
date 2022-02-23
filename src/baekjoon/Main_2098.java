package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2098 {
	static int N;
	static int[][] graph;
	static int[][] dp;
	static final int inf = (int)1e9;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		dp = new int[N][(1 << N)];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j] == 0) graph[i][j] = inf;
			}
		}
		
		// 시작1, 끝 5, 거쳐가는점 {}
		
		System.out.println(tsp(1, 0));
		

	}
	
	public static int tsp(int visitedFlag, int curVertex) {
		// 모두 방문했다면 다시 처음으로 돌아가고 종료
		if(visitedFlag == (1 << N)-1) {
			return graph[curVertex][0];
		}
		
		if(dp[curVertex][visitedFlag] != 0) return dp[curVertex][visitedFlag];
		dp[curVertex][visitedFlag] = inf;
		
		// 0 ~ N-1 차례대로 반복
		for(int i = 0; i < N; i++) {
			// i 도시가 이미 경로에 있다면 pass
			if((visitedFlag & (1 << i)) > 0) continue;
			// cur 에서 i 로 경로가 없다면 pass
			if(graph[curVertex][i] == inf) continue;
			// 방문하지 않은 i중 가장
			dp[curVertex][visitedFlag] = Math.min(dp[curVertex][visitedFlag], tsp(visitedFlag | (1 << i), i) + graph[curVertex][i]);
		}
		
		return dp[curVertex][visitedFlag];
	}

}
