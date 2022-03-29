package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1068 {
	static int ans;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		List<List<Integer>> children = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			children.add(new ArrayList<>());
		}

		int root = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			if(parent == -1) {
				root = i;
				continue;
			}
			children.get(parent).add(i);
		}
		
		int b = Integer.parseInt(br.readLine());
		
		if(root != b) {			
			dfs(children, root, b);
		}
		
		System.out.println(ans);

	}
	
	public static void dfs(List<List<Integer>> children, int cur, int br) {

		boolean haveChild = false;
		for(int child : children.get(cur)) {
			if(child == br) continue;
			haveChild = true;
			dfs(children, child, br);
		}
		if(!haveChild) ans++;
	}

}
