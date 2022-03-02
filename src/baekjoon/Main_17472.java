package baekjoon;

import java.io.*;
import java.util.*;

public class Main_17472 {
	static int N, M;
	static int[][] map;
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int islandNum = 0;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬 번호 기록, 섬 갯수 조사 
		searchIsland(map);
		
		
		// 놓을 수 있는 다리들
		List<Edge> edges = makeBridge(map);
		
		int min = mst(edges);
		
		System.out.println(min);
		

	}
	
	// 간선 클래스
	static class Edge implements Comparable<Edge>{
		public int v1, v2;
		public int distance;
		public Edge(int v1, int v2, int distance) {
			this.v1 = v1;
			this.v2 = v2;
			this.distance = distance;
		}
		@Override
		public int compareTo(Edge o) {
			return this.distance - o.distance;
		}

		
	}

	// 좌표가 지도 안에 있는지 논리값 반환
	public static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
	// 맵에 존재하는 점의 개수마다 번호를 매기고 수를 센다.
	public static void searchIsland(int[][] map) {
		boolean[][] visited = new boolean[N][M];
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] == 1 && !visited[r][c]) {
					dfs(map, visited, r, c, ++islandNum);
				}
			}
		}
	}
	
	// dfs
	public static void dfs(int[][] map, boolean[][] visited, int r, int c, int number) {
		map[r][c] = number;
		visited[r][c] = true;
		for(int[] delta : deltas) {
			int nr = r + delta[0];
			int nc = c + delta[1];
			if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
				dfs(map, visited, nr, nc, number);
			}
		}
	}
	
	// 섬 사이에 다리를 놓을 수 있는 모든 경우를 리스트로 반환
	public static List<Edge> makeBridge(int[][] map) {
		List<Edge> edgeList = new ArrayList<>();
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				
				// 섬이라면
				if(map[r][c] != 0) {
					// 4방향에 대해
					for(int[] delta : deltas) {
						
						// 바다와 인접한 방향이라면
						if(isIn(r+delta[0], c+delta[1]) && map[r+delta[0]][c+delta[1]] == 0) {
							// 다리를 놓아본다
							for(int dist = 1; ;dist++) {
								int nr = r + delta[0]*(dist+1);
								int nc = c + delta[1]*(dist+1);
								
								// 맵밖이거나 || 자기자신 섬일 경우
								if(!isIn(nr, nc) || map[nr][nc] == map[r][c]) break;
								
								// 다른 섬일 경우 
								if(map[nr][nc] != 0 && map[nr][nc] != map[r][c]) {
									
									// 길이가 1인 다리라면 이 방향 불가
									if(dist == 1) break;
									
									int from = map[r][c];
									int to = map[nr][nc];
									// 간선 저장
									edgeList.add(new Edge(from, to, dist));
									break;
								}
							}
						}
					}
				}
			}
		}
		return edgeList;
	}

	// 최소 신장 트리를 만든다.
	public static int mst(List<Edge> edges) {
		int[] parent = new int[islandNum+1];
		for(int i = 1; i <= islandNum; i++) {
			parent[i] = i;
		}
		PriorityQueue<Edge> pq = new PriorityQueue<>(edges);
		int cost = 0;
		
		// 정점-1 개만큼의 edge를 뽑는다.
		int cnt = 0;
		while(!pq.isEmpty() && cnt < islandNum-1) {
			Edge edge = pq.poll();
			if(union(parent, edge.v1, edge.v2)) {
				cnt++;
				cost += edge.distance;
			}
		}
		if(cnt < islandNum-1) return -1;
		return cost;
	}
	
	// 유니온파인드
	public static int find(int[] parent, int num) {
		if(parent[num] == num) {
			return num;			
		}
		return parent[num] = find(parent, parent[num]);
	}
	
	public static boolean union(int[] parent, int a, int b) {
		int parentA = find(parent, a);
		int parentB = find(parent, b);
		if(parentA == parentB) return false;
		if(parentA < parentB) {
			parent[parentB] = parentA;
		}
		else {
			parent[parentA] = parentB;
		}
		return true;
	}
	
	
}
