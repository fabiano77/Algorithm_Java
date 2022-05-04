package baekjoon;

import java.io.*;
import java.util.*;

public class Main_12100 {
	static int N;
	static int ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		permutation(0, map);
		System.out.println(ans);
		
		br.close();
	}
	
	public static int getMax(int[][] map) {
		int max = 0;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				max = Math.max(max, map[r][c]);
			}
		}
		return max;
	}
	
	public static void permutation(int depth, int[][] map) {
		if(depth == 5) {
			ans = Math.max(ans, getMax(map));
			return;
		}
		
		for(int dir = 0; dir < 4; dir++) {
			int[][] nextMap = move(map, dir);
			permutation(depth+1, nextMap);
		}
	}
	

	
	public static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
	
	public static int[][] move(int[][] map, int dir){
		int[][] nextMap = new int[N][N];
		int[] startR = {0, 0, 	N-1, 0};
		int[] startC = {0, N-1, 0,   0};
		int[] deltaR = {0, 1, 	0, 	 1};
		int[] deltaC = {1, 0,   1,   0};
		int[][] queueDeltas = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
		
		for(int r  = startR[dir], c  = startC[dir];		// 초기식
				isIn(r, c);								// 조건식
				r += deltaR[dir], c += deltaC[dir]){	// 증감식
			
			int idx = 0;
			Queue<Integer> queue = new LinkedList<>();
			for(int queueR = r, queueC = c;
					isIn(queueR, queueC);
					queueR += queueDeltas[dir][0], queueC += queueDeltas[dir][1]) {
				
				if(map[queueR][queueC] > 0) {
					queue.offer(map[queueR][queueC]);
				}
			}
			
			for(int queueR = r, queueC = c;
					isIn(queueR, queueC);
					queueR += queueDeltas[dir][0], queueC += queueDeltas[dir][1]) {
				
				if(!queue.isEmpty()) {					
					nextMap[queueR][queueC] = queue.poll();
					if(!queue.isEmpty() && (queue.peek() == nextMap[queueR][queueC])) {
						nextMap[queueR][queueC] += queue.poll();
					}
				}
				else {
					nextMap[queueR][queueC] = 0;
				}
				
			}
			
		}
		
		return nextMap;
	}
	
}
