package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2623 {
	static int N;
	static int M;
	static List<List<Integer>> graph;
	static int[] inDegree;
	static StringBuilder sb = new StringBuilder();
	static int cnt;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		inDegree = new int[N+1];
		graph = new ArrayList<>();
		for(int n = 0; n <= N; n++) {
			graph.add(new ArrayList<>());
		}
		
		int from, to;
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			from = Integer.parseInt(st.nextToken());
			while(st.hasMoreTokens()) {
				to = Integer.parseInt(st.nextToken());
				
				graph.get(from).add(to);
				inDegree[to]++;
				
				from = to;
			}
		}
		
		if(topologySort()) System.out.print(sb);
		else System.out.println(0);
		

	}
	
	public static boolean topologySort() {
		Queue<Integer> q = new LinkedList<>();
		for(int v = 1; v <= N; v++) {
			if(inDegree[v] == 0) {				
				q.offer(v);
			}
		}
		
		int from;
		while(!q.isEmpty()) {
			from = q.poll();
			cnt++;
			sb.append(from).append("\n");
			for(int to : graph.get(from)) {
				if(--inDegree[to] == 0) q.offer(to);
			}
		}
		return cnt == N;
	}

}
