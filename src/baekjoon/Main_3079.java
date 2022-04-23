package baekjoon;

import java.io.*;
import java.util.*;

public class Main_3079 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long[] arr = new long[N];
		long max = 0;
		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
			max = Math.max(max, arr[i]);
		}
		
		long ans = Long.MAX_VALUE-2;
		long start = 1L;
		long end = max * M;
		while(start <= end) {
			long mid = (start + end) / 2L;
			
			long cnt = 0;
			for(int i = 0; i < N; i++) {
				cnt += mid / arr[i];
			}
			
			
			if(cnt < M) {
				start = mid + 1;
			}
			else {
				ans = mid;
				end = mid - 1;
			}
		}
		
		System.out.println(ans);

		
		br.close();
	}

}
