package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1752 {

	static class Node {
		private int index;
		private int distance;

		public Node(int index, int distance) {
			this.index = index;
			this.distance = distance;
		}

		public int getIndex() {
			return index;
		}

		public int getDistance() {
			return distance;
		}
	}

	static final int INF = (int) 1e9; // 무한의미의 10억
	static int V;
	static int E;
	static boolean[] visited = new boolean[20001];
	static int[] distance = new int[20001];
	static List<List<Node>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		System.setIn(Main_1752.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		// 그래프 초기화
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		Arrays.fill(distance, INF);

		// 간선 정보 입력받기
		int start = Integer.parseInt(br.readLine());
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			graph.get(s).add(new Node(e, d));
		}
		
		dijkstra(start);
		for(int i = 1; i <= V; i++) {
			if(distance[i] == INF) {
				System.out.println("INF");
			}
			else {
				System.out.println(distance[i]);				
			}
		}

	}

	public static void dijkstra(int start) {
		distance[start] = 0;

		for (int i = 0; i < V; i++) {
			int now = getSmallestNode();
			visited[now] = true;
			for (Node no : graph.get(now)) {
				int index = no.getIndex();
				int cost = no.getDistance();
				if (distance[now] + cost < distance[index]) {
					distance[index] = distance[now] + cost;
				}
			}
		}
	}

	public static int getSmallestNode() {
		int minValue = INF;
		int index = 0;
		for (int i = 1; i <= V; i++) {
			if (distance[i] < minValue && !visited[i]) {
				minValue = distance[i];
				index = i;
			}
		}
		return index;
	}
}
