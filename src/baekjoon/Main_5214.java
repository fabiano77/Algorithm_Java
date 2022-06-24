package baekjoon;

import java.io.*;
import java.util.*;

public class Main_5214 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<List<Integer>> adjtube = new ArrayList<>();
		List<List<Integer>> hypertubes = new ArrayList<>();
		
		for(int i = 0; i <= N; i++) {
			adjtube.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			List<Integer> tube = new LinkedList<>();
			while(st.hasMoreTokens()) {
				int node = Integer.parseInt(st.nextToken());
				tube.add(node);
				adjtube.get(node).add(i);
			}
			hypertubes.add(tube);
		}
		
		Queue<Integer> q = new LinkedList<>();
		boolean[] visitedNode = new boolean[N+1];
		boolean[] visitedTube = new boolean[M+1];
		q.offer(1);
		visitedNode[1] = true;
		
		int ans = -1;
		int dist = 1;
		bfs:
		while(!q.isEmpty()) {
			int qSize = q.size();
			while(qSize-- > 0) {
				int cur = q.poll();
				if(cur == N) {
					ans = dist;		
					break bfs;
				}
				for(int tubeNum : adjtube.get(cur)) {
					if(!visitedTube[tubeNum]) {						
						visitedTube[tubeNum] = true;
						for(int next : hypertubes.get(tubeNum)) {						
							if(!visitedNode[next]) {
								visitedNode[next] = true;
								q.offer(next);
							}
						}
					}
				}
			}
			dist++;
		}
		
		System.out.println(ans);

		br.close();
		

	}

}
