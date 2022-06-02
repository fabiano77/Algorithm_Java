package baekjoon;

import java.io.*;
import java.util.*;

public class Main_21611 {

	// 방향 관련 서 남 동 북
	static int[][] deltas = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
	static int N;
	static int half;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 3 ~ 49 홀수
		int M = Integer.parseInt(st.nextToken()); //

		half = N / 2;

		// 2차원 map 입력받음
		int[][] map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// m번 반복
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());

			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int dir = getDir(d);
			
			// 블리자드 시전
			blizzard(map, dir, s);
			
			// 1차원으로 펼침
			int[] arr = mapToArr(map);
			
			boolean connected = false;
			while(!connected) {
				// 4개 연속구슬 터트림
				explosion(arr);
				
				// 앞으로 이어붙이기
				connected = connect(arr);			
			}
			
			// 구슬 부풀림
			arr = mutation(arr);
			

			map = arrToMap(arr);

		}
		
		System.out.println(ans);

		br.close();
	}
	
	public static int[] mutation(int[] arr) {
		int[] nextArr = new int[arr.length];
		int idx = 0;
		
		int curNum = -1;
		int curCnt = 0;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == curNum) {
				curCnt++;
			}
			else {
				// 다른 숫자 나온다면
				if(curNum != -1) {					
					nextArr[idx++] = curCnt;
					nextArr[idx++] = curNum;
				}
				if(idx >= arr.length || arr[i] == 0) break;
				
				curNum = arr[i];
				curCnt = 1;
			}
		}
		
		return nextArr; 
	}
	
	public static boolean connect(int[] arr) {
		int idx = 0;
		boolean alreadyConnected = true; 
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == 0) continue;
			arr[idx] = arr[i];
			if(idx != i) {
				arr[i] = 0;
				alreadyConnected = false;
			}
			idx++;
		}
		return alreadyConnected;
	}
	
	public static void explosion(int[] arr) {
		int firstIdx = -1;
		int curNum = -1;
		int curCnt = 0;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == 0) continue;
			
			if(arr[i] == curNum) {
				curCnt++;
			}
			else {
				if(curCnt >= 4) {
					for(int j = firstIdx; j < i; j++) {
						ans += arr[j]; 
						arr[j] = 0;
					}
				}
				firstIdx = i;
				curNum = arr[i];
				curCnt = 1;
			}
		}
		if(curCnt >= 4) {
			for(int j = firstIdx; j < arr.length; j++) {
				ans += arr[j]; 
				arr[j] = 0;
			}
		}
	}
	
	public static void blizzard(int[][] map, int dir, int s) {
		for(int dist = 1; dist <= s; dist++) {
			int r = half + deltas[dir][0] * dist;
			int c = half + deltas[dir][1] * dist;
			map[r][c] = 0;
		}
	}

	public static int getDir(int d) {
		switch (d) {
		case 1:
			return 3;
		case 2:
			return 1;
		case 3:
			return 0;
		case 4:
			return 2;
		default:
			break;
		}
		return -1;
	}

	public static int[] mapToArr(int[][] map) {
		// 1차원으로 펼쳐서 저장할 배열
		int[] arr = new int[N * N - 1];
		int idx = 0;

		// 2차원 배열 순회 관련
		int range = 1;
		int r = half;
		int c = half;
		int dir = 0;

		while (!(r == 0 && c == 0)) {
			// 다음 칸
			int nr = r + deltas[dir][0];
			int nc = c + deltas[dir][1];

			// 중간에서 range칸 내에있다면 이동
			if (half - range <= nr && nr <= half + range && half - range <= nc && nc <= half + range) {
				r = nr;
				c = nc;
				arr[idx++] = map[r][c];
			} else {
				// 좌회전
				dir = (dir + 1) % 4;
				if (dir == 0) {
					range++;
				}
			}

		}
		return arr;
	}

	public static int[][] arrToMap(int[] arr) {
		// 1차원으로 펼쳐서 저장할 배열
		int[][] map = new int[N][N];
		int idx = 0;

		// 2차원 배열 순회 관련
		int range = 1;
		int r = half;
		int c = half;
		int dir = 0;

		while (!(r == 0 && c == 0)) {
			// 다음 칸
			int nr = r + deltas[dir][0];
			int nc = c + deltas[dir][1];

			// 중간에서 range칸 내에있다면 이동
			if (half - range <= nr && nr <= half + range && half - range <= nc && nc <= half + range) {
				r = nr;
				c = nc;
				// arr[idx++] = map[r][c];
				map[r][c] = arr[idx++];
			} else {
				// 좌회전
				dir = (dir + 1) % 4;
				if (dir == 0) {
					range++;
				}
			}

		}
		return map;
	}

}
