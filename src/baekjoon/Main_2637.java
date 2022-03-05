package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2637 {
	static int N, M;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		boolean[] baseParts = new boolean[N+1];
		Arrays.fill(baseParts, true);
		int[][] graph = new int[N+1][N+1];
		int[] inDegree = new int[N+1];
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			graph[from][to] = cnt;
			inDegree[to]++;
			baseParts[to] = false;
		}
		
		int[][] required = new int[N+1][N+1];
		for(int part = 1; part <= N; part++) {
			if(baseParts[part]) required[part][part] = 1;
		}
		
		topologySort(graph, required, inDegree);

		for(int part = 1; part <= N; part++) {
			if(!baseParts[part]) continue;
			System.out.println(part+" "+required[N][part]);
		}

	}
	
	// 위상 정렬
	public static void topologySort(int[][] graph, int[][] required, int[] inDegree) {
		Queue<Integer> q = new LinkedList<>();
		
		for(int to = 1; to <= N; to++) {
			if(inDegree[to] == 0) q.offer(to);
		}
		
		while(!q.isEmpty()) {
			int from = q.poll();
			
			for(int to = 1; to <= N; to++) {
				if(graph[from][to] == 0) continue;
				if(--inDegree[to] == 0) q.offer(to);
				for(int fromPart = 1; fromPart <= N; fromPart++) {
					required[to][fromPart] += required[from][fromPart] * graph[from][to];
				}
			}
		}
		
	}

}
