package baekjoon;

import java.io.*;
import java.util.*;

public class Main_6416 {
	
	static class Edge{
		int u, v;

		public Edge(int u, int v) {
			this.u = u;
			this.v = v;
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		Scanner scanner = new Scanner(System.in);
		
		List<Edge> edges = new ArrayList<>();
		int maxNo = 0;
		int tc = 1;
		while(true) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			
			if(u < 0 && v < 0) break;
			else if(u == 0 && v == 0) {
				int[] inDegree = new int[maxNo+1];
				boolean[] isNode = new boolean[maxNo+1];
				
				for(Edge e : edges) {
					isNode[e.u] = true;
					isNode[e.v] = true;
					inDegree[e.v]++;
				}
				
				boolean isTree = true;
				int rootCnt = 0;
				for(int i = 1; i <= maxNo; i++) {
					if(isNode[i]) {						
						if(inDegree[i] == 0) rootCnt++;
						else if(inDegree[i] > 1) isTree = false;
					}
				}
				if(maxNo > 0 && rootCnt != 1) isTree = false;
				
				System.out.println(String.format("Case %d is %sa tree.", tc++, (isTree)?"":"not "));
				
				edges.clear();
				maxNo = 0;
			}
			else {
				maxNo = Math.max(maxNo, Math.max(u, v));
				edges.add(new Edge(u, v));
			}
		}

		scanner.close();
	}

}
