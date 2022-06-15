package baekjoon;

import java.io.*;
import java.util.*;

public class Main_19538 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] degree = new int[N+1];
		int[] adjRumors = new int[N+1];
		
		List<List<Integer>> graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			while(true) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 0) break;
				graph.get(i).add(num);
				degree[i]++;
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		int[] time = new int[N+1];
		Arrays.fill(time, -1);
		
		// 루머를 믿는 사람들 큐
		Queue<Integer> rumorQ = new LinkedList<>();
		
		// 최초유포자 큐에 삽입
		while(st.hasMoreTokens()) {
			rumorQ.offer(Integer.parseInt(st.nextToken()));
		}
		
		int curTime = 0;
		
		while(!rumorQ.isEmpty()) {
			// 주변 절반이상 믿는지 확인해야할 사람들 집합
			Set<Integer> checkSet = new HashSet<>();
			
			int qSize = rumorQ.size();
			for(int i = 0; i < qSize; i++) {
				int node = rumorQ.poll();
				time[node] = curTime;
				for(int adj : graph.get(node)) {
					if(time[adj] != -1) continue;
					adjRumors[adj]++;
					checkSet.add(adj);
				}
			}
			for(int node : checkSet) {
				if(time[node] != -1) continue;
				if((double)adjRumors[node]/degree[node] >= 0.5) {
					rumorQ.offer(node);
				}
			}
			
			curTime++;
		}
		
		for(int i = 1; i <= N; i++) {
			System.out.print(time[i]+" ");
		}
		
		
		br.close();
	}

}
