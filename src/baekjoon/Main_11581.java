package baekjoon;

import java.io.*;
import java.util.*;

public class Main_11581 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		List<List<Integer>> graph = new ArrayList<>();
		graph.add(null);
		int[] inDegree = new int[N + 1];

		for (int i = 1; i < N; i++) {
			graph.add(new ArrayList<>());
			int num = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < num; j++) {
				int to = Integer.parseInt(st.nextToken());
				graph.get(i).add(to);
				inDegree[to]++;
			}
		}
		graph.add(new ArrayList<>());
		
		System.out.println(dfs(graph, 1, new boolean[N+1], new boolean[N+1]) ? "NO CYCLE" : "CYCLE");
	}
	
	public static boolean dfs(List<List<Integer>> graph, int now, boolean[] visited, boolean[] curRoute) {
		if(curRoute[now]) {
			return false;
		}
		curRoute[now] = true;
		for(int to : graph.get(now)) {
			if(!visited[to]) {
				if(!dfs(graph, to, visited, curRoute)) return false;
			}
		}
		curRoute[now] = false;
		visited[now] = true;
		return true;
	}
}
