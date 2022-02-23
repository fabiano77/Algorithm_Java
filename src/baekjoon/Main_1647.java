package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1647 {
	
	static class Edge implements Comparable<Edge>{
		public int v1;
		public int v2;
		public int cost;
		public Edge(int v1, int v2, int cost) {
			super();
			this.v1 = v1;
			this.v2 = v2;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
		@Override
		public String toString() {
			return "Edge [v1=" + v1 + ", v2=" + v2 + ", cost=" + cost + "]";
		}
		
	}
	
	static int N;
	static int M;
	static int[] parent;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(v1, v2, c));
		}
		
		int edgeCnt = 0;
		int total = 0;
		int maxVal = 0;
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if(union(e.v1, e.v2)) {
				edgeCnt++;
				total += e.cost;
				maxVal = Math.max(maxVal, e.cost);
			}
			if(edgeCnt == N-1) break;
			
		}
		
		System.out.println(total - maxVal);

	}
	
	public static int find(int a) {
		if(a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
	public static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa == pb) return false;
		if(pa < pb) parent[pb] = pa;
		else parent[pa] = pb;
		return true;
	}

}
