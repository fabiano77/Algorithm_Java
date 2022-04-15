package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1374 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringBuilder sb = new StringBuilder();
			int N = Integer.parseInt(br.readLine());
			int lineCnt = ((N-1)/10)+1;
			int outCnt = 0;
			int cnt = 0;
			PriorityQueue<Integer> maxQ = new PriorityQueue<>((o1, o2)->o2 - o1);
			PriorityQueue<Integer> minQ = new PriorityQueue<>();
			
			for(int i = 0; i < lineCnt; i++) {
				st = new StringTokenizer(br.readLine());
				while(st.hasMoreTokens()) {
					int num = Integer.parseInt(st.nextToken());
					cnt++;
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
					
					if(cnt%2 == 1) {
						sb.append(maxQ.peek()).append(" ");
						outCnt++;
					}
					if(cnt > 0 && cnt%20 == 0) {
						sb.append("\n");
					}
				}
			}
			
			
			System.out.println(outCnt);
			System.out.println(sb);
		}

		br.close();
		
	}
}
