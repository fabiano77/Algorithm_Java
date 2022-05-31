package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1826 {
	static class GasStation {
		int dist;
		int fuel;
		public GasStation(int dist, int fuel) {
			this.dist = dist;
			this.fuel = fuel;
		}
		@Override
		public String toString() {
			return "GasStation [dist=" + dist + ", fuel=" + fuel + "]";
		}
		
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 주유소 개수
		int N = Integer.parseInt(br.readLine());
		List<GasStation> list = new ArrayList<>(N);
		
		for(int i = 0; i < N; i++) {
			// a:시작위치부터 주유소까지 거리, b:채울 수 있는 연료양
			st = new StringTokenizer(br.readLine());
			int dist = Integer.parseInt(st.nextToken());
			int fuel = Integer.parseInt(st.nextToken());
			
			list.add(new GasStation(dist, fuel));
		}
		list.sort((o1, o2)->Integer.compare(o1.dist, o2.dist));
		Queue<GasStation> q = new ArrayDeque<>(list);
		
		st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());	// 마을까지의 거리
		int P = Integer.parseInt(st.nextToken());	// 원래 있던 연료의 양
		
		PriorityQueue<GasStation> pq = new PriorityQueue<>((o1, o2)-> -Integer.compare(o1.fuel, o2.fuel));
		
		// 초기 위치에서 주유 안받고 갈 수 있는 거리
		int cur = P;
		int cnt = 0;
		
		// 주유할 수 있는 곳이 있고 마을에 도착하기 전까지 반복
		while(cur < L) {
			// 들릴 수 있는 주유소 객체 pq에 삽입
			while(!q.isEmpty() && q.peek().dist <= cur) {
				pq.add(q.poll());
			}
			if(pq.isEmpty()) {
				break;
			}
			cur += pq.poll().fuel;
			cnt++;
		}
		
		if(cur >= L) {
			System.out.println(cnt);
		}
		else {
			System.out.println(-1);
		}
		
		
		
		
		
		

		br.close();
		

	}

}
