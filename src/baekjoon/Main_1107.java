package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1107 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		if(M > 0) st = new StringTokenizer(br.readLine());
		boolean[] broken = new boolean[10];
		for(int i = 0; i < M; i++) {
			broken[Integer.parseInt(st.nextToken())] = true;
		}
		
		System.out.println(bfs(N, M, broken));

	}
	
	public static int bfs(int N, int M, boolean[] broken) {
		boolean[] visited = new boolean[1_000_001];
		int cnt = 0;
		int[] delta = {-1, +1};
		
		int ans = Math.abs(N-100);
		Queue<Integer> q = new LinkedList<>();
		q.offer(N);
		visited[N] = true;
		search:
		while(!q.isEmpty()) {
			int qSize = q.size();
			for(int i = 0; i < qSize; i++) {				
				int curNum = q.poll();

				String curStr = String.valueOf(curNum);
				boolean available = true;
				for(char c : curStr.toCharArray()) {
					if(broken[c-'0']) {
						available = false;
					}
				}
				if(available) {
					ans = Math.min(ans, cnt + curStr.length());
					break search;
				}
				
				for(int d = 0; d < 2; d++) {
					int next = curNum + delta[d];
					if(0 <= next && next <= 1_000_000 && !visited[next]) {
						visited[next] = true;
						q.offer(next);
					}
				}
			}
			cnt++;
		}

		return ans;
	}

}
