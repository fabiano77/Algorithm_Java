package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2473 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());	// ~ 5,000
		
		long[] arr = new long[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) 	arr[i] = Long.parseLong(st.nextToken());
		Arrays.sort(arr);
		
		long minAbs = Long.MAX_VALUE;
		List<Long> ans = null;

		out:
		for(int i = 0; i < N; i++) {
			int start = i+1;
			int end = N-1;
			
			while(start < end) {				
				long sum = arr[i] + arr[start] + arr[end];
				if(Math.abs(sum) < minAbs) {
					minAbs = Math.abs(sum);
					ans = Arrays.asList(arr[i], arr[start], arr[end]);
				}
				
				if(sum < 0) {
					start++;
				} else if(sum > 0) {
					end--;
				} else {
					break out;
				}
			}
			
		}
		
		ans.forEach(i->System.out.print(i+" "));

		br.close();
	}

}
