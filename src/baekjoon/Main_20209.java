package baekjoon;

import java.io.*;
import java.util.*;

public class Main_20209 {
	static int N, K;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[] cube = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			cube[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] switches = new int[K+1][];
		for(int k = 1; k <= K; k++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			switches[k] = new int[num];
			for(int i = 0; i < num; i++) {
				switches[k][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs(switches, cube));	
	}
	
	public static String cubeToString(int[] cube) {
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) sb.append(cube[i]).append(" ");
		return sb.toString();
	}
	
	public static boolean isEqual(int[] cube) {
		for(int i = 2; i <= N; i++) {
			if(cube[i] != cube[i-1]) {
				return false;
			}
		}
		return true;
	}
	
	public static int bfs(int[][] switches, int[] cube) {
		Set<String> visited = new HashSet<>();
		visited.add(cubeToString(cube));
		Queue<int[]> cubeQ = new LinkedList<>();
		cubeQ.add(cube);
		int depth = 0;
		while(!cubeQ.isEmpty()) {
			int qSize = cubeQ.size();
			while(qSize-- > 0) {				
				int[] curCube = cubeQ.poll();
				if(isEqual(curCube)) return depth;
				
				for(int k = 1; k <= K; k++) {
					int[] nextCube = Arrays.copyOf(curCube, N+1);
					for(int cubeNum : switches[k]) {
						nextCube[cubeNum] = (nextCube[cubeNum] + k) % 5;
					}
					String cubeStr = cubeToString(nextCube);
					if(!visited.contains(cubeStr)) {
						visited.add(cubeStr);
						cubeQ.add(nextCube);
					}
				}
			}
			depth++;
		}
		return -1;
	}

}
