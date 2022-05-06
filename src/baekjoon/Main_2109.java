package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2109 {
	
	static class Request{
		int pay, deadline;

		public Request(int pay, int deadline) {
			this.pay = pay;
			this.deadline = deadline;
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		List<Request> list = new ArrayList<>(7);
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int pay = Integer.parseInt(st.nextToken());
			int deadline = Integer.parseInt(st.nextToken());
			list.add(new Request(pay, deadline));
		}
		
		// 마감일 순 정렬
		list.sort((o1, o2)->Integer.compare(o1.deadline, o2.deadline));
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		list.forEach((item)->{
			if(pq.size() < item.deadline) {
				pq.offer(item.pay);
			}
			else if(pq.peek() < item.pay){
				pq.poll();
				pq.offer(item.pay);
			}
		});
		
		int sum = 0;
		for(int pay : pq) {
			sum += pay;
		}
		
		System.out.println(sum);
		
		br.close();
	}

}
