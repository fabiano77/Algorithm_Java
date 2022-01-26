package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Node {
	
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

public class Main_1916 {
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(Main_1916.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	//정점 최대 1,000개
		M = Integer.parseInt(br.readLine());	//간선 최대 100,000개
		
		// 인접리스트 생성
		List<List<Node>> adjList = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<Node>());
		}
		
		// 버스(간선)의 정보를 입력받음.
		for(int m = 0; m < M; m++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			// 시작점, 끝점, 비용 입력
			adjList.get(start).add(new Node(end, cost));
		}
		
		// 출발 점, 도착 점 입력
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		// 출발 점부터 각 점까지의 거리 배열 초기화
		int[] dist = new int[N+1];
		boolean[] visited = new boolean[N+1];
		for(int i = 1; i <= N; i++) {
			if(i == start) {
				// 자기 자신까지의 거리
				dist[i] = 0; 
			}
			else {
				// 다른 노드 i 와의 거리
				dist[i] = Integer.MAX_VALUE;
			}
		}
		
		// 다익스트라 :
		// 노드의 개수만큼 반복
		for(int i = 0; i < N; i++) {
			int minIndex = 0;
			int minDist = Integer.MAX_VALUE;
			
			// 현재 방문하지 않은 노드중 가장 가까운 점 탐색
			int nodeIndex = findShortestDist(visited, dist);
			
			// 방문한 노드에서 갈 수 있는 경로 확인
			for(Node p: adjList.get(nodeIndex)) {
				int index = p.getIndex();
				int cost = p.getDistance();
				
				// 방문한 노드를 거치는게 더 빠른경우 거리 갱신
				if(dist[nodeIndex]+cost < dist[index]) {
					dist[index] = dist[nodeIndex]+cost;
				}
			}
		}
		// start 노드에서 end 노드까지의 최소비용
		System.out.println(dist[end]);
	}
	
	static int findShortestDist(boolean[] visited, int[]  dist) {
		int minIndex = 0;
		int minDist = Integer.MAX_VALUE;
		
		// 현재 연결되어있는 노드중 가장 가까운 점 탐색
		for(int j = 1; j < N+1;  j++) {
			if(!visited[j] && dist[j] < minDist) {
				minDist = dist[j];
				minIndex = j;
			}
		}
		
		// 방문한 노드 표시
		visited[minIndex] = true;
		return minIndex;
	}

}
