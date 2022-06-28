package baekjoon;

import java.io.*;
import java.util.*;

public class Main_23057 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int sum = 0;
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			sum += num;
			Set<Integer> addSet = new HashSet<>();
			for(int el : set) {
				addSet.add(el + num);
			}
			set.addAll(addSet);
			set.add(num);
		}
		System.out.println(sum - set.size());
		
		br.close();
		

	}

}
