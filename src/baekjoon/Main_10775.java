package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10775 {
	static int G;
	static int P;
	static int[] flights;
	static int[] parent;

	public static void main(String[] args) throws IOException{
		System.setIn(Main_10775.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		
		flights = new int[P];
		parent = new int[G+1];
		for(int i = 0; i < P; i++) {
			// i 번째 비행기를 1번부터 flight[i]번째 게이트 중 하나에 도킹
			// 1번 게이트는 아무나 도킹할 수 있으니, 
			// 1~flight[i]에서 가능한 뒤에서 도킹하는게 best
			// <is Greedy>
			flights[i] = Integer.parseInt(br.readLine());
		}
		
		int cnt = 0;
		for(int lastGate : flights) {
			// 가능한 맨 뒤 gate가 남을 경우
			int docking = 0;
			if(parent[lastGate] == 0) {
				docking = lastGate;
			}
			// 이미 도킹된 비행기가 있을 경우
			else{
				// 도킹해야할 자리가 없을경우 종료
				docking = find(lastGate) - 1;
				if(docking == 0) {
					break;
				} 
			}
			parent[docking] = docking;
			// 도킹하고 앞을 살폈는데 있으면, union으로 이어준다
			if(docking-1 > 0 && parent[docking-1] != 0) {
				union(docking-1, docking);
			}
			// 도킹하고 뒤를 살폈는데 있으면, union으로 이어준다
			if(docking+1 < G && parent[docking+1] != 0) {
				union(docking, docking+1);
			}
			// 도킹된 횟수 카운트
			cnt++;
		}
		System.out.println(cnt);
		
	}
	
	static int find(int i) {
		// path compression 적용
		// find를 수행하는 과정에서 자신의 parent를 곧바로 root를 적용함으로써
		// find의 시간 복잡돌를 줄인다
		if (parent[i] != i) {
			parent[i] = find(parent[i]);
			return parent[i];
		}
		return i;
	}
	
	static void union(int ia, int ib) {
		int parentA = find(ia);
		int parentB = find(ib);
		if(parentA < parentB) {
			parent[ib] = parentA;
		}
		else {
			parent[ia] = parentB; 
		}
	}

}
