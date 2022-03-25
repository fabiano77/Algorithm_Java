package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1949 {
	static int N;
	static List<List<Integer>> list;
	static int[] arr;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];

		arr = new int[N+1];
		list = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}

		// 주민 수
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 그래프 저장할 list
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		Pair result = postOrder(1);
		System.out.println(Math.max(result.selectCase, result.notSelectCase));
	}
	static class Pair{
		int selectCase;
		int notSelectCase;
		public Pair(int selectCase, int notSelectCase) {
			this.selectCase = selectCase;
			this.notSelectCase = notSelectCase;
		}
	}
	
	public static Pair postOrder(int root) {
		// 루트 탐색을 위한 방문처리
		visited[root] = true;
		
		// 자신을 선택하거나 선택하지 않거나 두 경우를 나누어 구함.
		int selectCase = arr[root];
		int notSelectCase = 0;
		
		// 트리의 자식들을 모두 탐색한다.
		for(Integer child : list.get(root)) {
			// 방문했으면 pass -> 부모로 거슬러올라가는 것 방지
			if(visited[child]) continue;
			Pair prev = postOrder(child);
			// 루트를 선택할 경우는 바로 아래 자식이 not select 되는 경우
			selectCase += prev.notSelectCase;
			// 루트를 선택하지 않을 경우 바로 아래 자식이 not select되거나 select되거나 큰것을 사용한다.
			notSelectCase += Math.max(prev.selectCase, prev.notSelectCase);
		}

		return new Pair(selectCase, notSelectCase);		
	}
}
