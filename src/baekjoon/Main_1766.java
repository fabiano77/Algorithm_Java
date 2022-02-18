package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1766 {
	static int N;
	static int M;
	static int[] inDegree;
	static List<List<Integer>> graph;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		inDegree = new int[N+1];
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			inDegree[to]++;
			graph.get(from).add(to);
		}
		
		priorityTopologySort();
		
		System.out.println(sb);
	}
	
	static void priorityTopologySort() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 1; i <= N; i++) {
			if(inDegree[i] == 0) pq.offer(i);
		}
		
		while(!pq.isEmpty()) {
			int from = pq.poll();
			for(int to : graph.get(from)) {
				if(--inDegree[to] == 0) {
					pq.offer(to);
				}
			}
			sb.append(from).append(" ");
			
		}
		
	}

}
