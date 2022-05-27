package baekjoon;

import java.io.*;
import java.util.*;

public class Main_11437 {
	
	static int[] parent;
	static int[] depth;
	static boolean[] visited; 
	static List<List<Integer>> graph;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		depth = new int[N+1];
		visited = new boolean[N+1];
		graph = new ArrayList<>();
		
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
		
		dfs(1, 0);
		
		int M = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()); 
			sb.append(lca(a, b)).append("\n");
		}
		
		System.out.println(sb);
		

		br.close();
		

	}

	public static void dfs(int node, int dep){
		visited[node] = true;
		depth[node] = dep;
		for(int child : graph.get(node)) {
			if(visited[child])
				continue;
			parent[child] = node;
			dfs(child, dep+1);
		}
	}
	
	
	public static int lca(int a, int b) {
		while(depth[a] != depth[b]) {
			if(depth[a] > depth[b]) {
				a = parent[a];
			}
			else {
				b = parent[b];
			}
		}
		
		while(a != b) {
			a = parent[a];
			b = parent[b];
		}
		
		
		return a;
	}
}
