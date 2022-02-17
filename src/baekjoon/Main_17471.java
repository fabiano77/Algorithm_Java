package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17471 {
	static int N;
	static int[] populations;
	static boolean[][] graph;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 구역의 개수
		N = Integer.parseInt(br.readLine());
		populations = new int[N + 1];
		graph = new boolean[N + 1][N + 1];

		// 각 구역의 인구수 입력 받음
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			populations[n] = Integer.parseInt(st.nextToken());
		}

		// 그래프 연결 정보 입력받음
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			int adj = Integer.parseInt(st.nextToken());
			for (int i = 0; i < adj; i++) {
				graph[n][Integer.parseInt(st.nextToken())] = true;
			}
		}
		
		// 인구 차이의 최소값
		int minDif = Integer.MAX_VALUE;

		// 모든 부분집합의 개수
		int subsetCnt = (int) Math.pow(2, N);
		// 0과 subsetCnt-1은 제외(공집합과, 전체집합을 제외)
		for (int subset = 1; subset < subsetCnt - 1; subset++) {
			// 부분집합으로 뽑힌(비트가 1인)구역

			// 전체집합
			int universalSet = (1 << N) - 1;

			// 해당 부분집합의 여집합
			int complementSet = universalSet ^ subset;

			// 부분집합과 여집합이 각자의 집합내에서 연결되어있다면
			if (checkConnect(subset) && checkConnect(complementSet)) {
				// 인구수 차이의 최소값을 갱신
				minDif = Math.min(minDif, Math.abs(getTotalPopulration(subset) - getTotalPopulration(complementSet)));
			}
		}
		
		// 최종 결과 출력
		if(minDif == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {			
			System.out.println(minDif);
		}

	}

	// 현재 부분집합이 연결되었는지 논리값을 반환한다.
	public static boolean checkConnect(int subsetFlag) {
		return subsetFlag == dfsGetConnectedFlag(getFirstBit(subsetFlag), subsetFlag, 0);
	}

	// dfs탐색으로 현재 연결된 구역을 flag로 반환한다.
	public static int dfsGetConnectedFlag(int start, int subsetFlag, int visitedFlag) {
		int retFlag = (1 << start - 1);
		visitedFlag |= (1 << start - 1);
		// 1번구역부터 N번구역까지중에
		for (int a = 1; a <= N; a++) {
			// 방문한 구역이라면 continue
			if ((1 << a - 1) == (visitedFlag & (1 << a - 1)))
				continue;
			// a번째 구역이 현재 부분집합에있고 연결되어있다면 DFS로 탐색
			if ((1 << a - 1) == (subsetFlag & (1 << a - 1)) && graph[start][a]) {
				retFlag |= dfsGetConnectedFlag(a, subsetFlag, visitedFlag);
			}
		}

		return retFlag;
	}

	// 현재 부분집합에서 가장 작은 비트를 구함
	public static int getFirstBit(int subset) {
		// 가장 최소 비트 구함.
		int first = subset & (-subset);
		int firstSection = 0;
		for (int i = 1; i <= N; i++) {
			if (first == (first & 1 << i - 1)) {
				firstSection = i;
				break;
			}
		}
		return firstSection;
	}

	// 현재 부분집합의 인구들의 합계 구함
	public static int getTotalPopulration(int subset) {
		int total = 0;
		for(int a = 1; a <= N; a++) {
			if((1 << a - 1) == (subset & (1 << a - 1))) {
				total += populations[a];
			}
		}
		return total;
	}

}
