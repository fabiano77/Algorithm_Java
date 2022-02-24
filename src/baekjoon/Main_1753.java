package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1753 {
	static class Node{
		public int v;
		public int weight;
		public Node(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
		@Override
		public String toString() {
			return "Node [v=" + v + ", weight=" + weight + "]";
		}
		
	}
	static int V;
	static int E;
	static int start;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());
		
		List<List<Node>> graph = new ArrayList<>();
		boolean[] visited = new boolean[V+1]; 
		for(int v = 0; v <= V; v++) {
			graph.add(new ArrayList<>());
		}
		
		for(int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, weight));
		}
		
		int[] weightTable = new int[V+1];
		Arrays.fill(weightTable, Integer.MAX_VALUE);
		
		
		// 시작점 처리
		weightTable[start] = 0;
		
		
		// V개의 점을 추가
		for(int v = 0; v < V; v++) {
			int minNode = getMinIndex(weightTable, visited);
			visited[minNode] = true;
			for(Node node : graph.get(minNode)) {
				weightTable[node.v] = Math.min(weightTable[node.v], weightTable[minNode]+node.weight);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int v = 1; v <= V; v++) {
			if(weightTable[v] == Integer.MAX_VALUE) sb.append("INF");
			else sb.append(weightTable[v]);
			sb.append("\n");
		}
		System.out.print(sb);
		

	}
	
	public static int getMinIndex(int[] arr, boolean[] visited) {
		int minIdx = 0;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < arr.length; i++) {
			if(visited[i]) continue;
			if(arr[i] < min) {
				min = arr[i];
				minIdx = i;
			}
		}
		return minIdx;
	}

}
