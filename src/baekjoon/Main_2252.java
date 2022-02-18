package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2252 {
	static int N;
	static int M;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 학생 수(정점 수)
		N = Integer.parseInt(st.nextToken());
		
		// 비교 수(간선 수)
		M = Integer.parseInt(st.nextToken());
		
		// 진입 차수를 기입할 배열
		int[] inDegree = new int[N+1];
		
		// 인접 리스트 그래프 초기화
		List<List<Integer>> graph = new ArrayList<>();
		for(int n = 0; n <= N; n++) {
			graph.add(new ArrayList<>());
		}
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			// 키 : from < to
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// a에서 b를 가리키는 간선 추가
			graph.get(from).add(to);
			// b의 진입차수 증가
			inDegree[to]++;
		}
		
		// 위상 정렬
		topologySort(graph, inDegree);
		
		// 결과 출력
		System.out.println(sb);
		
	}
	
	private static void topologySort(List<List<Integer>> graph, int[] inDegree) {
		Queue<Integer> q = new LinkedList<>();
		// 모든 정점중에
		for(int i = 1; i <= N; i++) {
			// 진입차수가 0인 정점들 큐에 추가
			if(inDegree[i] == 0) {
				q.offer(i);
			}
		}
		
		// 큐가 비어있지 않다면
		while(!q.isEmpty()) {
			// 큐에서 정점을 하나 꺼낸다
			int from = q.poll();
			// from 정점에서 엣지를 삭제하고, to 정점에대해 진입차수를 감소시킨다.
			for(int to : graph.get(from)) {
				// to 정점의 진입차수가 0이라면 큐에 추가한다.
				if(--inDegree[to] == 0) {
					q.offer(to);
				}
			}
			sb.append(from).append(" ");
		}
	}

}











