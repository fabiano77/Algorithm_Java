package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_13904_PriorityQueue {
	static int N;
	static boolean[] day = new boolean[1001];
	static int answer;
	
	static class HomeWork {
		public int deadline;
		public int score;
		public HomeWork(int deadline, int score) {
			this.deadline = deadline;
			this.score = score;
		}
	}

	public static void main(String[] args) throws IOException{
		System.setIn(Main_13904_PriorityQueue.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N과 과제의 배열 입력받음
		N = Integer.parseInt(br.readLine());
		HomeWork[] arr = new HomeWork[N];
		for(int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[n] = new HomeWork(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		// 과제 deadline 기준 오름차순으로 정렬
		Arrays.sort(arr, (o1, o2)->Integer.compare(o1.deadline, o2.deadline));
		
		// 우선순위큐 생성
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int day = 1;
		for(HomeWork hw : arr) {
			if(hw.deadline > day) {
				// pq 의 개수를 day - 1로 만들어 준다
				while(pq.size() > day) {
					pq.poll();
				}
				day = hw.deadline;
			}
			pq.add(hw.score);
		}
		while(pq.size() > day) {
			pq.poll();
		}
		
		pq.forEach(e->answer+=e);
		System.out.println(answer);

	}

}
