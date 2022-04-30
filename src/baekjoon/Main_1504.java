package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1504 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// ~ 800
		int E = Integer.parseInt(st.nextToken());	// ~ 200,000
		
		int[][] graph = new int[N+1][N+1];
		
		for(int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			graph[b][a] = graph[a][b] = d;
		}
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		// 1에서 N으로 가는데 x와 y를 꼭 거쳐야함. 
		
		int[] distanceFromX = dijkstra(x, graph, N);
		int[] distanceFromY = dijkstra(y, graph, N);
		int distanceXtoY = distanceFromX[y];
		
		
		int temp1 = distanceFromX[1] + Math.min(distanceXtoY + distanceFromY[N], 2*distanceXtoY + distanceFromX[N]);
		int temp2 = distanceFromY[1] + Math.min(distanceXtoY + distanceFromX[N], 2*distanceXtoY + distanceFromY[N]);
		int ans = Math.min(temp1, temp2);
		
		System.out.println((ans >= INF)? -1 : ans);
		

		br.close();
	}
	
	static final int INF = 10_000_000;
	
	static class Edge implements Comparable<Edge> {
		int node, dist;

		public Edge(int node, int dist) {
			super();
			this.node = node;
			this.dist = dist;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(dist, o.dist);
		}
	}
	
	public static int[] dijkstra(int start, int[][] graph, int N) {
		int[] distance = new int[N+1];
		Arrays.fill(distance, INF);
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			if(distance[cur.node] != INF) continue;
			distance[cur.node] = cur.dist;
			
			for(int next = 1; next <= N; next++) {
				if(graph[cur.node][next] == 0) continue;
                if(distance[cur.node] + graph[cur.node][next] >= distance[next]) continue;
				pq.offer(new Edge(next, distance[cur.node] + graph[cur.node][next]));
			}
		}
		
		return distance;
	}

}