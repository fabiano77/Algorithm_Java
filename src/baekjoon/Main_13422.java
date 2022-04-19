package baekjoon;

import java.io.*;
import java.util.*;

public class Main_13422 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	// ~ 100,000
			int M = Integer.parseInt(st.nextToken());	// ~ M
			int K = Integer.parseInt(st.nextToken());	// 1,000,000,000
			
			int[] money = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				money[i] = Integer.parseInt(st.nextToken());	// 1 ~ 10,000
			}
			
			int ans = 0;
			int sum = 0;
			for(int i = 0; i < M; i++) {
				sum += money[i];
			}
			if(N == M) {
				if(sum < K) ans++;
				System.out.println(ans);
			}
			else {				
				int end = M;
				for(int start = 0; start < N; start++) {
					if(sum < K) ans++;
					sum += money[end];
					sum -= money[start];
					end = (end+1)%N;
				}
				System.out.println(ans);
			}
			
		}
		
		
		
		br.close();
		

	}

}
