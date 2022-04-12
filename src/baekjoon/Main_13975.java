package baekjoon;

import java.io.*;
import java.util.*;

public class Main_13975 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			int K = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			long ans = 0;
			PriorityQueue<Long> pq = new PriorityQueue<>();
			while(K-- > 0) pq.offer(Long.parseLong(st.nextToken()));
			
			while(pq.size() > 1) {
				long a = pq.poll();
				long b = pq.poll();
				long sum = a + b;
				ans += sum;
				pq.offer(sum);
			}
			
			System.out.println(ans);
		}
			
		

	}

}
