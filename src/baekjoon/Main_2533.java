package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2533 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N+1][2];
		boolean[] visited = new boolean[N+1];
		
		
		List<List<Integer>> graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		
		
		dfsPostOrder(graph, 1, visited, dp);
		System.out.println(Math.min(dp[1][0], dp[1][1]));
		br.close();
	}
	
	public static void dfsPostOrder(List<List<Integer>> graph, int cur, boolean[] visited, int[][] dp) {
		visited[cur] = true;
		
		for(int child : graph.get(cur)) {
			if(visited[child]) continue;
			dfsPostOrder(graph, child, visited, dp);
			dp[cur][0] += dp[child][1];
			dp[cur][1] += Math.min(dp[child][0], dp[child][1]);
		}
		// 자신을 얼리어답터로 만든다
		dp[cur][1]++;
		
		
	}

}
