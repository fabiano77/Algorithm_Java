package baekjoon;

import java.io.*;
import java.util.*;

public class Main_16932 {
	static int[] parents;
	static int[] groupCnt;
	static final int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int N, M;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N*M];
		groupCnt = new int[N*M];
		for(int i = 0; i < N*M; i++) {
			parents[i] = i;
		}
		
		// 1,000,000
		int[][] arr = new int[N][M];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			
			for(int c = 0; c < M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[][] visited = new boolean[N][M];
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(!visited[r][c] && arr[r][c] == 1) {
					dfsUnion(r, c, visited, arr);
				}
			}
		}
		
		int ans = 0;
		Set<Integer> visitedGroup = new HashSet<>();
		// 모든 점에 대해 탐색
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				// 값이 0인 점에서 상하좌우를 이어본다.
				if(arr[r][c] == 0) {
					// 0을 1로 바꿨을 때 모양의 기본 크기 1
					int cnt = 1;
					visitedGroup.clear();
					for(int[] delta : deltas) {
						int nr = r + delta[0];
						int nc = c + delta[1];
						if(!isIn(nr, nc)) continue;
						if(arr[nr][nc] == 0) continue;
						int adjGroupIdx = find(pointToIdx(nr, nc));
						if(visitedGroup.contains(adjGroupIdx)) continue;
						visitedGroup.add(adjGroupIdx);
						cnt += groupCnt[adjGroupIdx];
					}
					ans = Math.max(ans, cnt);
				}
			}
		}
		
		System.out.println(ans);
		
	}
	
	public static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
	public static int pointToIdx (int r, int c) {
		return r * M + c;
	}
	
	public static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	public static int dfsUnion(int r, int c, boolean[][] visited, int[][] arr) {
		// 현재 좌표를 1차원 idx로 저장
		visited[r][c] = true;
		int idx = pointToIdx(r, c);
		int cnt = 0;
		for(int[] delta : deltas) {
			int nr = r + delta[0];
			int nc = c + delta[1];
			if(isIn(nr, nc) && !visited[nr][nc] && arr[nr][nc] == 1) {
				// dfs로 탐색하는 지점들이 시작점을 가리키게함.
				parents[pointToIdx(nr, nc)] = idx;
				cnt += dfsUnion(nr, nc, visited, arr);
			}
		}
		return groupCnt[idx] = cnt + 1;
	}

}
