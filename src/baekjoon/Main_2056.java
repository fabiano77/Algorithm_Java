package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2056 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] time = new int[N+1];
		int[] finishTime = new int[N+1];
		int[] inDegree = new int[N+1];
		List<List<Integer>> graph = new ArrayList<>();
		
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			while(cnt-- > 0) {
				int from = Integer.parseInt(st.nextToken());
				graph.get(from).add(i);
				inDegree[i]++;
			}
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		
		for(int i = 1; i <= N; i++) {
			if(inDegree[i] == 0) {
				q.offer(i);
				finishTime[i] = time[i];
			}
		}
		
		int ans = 0;
		while(!q.isEmpty()) {
			int from = q.poll();
			for(int to : graph.get(from)) {
				if(--inDegree[to] == 0) {
					q.offer(to);
				}
				finishTime[to] = Math.max(finishTime[to], finishTime[from]+time[to]);
				ans = Math.max(finishTime[to], ans);
			}
		}
		System.out.println(ans);

		

	}

}
