package baekjoon;

import java.io.*;
import java.util.*;

public class Main_10282 {
	static class Edge{
		int v, d;

		public Edge(int v, int d) {
			this.v = v;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			List<List<Edge>> graph = new ArrayList<>(N+1);
			for(int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}
				
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				// b가 감염되면 s초 후 a도 감염됨
				int to = Integer.parseInt(st.nextToken());
				int from = Integer.parseInt(st.nextToken());
				int dist = Integer.parseInt(st.nextToken());
				graph.get(from).add(new Edge(to, dist));
			}
			
			int[] distance = dijkstra(graph, start);
			
			int cnt = 0;
			int max = 0;
			for(int i = 1; i <= N; i++) {
				if(distance[i] == INF) continue;
				cnt++;
				max = Math.max(max, distance[i]);
			}
			System.out.println(cnt+" "+max);
		}
		
		br.close();
	}
	final static int INF = 100000000;
	
	public static int[] dijkstra(List<List<Edge>> graph, int start) {
		int[] distance = new int[graph.size()];
		boolean[] visited = new boolean[graph.size()];
		Arrays.fill(distance, INF);
		distance[start] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2)->Integer.compare(e1.d, e2.d));
		for(Edge e : graph.get(start)) {			
			pq.offer(e);
		}
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			if(visited[cur.v]) continue;
			visited[cur.v] = true;
			distance[cur.v] = cur.d; 
			for(Edge e : graph.get(cur.v)) {
				if(!visited[e.v]) {
					pq.offer(new Edge(e.v, distance[cur.v]+e.d));
				}
			}
		}
		
		return distance;
	}

}
