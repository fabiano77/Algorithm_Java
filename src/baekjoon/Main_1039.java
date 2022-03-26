package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1039 {
	static String max;
	static boolean answer;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder N = new StringBuilder(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		max = null;
		Set<String> visited = new HashSet<>();
		StringBuilder idxes = new StringBuilder();
		for(int i = 0; i < N.length(); i++) {
			idxes.append(i);
		}
		combination(visited, idxes, N, K, 2, -1, -1);
		
		if(answer) {
			System.out.println(max);
		}
		else {
			System.out.println(-1);
		}
	}
	
	public static void swap(StringBuilder sb, int idx1, int idx2) {
		char temp = sb.charAt(idx1);
		sb.setCharAt(idx1, sb.charAt(idx2));
		sb.setCharAt(idx2, temp);
	}
	
	public static void combination(Set<String> visited, StringBuilder idxes, StringBuilder N, int K, int toChoose, int selIdx1, int selIdx2) {
		if(K == 0) {
			if(max == null || N.toString().compareTo(max) > 0) {
				max = N.toString();
				answer = true;
			}
			return;
		}
		if(toChoose == 0) {
			swap(N, selIdx1, selIdx2);
			swap(idxes, selIdx1, selIdx2);
			// 0이면 진행 X
			if(N.charAt(0) != '0' && !visited.contains(K+" "+idxes.toString())) {
				visited.add(K+" "+idxes.toString());
				combination(visited, idxes, N, K-1, 2, -1, -1);
			}
			swap(idxes, selIdx1, selIdx2);
			swap(N, selIdx1, selIdx2);
			return;
		}
		
		for(int i = 0; i < N.length(); i++) {
			if(i == selIdx1) continue;
			
			if(selIdx1 == -1) {				
				selIdx1 = i;
				combination(visited, idxes, N, K, toChoose-1, selIdx1, selIdx2);
				selIdx1 = -1;
			}
			else {
				selIdx2 = i;
				combination(visited, idxes, N, K, toChoose-1, selIdx1, selIdx2);
				selIdx2 = -1;
			}
		}
		
	}

}
