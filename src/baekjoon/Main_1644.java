package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1644 {
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
				
		N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		// 소수 구하기
		
		boolean[] prime = new boolean[N+1];
		prime[0] = prime[1] = true;
		for(int i = 2; i*i <= N; i++) {
			if(!prime[i]) {
				for(int j = i*i; j <= N; j += i) prime[j] = true;
			}
		}
		for(int i = 1; i <= N; i++) {
			if(!prime[i]) list.add(i);
		}

		int[] sums = new int[list.size()+1];
		for(int i = 1; i <= list.size(); i++) {
			sums[i] = sums[i-1] + list.get(i-1);
		}
		int start = 0;
		int end = 1;
		
		int cnt = 0;
		while(end < list.size()+1) {
			int sum = sums[end]-sums[start];
			if(sum == N) {
				cnt++;
				end++;
			}
			else if(sum < N) end++;
			else start++;
		}
		System.out.println(cnt);
		
		
	}
	

}