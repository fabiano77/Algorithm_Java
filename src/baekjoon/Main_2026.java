package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2026 {
	static int K, N, F;
	static boolean[][] graph;
	static List<Integer> group;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());	// K명 선발		  ~ 62
		N = Integer.parseInt(st.nextToken());	// 1~N번 번호	K ~ 900
		F = Integer.parseInt(st.nextToken());	// 친구관계 개수  ~ 5,600
		
		//List<List<Integer>> graph = new ArrayList<>();
		graph = new boolean[N+1][N+1];
		
		for(int i = 0; i < F; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a][b] = true;
			graph[b][a] = true;
		}
		
		group = new ArrayList<>();
		for(int i = 1; i <= N; i++) {
			findFriend(i);
			if(group.size() < K) {
				group = new ArrayList<>();
			}
			else {
				Collections.sort(group);
				for(int j = 0; j < K; j++) {
					System.out.println(group.get(j));
				}
				System.exit(0);
			}
		}
		System.out.println(-1);

		br.close();
	}
	
	
	public static void findFriend(int man) {
		if(group.size() > 0) {
			for(int friend : group) {
				if(!graph[friend][man]) {
					return;
				}
			}
			group.add(man);
			return;
		}
		else {
			group.add(man);
		}
		
		for(int i = 1; i <= N; i++) {
			if(man == i) continue;
			if(graph[man][i]) {
				findFriend(i);
			}
		}
	}

}
