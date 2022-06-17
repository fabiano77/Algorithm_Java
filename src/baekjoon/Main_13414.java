package baekjoon;

import java.io.*;
import java.util.*;

public class Main_13414 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		LinkedHashSet<String> set = new LinkedHashSet<>();
		
		for(int i = 0; i < M; i++) {
			String str = br.readLine();
			if(set.contains(str))
				set.remove(str);
			set.add(str);
		}
		int cnt = 0;
		for(String str : set) {
			System.out.println(str);
			if(++cnt == N) break;
		}

		

	}

}
