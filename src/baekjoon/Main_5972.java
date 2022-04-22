package baekjoon;

import java.io.*;
import java.util.*;

public class Main_5972 {
	static class Edge implements Comparable<Edge>{
		public int node, dist;

		public Edge(int node, int dist) {
			super();
			this.node = node;
			this.dist = dist;
		}
		@Override
		public int compareTo(Edge o) {
			return dist - o.dist;
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1]; 
		List<List<int[]>> graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			graph.get(a).add(new int[]{b, d});
			graph.get(b).add(new int[]{a, d});
		}
		
		pq.offer(new Edge(1, 0));
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			if(cur.node == N) {
				System.out.println(cur.dist);
				break;
			}
			visited[cur.node] = true;
			for(int[] info : graph.get(cur.node)) {
				if(visited[info[0]]) continue;
				pq.offer(new Edge(info[0], cur.dist+info[1]));
			}
		}
		
		
		br.close();
		

	}

}
