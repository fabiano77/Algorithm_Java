package baekjoon;

import java.io.*;
import java.util.*;

public class Main_19238 {
	
	static class Point implements Comparable<Point>{
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
		@Override
		public int compareTo(Point o) {
			if(this.r == o.r) return this.c - o.c;
			return this.r - o.r;
		}
	}
	
	
	static int N, M, fuel;
	static int[][] map; 
	static int distance;
	static int passengerNumber;
	static boolean fail;
	static Point[] destinations;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken())-1;
		int c = Integer.parseInt(st.nextToken())-1;
		
		Point taxi = new Point(r, c);
		
		destinations = new Point[M+2];
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			
			int r1 = Integer.parseInt(st.nextToken())-1;
			int c1 = Integer.parseInt(st.nextToken())-1;
			int r2 = Integer.parseInt(st.nextToken())-1;
			int c2 = Integer.parseInt(st.nextToken())-1;
			// 승객 위치 기록
			map[r1][c1] = m+2;
			// 승객 목적지 위치 기록
			destinations[map[r1][c1]] = new Point(r2, c2);
		}
		

		

		// 승객 수만큼 반복
		for(int m = 0; m < M; m++) {
			// 가장 가까운 승객이 있는 곳으로 이동
			taxi = bfs(taxi, true);
			if(fail) break;
			// 승객 번호를 기억한다(목적지 판별을위해)
			passengerNumber = map[taxi.r][taxi.c];
			// 승객을 태움
			map[taxi.r][taxi.c] = 0;
			// 목적지로 이동
			taxi = bfs(taxi, false);
			if(fail) break;
			// 운송에 성공했을 경우 연료를 2배로함
			fuel += distance*2;
		}
		
		
		if(fail) {
			System.out.println(-1);
		}
		else {
			System.out.println(fuel);
		}

	}
	
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	static boolean isIn(Point p) {
		return 0 <= p.r && p.r < N && 0 <= p.c && p.c < N;
	}
	
	static Point bfs(Point taxi, boolean forPassenger) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		visited[taxi.r][taxi.c] = true;
		q.offer(taxi);
		
		// 거리 0으로 초기화
		distance = 0;
		
		PriorityQueue<Point> passengersQ = new PriorityQueue<>();
		
		while(!q.isEmpty()) {
			int qSize = q.size();
			// bfs에서 동일 거리(동일 깊이)별 탐색을 위한, qSize
			for(int i = 0; i < qSize; i++) {
				Point cur = q.poll();
				// 승객을 태우러 가는 중이고, 승객이라면
				if(forPassenger && map[cur.r][cur.c] > 1) {
					passengersQ.offer(cur);
					continue;
				}
				// 승객을 태운 후 목적지를 가는 중이고, 목적지라면
				if(!forPassenger && destinations[passengerNumber].r == cur.r && destinations[passengerNumber].c == cur.c) {
					return cur;
				}
				// 사방 탐색
				for(int d = 0; d < 4; d++) {
					Point next = new Point(cur.r+deltas[d][0], cur.c+deltas[d][1]);
					// 지도 내부이고, 벽이 아니고, 방문한 점이 아닐 경우
					if(isIn(next) && map[next.r][next.c] != 1 && !visited[next.r][next.c]) {
						visited[next.r][next.c] = true;
						q.offer(next);
					}
				}
			}
			// 태울 승객이 있다면
			if(!passengersQ.isEmpty()) {
				break;
			}
			
			distance++;
			
			// 연료 1 감소
			if(fuel-- == 0) {
				// 연료 0되면 종료
				fail = true;
				return null;
			}
		}
		
		// 승객을 태우러 돌아다녔는데, 승객을 못찾았을 경우
		// 또는 목적지를 못찾았을 경우
		if((forPassenger && passengersQ.isEmpty()) || !forPassenger) {
			fail = true;
			return null;
		}
		else {
			return passengersQ.poll();
		}
		
	}

}
