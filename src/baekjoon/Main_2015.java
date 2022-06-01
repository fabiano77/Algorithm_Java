package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2015 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Map<Integer, Long> map = new HashMap<>();
		
		long ans = 0;
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		int[] prefixSum = new int[N+1];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			prefixSum[i+1] = prefixSum[i] + arr[i];
			
			if(prefixSum[i+1] == K) {
				ans++;
			}
			
			if(map.containsKey(prefixSum[i+1] - K)) {
				ans += map.get(prefixSum[i+1] - K);
			}
			
			if(!map.containsKey(prefixSum[i+1])) {
				map.put(prefixSum[i+1], 1L);
			}
			else {
				map.put(prefixSum[i+1], map.get(prefixSum[i+1])+1);
			}
		}
		
		System.out.println(ans);
		
		

		br.close();
	}

}
