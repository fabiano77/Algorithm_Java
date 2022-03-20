package baekjoon;

import java.io.*;
import java.util.*;

public class Main_4195 {
	static Map<String, Integer> size;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			
			size = new HashMap<>();
			Map<String, String> map = new HashMap<>();
			
			int N = Integer.parseInt(br.readLine());
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				
				if(!map.containsKey(a)) {
					map.put(a, a);
				}
				if(!map.containsKey(b)) {
					map.put(b, b);
				}
				
				sb.append(union(map, a, b)).append("\n");
				
				

				
			}
			
			
		}
		System.out.print(sb);

	}
	
	public static String find(Map<String, String> map, String a) {
		if(map.get(a) != a) {			
			map.put(a, find(map, map.get(a)));
		}
		
		return map.get(a);
	}
	
	public static int union(Map<String, String> map, String a, String b) {
		String pA = find(map, a);
		String pB = find(map, b);
		
		if(!size.containsKey(pA)) size.put(pA, 1);
		if(!size.containsKey(pB)) size.put(pB, 1);
		
		if(pA.equals(pB)) {
			return size.get(pA);
		}
		else if(pA.compareTo(pB) < 0) {
			map.put(pB, pA);
			size.put(pA, size.get(pA) + size.get(pB));
			return size.get(pA);
		}
		else {
			map.put(pA, pB);
			size.put(pB, size.get(pA) + size.get(pB));
			return size.get(pB);
		}
	}

}
