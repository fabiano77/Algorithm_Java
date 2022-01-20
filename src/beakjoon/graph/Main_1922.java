package beakjoon.graph;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main_1922 {
	static int N;
	static int M;
	static int [][] edges;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setIn(Main_1922.class.getResourceAsStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();	//노드의 개수
		M = sc.nextInt();	//엣지의 개수
		int result = 0;		//결과 비용
		
		// edges 배열
		edges = new int[M+1][3];
		
		for (int i = 0; i < M; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int cost = sc.nextInt();
			// 비 방향 그래프이므로 start end 상관 없음
			edges[i] = new int[]{start, end, cost};
		}
		
		// cost 기준으로 edges 정렬
		Arrays.sort(edges, Comparator.comparing(o1 -> o1[2]));
		
		
		// 노드들의 서로소 집합 알고리즘을 위한 parentTable 셋팅
		int [] parentTable = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parentTable[i] = i;
		}
		
		// 가장 비용이 적은 edge부터 사이클을 만들지 않도록 추가한다.
		for(int i = 1; i <= M; i++) {
			int a = edges[i][0];
			int b = edges[i][1];
			
			// a와 b가 다른 집합이라면
			if (findParent(parentTable, a) != findParent(parentTable, b)) {
				// 같은 집합으로 추가하고, 비용을 더한다
				unionParent(parentTable, a, b);
				result += edges[i][2];
			}
		}
		
		System.out.println(result);
	}
	
	// 루트 노드를 찾는 메서드
	static int findParent(int[] parent, int x) {
		if (parent[x] != x) {
			return findParent(parent, parent[x]);
		}
		return x;
	}
	
	// 두 원소가 속한 집합을 합치기
	static void unionParent(int[] parent, int a, int  b) {
		a = findParent(parent, a);
		b = findParent(parent, b);
		
		if(a < b) {
			parent[b] = a;
		}
		else {
			parent[a] = b;
		}
	}

}
