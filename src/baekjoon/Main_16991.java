package baekjoon;

import java.io.*;
import java.util.*;

public class Main_16991 {
	static class Point {
		public int x;
		public int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		public double getDistance(Point o) {
			return Math.sqrt(Math.pow((this.x - o.x), 2) + Math.pow((this.y - o.y), 2)); 
		}
	}
	static int N;
	static double[][] graph;
	static double[][] dp;
	static final int inf = (int)1e9;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		Point[] cities = new Point[N]; 
		graph = new double[N][N];
		dp = new double[N][(1 << N)];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cities[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				graph[i][j] = graph[j][i] = cities[i].getDistance(cities[j]);
				
			}
		}
		
		// 시작1, 끝 5, 거쳐가는점 {}
		
		System.out.println(tsp(1, 0));
		

	}
	
	public static double tsp(int visitedFlag, int curVertex) {
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
