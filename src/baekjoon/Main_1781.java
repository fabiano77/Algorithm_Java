package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1781 {
	
	static class Problem {
		int deadline, point;

		public Problem(int deadline, int point) {
			this.deadline = deadline;
			this.point = point;
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		PriorityQueue<Problem> pqDeadline = new PriorityQueue<>((o1, o2)-> -Integer.compare(o1.deadline, o2.deadline));
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int deadline = Integer.parseInt(st.nextToken());
			int point = Integer.parseInt(st.nextToken());
			pqDeadline.offer(new Problem(deadline, point));
		}
		
		int score = 0;
		PriorityQueue<Integer> pqPoint = new PriorityQueue<>(Collections.reverseOrder());
		for(int day = pqDeadline.peek().deadline; day >= 1; day--) {
			while(!pqDeadline.isEmpty() && pqDeadline.peek().deadline >= day) {
				pqPoint.offer(pqDeadline.poll().point);
			}
			if(!pqPoint.isEmpty()) {
				score += pqPoint.poll();
			}
		}
		
		System.out.println(score);

		br.close();
	}

}
