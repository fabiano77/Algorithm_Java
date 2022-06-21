package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1822 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Set<Integer> set1 = new TreeSet<>();
		for(int i = 0; i < N; i++) {
			set1.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		Set<Integer> set2 = new TreeSet<>();
		for(int i = 0; i < M; i++) {
			set2.add(Integer.parseInt(st.nextToken()));
		}
		
		set1.removeAll(set2);
		System.out.println(set1.size());
		for(int num : set1) {
			System.out.print(num+" ");
		}
		

		br.close();
	}

}
