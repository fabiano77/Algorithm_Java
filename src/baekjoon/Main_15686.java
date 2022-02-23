package baekjoon;

import java.io.*;
import java.util.*;

public class Main_15686 {
	static class Point{
		public int x;
		public int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		public int getDistance(Point o) {
			return Math.abs(this.x - o.x) + Math.abs(this.y - o.y);
		}
		
	}
	static int N;
	static int M;

	public static void main(String[] args)throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
//		ArrayList<Point> houses = new ArrayList<>();
//		ArrayList<Point> chickens = new ArrayList<>();
		
		// 컬렉션API ArrayList에서 Array로 바꾸기 160 -> 140ms
		int housesLen = 0;
		Point[] houses = new Point[N*N];
		int chickensLen = 0;
		Point[] chickens = new Point[13];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				int info = Integer.parseInt(st.nextToken());
				if(info == 1) 		houses[housesLen++] = new Point(r, c);
				else if(info == 2) 	chickens[chickensLen++] = new Point(r, c);
			}
		}
		
		// 최종 치킨 거리
		int ans = Integer.MAX_VALUE;

		// 반복문에서 선언을 최소화 168ms -> 160ms
		int chickDist, distance;
		
		// 치킨집 m개만 남겨야함.
		for(int i = 0; i < 1 << chickensLen; i++) {
			// 조합으로 m개를 고르는
			if(Integer.bitCount(i)==M) {
				chickDist = 0;
				// 각 집마다
				for(int h = 0; h < housesLen; h++) {
					distance = Integer.MAX_VALUE;
					// 조합에서 골라진 인덱스 j
					for(int j = 0; j < chickensLen; j++) {
						if((i & (1 << j)) > 0) {
							distance = Math.min(distance, chickens[j].getDistance(houses[h]));
						}						
					}
					chickDist += distance;
				}
				ans = Math.min(ans, chickDist);
			}
			
		}
		
		System.out.println(ans);
		
		
		

	}

}
