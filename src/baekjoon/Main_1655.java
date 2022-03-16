package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1655 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> maxQ = new PriorityQueue<>((o1, o2)->o2 - o1);
		PriorityQueue<Integer> minQ = new PriorityQueue<>();
		
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			// 중간값보다 큰 경우
			if(!maxQ.isEmpty() && maxQ.peek() < num) {
				minQ.offer(num);
			}
			// 중간값보다 작은 경우
			else {
				maxQ.offer(num);
			}
			
			// minQ, maxQ 개수 맞춤
			while(maxQ.size() > minQ.size()) {
				minQ.offer(maxQ.poll());
			}
			while(maxQ.size() < minQ.size()) {
				maxQ.offer(minQ.poll());
			}
			
			sb.append(maxQ.peek()).append("\n");
		}

		System.out.print(sb);
		

	}

}