package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1765 {
	static int[] parents;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		parents = new int[N+1];
		makeSet(parents);
		
		List<List<Integer>> enemies = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			enemies.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			char type = st.nextToken().charAt(0);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(type == 'F') {
				union(a, b);
			}
			else {
				enemies.get(a).add(b);
				enemies.get(b).add(a);
			}
		}
		
//		int[] index = new int[N+1];
//		makeSet(index);

		for(int i = 1; i <= N; i++) {
			search(i, -1, enemies, 1);
		}
		
		int ans = 0;
		for(int i = 1; i <= N; i++) {
			if(parents[i] == i) ans++;
		}
		
		System.out.println(ans);
		
		br.close();
	}
	
	public static void search(int start, int enemy, List<List<Integer>> enemies, int depth) {
		if(depth == 2) {
			for(int friends : enemies.get(enemy)) {
				union(start, friends);
			}
			return;
		}
		
		for(int e : enemies.get(start)) {
			search(start, e, enemies, depth+1);
		}
	}
	
	
	public static void makeSet(int[] parents) {
		for(int i = 1; i < parents.length; i++) {
			parents[i] = i;
		}
	}
	
	public static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find( parents[a]);
	}
	
	public static void union(int a, int b) {
		int pA = find(a);
		int pB = find(b);
		if(pA == pB) return;
		else if(pA < pB) {
			parents[pB] = pA;
		}
		else {
			parents[pA] = pB;
		}
	}

}
