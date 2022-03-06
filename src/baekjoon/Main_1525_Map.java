package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1525_Map {
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

		// puzzle상태와 움직인 횟수를 저장할 map 객체
		Map<String, Integer> cases = new HashMap<>();
		cases.put(origin, 0);

		Queue<String> q = new LinkedList<>();
		q.offer(origin);

		while (!q.isEmpty()) {
			String item = q.poll();
			// 상 하 좌 우 4방향을 swap해봄
			for (int d = 0; d < 4; d++) {
				String moved = swap(item, d);
				// 이미 움직여본 경우의 수가 아닐 경우
				if (moved != null && !cases.containsKey(moved)) {
					// 움직여본 경우의 수 map에 저장한다
					cases.put(moved, cases.get(item) + 1);
					q.offer(moved);
				}
			}
		}

		String sorted = "123456780";

		// sorted가 움직일 수 있는 경우의수에 있을 경우
		if (cases.containsKey(sorted)) {
			System.out.println(cases.get(sorted));
		} else {
			System.out.println(-1);
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
