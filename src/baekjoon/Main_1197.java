package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main_1197 {
	static int V;
	static int E;
	static int[] parent;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		// union-find에서 사용할 부모 노드를 가리킬 배열 
		parent = new int[V+1];
		
		// IntStream을 사용하여 parent 배열을 초기화
		IntStream.range(1, V+1).forEach(i -> parent[i]=i);
		
		// 우선순위 큐 생성후 edge들 모두 삽입
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for(int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(v1, v2, w));
		}
		
		int sumWeight = 0;
		// i-1개의 간선들을 연결하면 모든 정점 연결
		for(int i = 0; i < V-1; i++) {
			Edge e;
			// 가장 가중치가 작은 Edge를 찾는다. 
			do {
				e = pq.poll();
			}while(find(e.v1) == find(e.v2));	// 사이클이 생긴다면 버린다. 
			
			// 연결하고 가중치를 누적한다.
			union(e.v1, e.v2);
			sumWeight += e.weight;
		}
		
		System.out.println(sumWeight);
		
		
	}
	
	static class Edge implements Comparable<Edge>{
		int v1;
		int v2;
		int weight;
		public Edge(int v1, int v2, int weight) {
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int find(int v) {
		// 자신이 루트노드라면
		if(parent[v] == v) return v;
		// 루트노드가 아니라면 재귀적 호출(& path compression)
		return parent[v] = find(parent[v]);
	}
	
	static void union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		// 두 트리가 분리되어있으므로, 작은 루트를 부모로 가리키게한다. 
		if(p1 < p2) parent[p1] = parent[p2];
		else parent[p2] = parent[p1];
	}
	
}
