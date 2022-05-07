package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1806 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		long[] arr = new long[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		int right = 0;
		int left = 0;
		long sum = arr[left];
		int ans = N+1;
		while(right < N) {
			
			if(sum >= S) {
				ans = Math.min(ans, right-left+1);
				sum -= arr[left++];
			}
			else {
				right++;
				if(right<N) sum += arr[right];
			}
		}
		
		if(ans == N+1) {
			System.out.println(0);
		}
		else {			
			System.out.println(ans);
		}
		
		
		br.close();
		

	}

}
