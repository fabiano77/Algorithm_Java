package baekjoon;

import java.io.*;
import java.util.*;

public class Main_17396 {
	
	static class Node implements Comparable<Node>{
		int no;
		long time;
		public Node(int no, long time) {
			this.no = no;
			this.time = time;
		}
		@Override
		public int compareTo(Node o) {
			return Long.compare(time, o.time);
		}
		@Override
		public String toString() {
			return "Node [no=" + no + ", time=" + time + "]";
		}
		
	}
	
	static int N, M;
	static boolean[] sight;
	static List<List<Node>> graph;
	static boolean[] visited;
	static long[] distance;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sight = new boolean[N];
		visited = new boolean[N];
		distance = new long[N];
		Arrays.fill(distance, Long.MAX_VALUE);
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			if(st.nextToken().charAt(0) == '1') sight[i] = true;
		}
		
		graph = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, time));
			graph.get(to).add(new Node(from, time));
		}
		
		distance[0] = 0;
		dijkstra(0);
		System.out.println(distance[N-1] == Long.MAX_VALUE ? -1 : distance[N-1]);
		
	}
	
	static public void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			
			if(visited[n.no]) continue;
			visited[n.no] = true;

			for(Node n2 : graph.get(n.no)) {
				if(n2.no != N-1 && sight[n2.no]) continue;
				distance[n2.no] = Math.min(distance[n2.no], distance[n.no]+n2.time);
				pq.offer(new Node(n2.no, distance[n2.no]));
			}
		}
	}
}
