package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1167 {
	
	static class Edge{
		public int v;
		public int dist;
		public Edge(int v, int dist) {
			this.v = v;
			this.dist = dist;
		}
		@Override
		public String toString() {
			return "Edge [v=" + v + ", dist=" + dist + "]";
		}
		
	}
	
	static int N;
	static boolean[] visited;
	static int lastNode;
	static int distance;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N+1];
		
		List<List<Edge>> graph = new ArrayList<>();
		
		// graph의 인덱스를 채움
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Edge>());			
		}
		
		// N개의 트리 정점에 관한 정보 입력
		for(int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			int curNode = Integer.parseInt(st.nextToken()); 
			while(true) {
				int v = Integer.parseInt(st.nextToken());
				if(v == -1) break;
				int dist = Integer.parseInt(st.nextToken());
				graph.get(curNode).add(new Edge(v, dist));
			}
		}
		
		
		// 임의의 점에서 가장 길이가 노드까지 탐색, 경로가 가장 길때 단말 노드를 기억한다
		distance = 0;
		dfs(graph, 1, 0);
		
		// 가장 멀었던 단말노드에서 다시 dfs실행
		distance = 0;
		dfs(graph, lastNode, 0);
		
		System.out.println(distance);
		
		
	}
	
	private static void dfs(List<List<Edge>> graph, int node, int dist) {
		// 출발지점이 아닌 단말노드면 종료
		if(dist != 0 && graph.get(node).size() == 1) {
			
			// 현재 경로의 겨리가 최대거리일 경우
			if(distance < dist) {
				// 마지막노드와 거리를 기억한다.
				lastNode = node;
				distance = dist;
			}
			return;
		}
		
		visited[node] = true;
		for(Edge edges : graph.get(node)) {
			if(!visited[edges.v]) dfs(graph, edges.v, dist+edges.dist);
		}
		visited[node] = false; 
	}

}
