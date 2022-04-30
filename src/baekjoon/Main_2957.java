package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2957 {

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		TreeSet<Integer> tree = new TreeSet<>();
		
		long ans = 0;
		int[] depth = new int[N+2];
		depth[0] = -1;
		depth[N+1] = -1;
		tree.add(0);
		tree.add(N+1);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			depth[num] = Math.max(depth[tree.lower(num)], depth[tree.higher(num)]) + 1;
			tree.add(num);
			ans += depth[num];
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
		

	}

}
