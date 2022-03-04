package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1516 {
	static int N;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		List<List<Integer>> graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		int[] time = new int[N+1];
		int[] inDegree = new int[N+1];
		
		for(int cur = 1; cur <= N; cur++) {
			st = new StringTokenizer(br.readLine());
			time[cur] = Integer.parseInt(st.nextToken());
			int preCondition = Integer.parseInt(st.nextToken());
			while(preCondition != -1) {
				graph.get(preCondition).add(cur);
				inDegree[cur]++;
				preCondition = Integer.parseInt(st.nextToken());
			}
		}
		
		// 위상정렬
		Queue<Integer> q = new LinkedList<>();
		int[] completeTime = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			if(inDegree[i] == 0) {
				q.offer(i);
				completeTime[i] = time[i];
			}
		}
		
		while(!q.isEmpty()) {
			int from = q.poll();
			
			
			for(int to : graph.get(from)) {
				completeTime[to] = Math.max(completeTime[to], completeTime[from]+time[to]);
				if(--inDegree[to] == 0) {
					q.offer(to);
				}
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			sb.append(completeTime[i]).append("\n");
		}
		System.out.println(sb);

	}

}
