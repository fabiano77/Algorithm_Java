package baekjoon;

import java.io.*;
import java.util.*;

public class Main_9466 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] choices = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				choices[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] isTeam = new int[N+1];	// -1: 팀 아님, 1: 팀, 0: 아직모름
			boolean[] visited = new boolean[N+1];
			
			for(int i = 1; i <= N; i++) {
				if(isTeam[i] == 0) {
					makeTeam(choices, visited, isTeam, i, N, new ArrayList<>());
				}
			}
			
			int cnt = 0;
			for(int i = 1; i <= N; i++) if(isTeam[i] == -1) cnt++;
			
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	
	public static void makeTeam(int[] choices, boolean[] visited, int[] isTeam, int current, int N, List<Integer> Route) {
		Route.add(current);
		visited[current] = true;
		
		int next = choices[current];
		// 현재 경로에 포함되어있는 점이라면 팀이거나 팀이 아님을 알 수 있다.
		if (visited[next]) {
			// 현재 순환되는 경로에 있는 사람들 끼리만 팀이 된다.
			while(isTeam[next] == 0) {
				isTeam[next] = 1;
				next = choices[next];
			}
			// 이미 선택을 거쳤지만, 순환에 포함되지 않으면 팀이 되지 않음.
			for(int node : Route) {
				if(isTeam[node] == 0) {
					isTeam[node] = -1;
				}
			}
		}
		else if(isTeam[next] != 0) {
			// 팀이거나 팀이 아닌 사람을 지목할경우 나와 나를 지목한 인원은 무조건 팀이 없음
			for(int node : Route) {
				isTeam[node] = -1;		
			}
		}
		else {
			makeTeam(choices, visited, isTeam, choices[current], N, Route);
		}
		
	}

}
