package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1525_Set {
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				sb.append(st.nextElement());
			}
		}
		
		String origin = sb.toString();
		String sorted = "123456780";

		Queue<Puzzle> q = new LinkedList<>();
		q.offer(new Puzzle(0, origin));

		// puzzle의 상태를 저장할 Set 객체
		Set<String> visited = new HashSet<>();
		visited.add(origin);

		while (!q.isEmpty()) {
			Puzzle item = q.poll();
			// 정렬되었을 경우 출력하고 프로그램 종료
			if(sorted.equals(item.str)) {
				System.out.println(item.cnt);
				return;
			}
			
			// 상 하 좌 우 4방향 BFS
			for (int d = 0; d < 4; d++) {
				String moved = swap(item.str, d);
				// 이미 움직여본 경우의 수가 아닐 경우
				if (moved != null && !visited.contains(moved)) {
					// queue에 moved str과 움직인 횟수를 추가하고
					q.offer(new Puzzle(item.cnt+1, moved));
					// 움직여본 경우의 수 map에 저장한다
					visited.add(moved);
				}
			}
		}

		System.out.println(-1);
	}
	
	static class Puzzle {
		int cnt;
		String str;
		public Puzzle(int cnt, String str) {
			this.cnt = cnt;
			this.str = str;
		}
	}

	public static String swap(String str, int d) {
		// 0의 위치를 찾고 3x3 2차원배열에서의 위치를 찾는다.
		int idx = str.indexOf("0");
		int r = idx / 3;
		int c = idx % 3;

		int nr = r + deltas[d][0];
		int nc = c + deltas[d][1];
		int nextIdx = nr * 3 + nc;
		
		// '0'과 swap할 방향이 3x3 내부일때만
		if (0 <= nr && nr < 3 && 0 <= nc && nc < 3) {
			StringBuilder sb = new StringBuilder(str);
			sb.setCharAt(idx, str.charAt(nextIdx));
			sb.setCharAt(nextIdx, '0');
			return sb.toString();
		}

		// swap할 방향이 3x3 바깥일 경우 null반환
		return null;
	}

}
