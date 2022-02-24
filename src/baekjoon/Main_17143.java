package baekjoon;

import java.io.*;
import java.util.*;

public class Main_17143 {
	static int[][] deltas = {{},{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	static int R, C, M;
	
	static class Shark{
		public int r, c, s, d, z;
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		public void move(int[][] newMap) {
			r = (deltas[d][0] == 0)? r :r+deltas[d][0]*s;
			c = (deltas[d][1] == 0)? c :c+deltas[d][1]*s;
			
			// r 이 범위 밖이라면
			while(r < 1 || R < r) {
				if(r < 1) {
					r = 1 - (r - 1);
					d = 2;
				}
				else { 
					r = R - (r - R);
					d = 1;
				}
			}
			// c 이 범위 밖이라면
			while(c < 1 || C < c) {
				if(c < 1) {
					c = 1 - (c - 1);
					d = 3;
				}
				else {
					c = C - (c - C);
					d = 4;
				}
			}
			
			// 해당 칸에 작은 상어가 있는경우 덮어쓴다.
			if(newMap[r][c] < z) {
				newMap[r][c] = z;
			}
			// 이미 큰 상어가 있는 경우 자신은 잡아먹힌다.
		}
		
	}
	

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 2차원 지도
		int[][] map = new int[R+1][C+1];
		
		// 크기별 상어 정보.
		Map<Integer, Shark> sharks = new HashMap<>();
		
		// 상어 입력받기
		int r, c, s, d, z;
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());	// 행
			c = Integer.parseInt(st.nextToken());	// 열
			s = Integer.parseInt(st.nextToken());	// 속력
			d = Integer.parseInt(st.nextToken());	// 이동방향 (1:상, 2:하, 3:우, 4:좌)
			z = Integer.parseInt(st.nextToken());	// 크기			
			// 해쉬맵에 상어 객체 추가
			sharks.put(z, new Shark(r, c, s, d, z));
			
			// 지도에 상어(크기) 표시
			map[r][c] = z;
		}

		// 낚시왕이 잡은 상어 크기 총합 
		int ansCnt = 0;
		int[][] nextMap;
		
		
		// 왼쪽 열부터 낚시
		for(c = 1; c <= C; c++) {
			// 낚시왕한테 가장 가까운 상어 찾기
			for(r = 1; r <= R; r++) {
				// 상어 발견
				if(map[r][c] != 0) {
					ansCnt += map[r][c];
					map[r][c] = 0;
					break;
				}
			}
			
			// 1초후 상어가 이동할 지도 정보를 새로 초기화
			nextMap = new int[R+1][C+1];
			
			// 맵에 존재하는 모든 상어 이동
			for(int rr = 1; rr <= R; rr++) {
				for(int cc = 1; cc <= C; cc++) {
					if(map[rr][cc] != 0) {
						sharks.get(map[rr][cc]).move(nextMap);
					}
				}
			}
			// 1초후의 지도 정보를 현재 지도로 교체
			map = nextMap;
		}
		
		// 최종 결과 출력
		System.out.println(ansCnt);
		
	}
}
