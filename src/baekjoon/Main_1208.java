package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1208 {
	static Map<Integer, Integer> map;
	static int[] arr;
	static int N, S, half;
	static long ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		half = N/2;
		
		st = new StringTokenizer(br.readLine());
		
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		map = new HashMap<>();
		
		dfs(0, 0, false);
		dfs(half, 0, true);
		if(S == 0) ans--;
		System.out.println(ans);
	}
	
	public static void dfs(int idx, int sum, boolean right) {
		if(right) {
			if(idx == N) {
				if(map.containsKey(S-sum)) {
					ans += map.get(S-sum);
				}
				return;
			}
		}
		else {
			if(idx == half) {
				if(map.containsKey(sum)) {
					map.put(sum, map.get(sum)+1);
				}
				else {
					map.put(sum, 1);					
				}
				return;
			}
		}
		dfs(idx+1, sum, right);
		dfs(idx+1, sum+arr[idx], right);
	}
	


}
