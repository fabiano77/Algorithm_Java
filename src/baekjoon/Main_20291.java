package baekjoon;

import java.io.*;
import java.util.*;

public class Main_20291 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		Map<String, Integer> map = new TreeMap<>();
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine().split("\\.")[1];
			map.put(str, map.getOrDefault(str, 0) + 1 );
		}
		
		for(Map.Entry<String, Integer> e : map.entrySet()) {
			System.out.println(e.getKey()+" "+e.getValue());
		}

		br.close();
		

	}

}
