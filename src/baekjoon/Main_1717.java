package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1717 {
	static int N, M;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] parents = new int[N+1];
		for(int i = 0; i < N+1; i++) {
			parents[i] = i;
		}
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(command == 0) {
				//union
				union(parents, a, b);
			}
			else {
				if(check(parents, a, b)) sb.append("YES").append("\n");
				else sb.append("NO").append("\n");
			}
		}
		System.out.println(sb);

	}
	
	public static int find(int[] parents, int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents, parents[a]);
	}

	public static void union(int[] parents, int a, int b) {
		int pA = find(parents, a);
		int pB = find(parents, b);
		if(pA < pB) {
			parents[pB] = pA;
		}
		else {
			parents[pA] = pB;
		}
	}
	
	public static boolean check(int[] parents, int a, int b) {
		int pA = find(parents, a);
		int pB = find(parents, b);
		return pA == pB;
	}
}
