package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1351 {
	
	static long P, Q;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long N = Long.parseLong(st.nextToken());
		P = Long.parseLong(st.nextToken());
		Q = Long.parseLong(st.nextToken());
		
		Map<Long, Long> map = new HashMap<>();
		
		System.out.println(solve(map, N));

		br.close();
		

	}
	
	static long solve(Map<Long, Long> map, long num) {
		
		if(num == 0) return 1;
		if(map.containsKey(num)) return map.get(num);
		
		long a = (long)Math.floor(num/P);
		long b = (long)Math.floor(num/Q);
		
		map.put(num, solve(map, a) + solve(map, b));
		return map.get(num);
	}

}
