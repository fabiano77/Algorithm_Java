package baekjoon;

import java.io.*;
import java.util.*;

public class Main_16235 {
	static int N;
	static int[][] deltas = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// ~ 10
		int M = Integer.parseInt(st.nextToken());	// ~ 100
		int K = Integer.parseInt(st.nextToken());	// ~ 1,000
		
		int[][] s2d2 = new int[N][N];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				s2d2[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 봄 : 나무가 자신의 나이만큼 양분 먹고 나이 1 증가.
		// 		같은 칸에 여러 나무 있다면 나이가 어린 나무부터 먹는다.
		//		양분이 부족하여 양분을 먹지못하는 나무는 즉시 죽는다.
		// 여름 : 봄에 죽은 나무가 양분으로 변하게 된다.
		//		죽은 나무마다 나이를 2로 나눈 몫이 양분으로 추가된다.
		// 가을 : 나이가 5의 배수인 나무가 번식한다.
		//		인접한 8개의 칸에 나이가 1인 나무가 생긴다. 땅밖으로는 X
		// 겨울 : 입력받은대로 땅에 양분을 추가한다.
		
		int[][] map = new int[N][N];
		PriorityQueue<Integer>[][] trees = new PriorityQueue[N][N];
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				// 초기 양분은 모든칸 5
				map[r][c] = 5;
				// 나무를 저장할 우선순위큐
				trees[r][c] = new PriorityQueue<>();
			}
		}
		
		// 나무 입력받음
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int age = Integer.parseInt(st.nextToken());
			trees[r][c].add(age);
		}
		
		for(int year = 0; year < K; year++) {
			// 봄 & 여름
			spring(trees, map);
			// 가을
			fall(trees, map);
			// 겨울
			winter(map, s2d2);
		}
		
		System.out.println(count(trees));
	}
	
	
	static void spring(PriorityQueue<Integer>[][] trees, int[][] map) {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				int addNutrient = 0;
				PriorityQueue<Integer> after = new PriorityQueue<>();
				while(!trees[r][c].isEmpty()) {
					int treeAge = trees[r][c].poll();
					// 먹을 양분이 있을 경우
					if(treeAge <= map[r][c]) {
						map[r][c] -= treeAge;
						after.add(treeAge+1);
					}
					// 양분을 못먹고 죽는 경우
					else {
						addNutrient += treeAge / 2;
					}
				}
				trees[r][c] = after;
				map[r][c] += addNutrient;
			}
		}
	}
	
	static void fall(PriorityQueue<Integer>[][] trees, int[][] map) {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				for(int treeAge : trees[r][c]) {
					// 나이가 5의 배수인 나무들에 대해
					if(treeAge % 5 == 0) {
						// 인접한 8칸에 번식하도록한다.
						for(int[] delta : deltas) {
							int nr = r + delta[0];
							int nc = c + delta[1];
							if(!isIn(nr, nc, N)) continue;
							trees[nr][nc].add(1);
						}
					}
				}
			}
		}
	}
	
	static void winter(int[][] map, int[][] s2d2) {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				map[r][c] += s2d2[r][c];
			}
		}
	}
	
	static int count(PriorityQueue<Integer>[][] trees) {
		int ans = 0;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				ans += trees[r][c].size();
			}
		}
		return ans;
	}
	
	static boolean isIn(int r, int c, int N) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}

}
