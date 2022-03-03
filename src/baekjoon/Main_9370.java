package baekjoon;
import java.io.*;
import java.util.*;

public class Main_9370 {
	
	static class Node implements Comparable<Node>{
		int v;
		int distance;
		public Node(int v, int distance) {
			this.v = v;
			this.distance = distance;
		}
		@Override
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}	
	}
	

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	// 교차로 수 (정점) ~2,000
			int M = Integer.parseInt(st.nextToken());	// 도로 수	 (간선) ~50,000
			int T = Integer.parseInt(st.nextToken());	// 목적지 후보 수   ~100
			
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());	// 출발지
			int G = Integer.parseInt(st.nextToken());	// G-H 사이의 간선을 지남
			int H = Integer.parseInt(st.nextToken());	
			
			List<List<Node>> graph = new ArrayList<>();
			for(int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}
			
			// M개의 도로
			for(int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int dist = Integer.parseInt(st.nextToken());
				graph.get(from).add(new Node(to, dist));
				graph.get(to).add(new Node(from, dist));
			}
			
			
			// 목적지 후보
			int[] destinations = new int[T];
			for(int t = 0; t < T; t++) 	
				destinations[t] = Integer.parseInt(br.readLine());
			// 목적지 후보 오름차순 정렬
			Arrays.sort(destinations);
			
			// 시작 정점 S로부터의 최소 거리 배열
			int[] distanceFromS = dijkstra(graph, S);
			
			// 꼭 거쳐야하는 경유지중 더 먼점을 구함
			int start2 = -1;
			if(distanceFromS[G] > distanceFromS[H]) start2 = G;
			else 									start2 = H;
			
			// 경유지로부터의 최소 거리 배열
			int[] distanceFromStart2 = dijkstra(graph, start2);
			
			// 목적지 후보중 경유지를 거치는 경우
			for(int t = 0; t < T; t++) {
				int d = destinations[t];
				// 출발정점으로부터의 거리와 출발점->경유지->목적지 거리가 같은 경우 경유지를 경유했다고 볼 수 있음.
				if(distanceFromS[d] == distanceFromS[start2]+distanceFromStart2[d]) {
					sb.append(d).append(" ");
				}
			}
			
			

			sb.append("\n");
		}

		System.out.print(sb);
		br.close();
	}
	
	// 다익스트라 알고리즘
	static int[] dijkstra(List<List<Node>> graph, int start) {
		
		// 최단거리 테이블
		int[] distances = new int[graph.size()];

		// Arrays.fill(distances, Integer.MAX_VALUE);
		Arrays.fill(distances, 100_000_000);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[graph.size()];

		// 다익스트라
		pq.offer(new Node(start, 0));
		distances[start] = 0;

		while (!pq.isEmpty()) {
			Node from = pq.poll();
			
			if (visited[from.v]) continue;
			visited[from.v] = true;

			// start로부터 현재 가장 거리가 짧았던 점에 연결된 간선들을 추가
			for (Node to : graph.get(from.v)) {
				int cost = distances[from.v] + to.distance;
				if (cost <= distances[to.v]) {
					distances[to.v] = cost;
					pq.offer(new Node(to.v, cost));
				}
			}
		}
		
		return distances;
	}
	

}