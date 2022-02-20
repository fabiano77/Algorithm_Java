package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1005 {
	static int T;
	static int N;
	static int K;
	static int[] time;
	static int[] completionTime;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			
			// N, K 입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			// 각 건물의 소요시간 
			st = new StringTokenizer(br.readLine());
			time = new int[N+1];
			completionTime = new int[N+1];
			for(int n = 1; n <= N; n++) {
				completionTime[n] = time[n] = Integer.parseInt(st.nextToken());
			}
			
			// 건물 순서(위상 정렬)를 위한 그래프 선언 및 초기화
			List<List<Integer>> graph = new ArrayList<>();
			int[] inDegree = new int[N+1];	// 진입차수 배열
			for(int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}
			
			// k개의 간선(건축 순서) 입력 받고 진입 차수 기입
			for(int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph.get(from).add(to);
				inDegree[to]++;
			}
			
			// 지으면 이기는 건물 번호
			int win = Integer.parseInt(br.readLine());
			
			// 위상 정렬
			topologySort(graph, inDegree, win);
			
			// 결과 출력
			System.out.println(completionTime[win]);
		}
		

	}
	
	public static void topologySort(List<List<Integer>> graph, int[] inDegree, int win) {		
		// 진입차수가 0인 정점 큐에 추가
		Queue<Integer> q = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			if(inDegree[i] == 0) q.offer(i);
		}
		
		// 큐가 비어있지 않다면
		while(!q.isEmpty()) {
			// 큐에서 정점 하나를 뽑고
			int from = q.poll();
			// 간선을 모두 지우고
			for(int to : graph.get(from)) {
				// 건물의 완성시간은, 현재 짓기시작한 건물의 건축이 끝나야 한다.
				completionTime[to] = Math.max(completionTime[to], time[to]+completionTime[from]);
				// 진입 차수가 0이 된 정점을 큐에 추가한다.
				if(--inDegree[to] == 0) {
					q.offer(to);
				}
			}
					
		}
		
	}

}
