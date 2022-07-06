package baekjoon;

import java.io.*;
import java.util.*;

public class Main_17406 {
	
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[R][C];
		
		for(int r = 0; r < R; r++){
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		List<List<Integer>> commands = new ArrayList<>();
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			commands.add(Arrays.asList(r, c, s));
		}
		boolean[] visited = new boolean[K];
		
		permutation(arr, commands, K, visited);
		
		System.out.println(ans);

		br.close();
		

	}
	
	public static int getPoint(int[][] arr) {
		int min = Integer.MAX_VALUE;
		for(int[] line : arr) {
			int sum = 0;
			for(int num : line) {
				sum += num;
			}
			min = Math.min(min, sum);
		}
		
		return min;
	}
	
	public static int getDist(int x, int y, int r, int c) {
		return Math.abs(x-r)+Math.abs(y-c);
	}
	
	public static void solve(int[][] arr, int r, int c, int s, boolean right) {
		for(int dif = 1; dif <= s; dif++) {
			int startX = r - dif;
			int startY = c - dif;
			int x = startX;
			int y = startY;
			int dir = 1;
			Deque<Integer> deque = new ArrayDeque<>();
			do {
				deque.add(arr[x][y]);
				int nx = x + deltas[dir][0];
				int ny = y + deltas[dir][1];
				if(getDist(nx, ny, r, c) > 2*dif) {
					dir = (++dir > 3)? 0 : dir; 
					nx = x + deltas[dir][0];
					ny = y + deltas[dir][1];
				}
				x = nx;
				y = ny;
				
			} while (!(x == startX && y == startY));
			
			if(right) 	deque.addFirst(deque.pollLast());
			else 		deque.addLast(deque.pollFirst());
			
			do {
				arr[x][y] = deque.pollFirst();
				int nx = x + deltas[dir][0];
				int ny = y + deltas[dir][1];
				if(getDist(nx, ny, r, c) > 2*dif) {
					dir = (++dir > 3)? 0 : dir; 
					nx = x + deltas[dir][0];
					ny = y + deltas[dir][1];
				}
				x = nx;
				y = ny;
				
			} while (!(x == startX && y == startY));
		}
		
	}

	public static void permutation(int[][] arr, List<List<Integer>> commands, int toChoose, boolean[] visited) {
		if(toChoose == 0) {
			ans = Math.min(ans, getPoint(arr));
			return;
		}
		
		for(int i = 0; i < commands.size(); i++) {
			if(!visited[i]) {
				int r = commands.get(i).get(0);
				int c = commands.get(i).get(1);
				int s = commands.get(i).get(2);
				
				visited[i] = true;
				solve(arr, r, c, s, true);
				permutation(arr, commands, toChoose-1, visited);
				solve(arr, r, c, s, false);
				visited[i] = false;
			}
		}
		
	}
	
}
