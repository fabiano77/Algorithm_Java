package baekjoon;

import java.io.*;
import java.util.*;

public class Main_13460 {
	static int N, M;
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static boolean clear = false;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		
		for(int r = 0; r < N; r++) {
			sb.append(br.readLine());
		}
		String map = sb.toString();
		// . : 빈칸
		// # : 벽
		// O : 구명
		// R : 빨간 구슬 (1개) -> 성공
		// B : 파란 구슬 (1개) -> 실패
		
		// BFS
		
		Queue<String> q = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		q.offer(map);
		visited.add(map);
		int moveCnt = 0;
		
		while(!q.isEmpty() && moveCnt < 10) {
			int qSize = q.size();
			moveCnt++;
			
			for(int i = 0; i < qSize; i++) {
				map = q.poll();
				
				for(int d = 0; d < 4; d++) {
					String next = move(map, d);
					
					// 파란구슬이 골인하지 않았고 방문하지 않았던 경우면 queue에 넣고 bfs진행
					if(next != null && !visited.contains(next)) {
						q.offer(next);
						visited.add(next);
					}
					// 빨간 구슬을 골인한 경우 출력후 종료
					if(clear) {
						System.out.println(moveCnt);
						return;
					}
				}
			}
		}

		System.out.println(-1);
	}

	// 판을 기울여 구슬들을 움직인다
	public static String move(String map, int d) {
		// 2차원 배열에서의 row, col 구함
		int redIdx = map.indexOf('R');
		int blueIdx = map.indexOf('B');
		
		int redR = redIdx / M;
		int redC = redIdx % M;
		
		int blueR = blueIdx / M;
		int blueC = blueIdx % M;
		
		// 먼저 굴릴 구슬을 정함 -> 기울일 방향의 벽과 가까운 구슬부터
		boolean redFirst = false;
		if(d == 0 && redR < blueR) {	//북
			redFirst = true;
		}
		else if(d == 1 && redC > blueC) {	//동
			redFirst = true;
		}
		else if(d == 2 && redR > blueR) {	//남
			redFirst = true;
		}
		else if(d == 3 && redC < blueC) {	//서
			redFirst = true;
		}

		
		// 구슬 굴리는 순서대로
		if(redFirst) {
			map = moveMarble(map, redR, redC, d, true);
			map = moveMarble(map, blueR, blueC, d, false);
		}
		else {
			map = moveMarble(map, blueR, blueC, d, false);
			map = moveMarble(map, redR, redC, d, true);
		}
		
		return map;
	}
	
	// 지정 방향으로 구슬 굴리기
	public static String moveMarble(String map, int r, int c, int d, boolean isRed) {
		if(map == null) return null;
		
		StringBuilder sb = new StringBuilder(map);
		
		while(true) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];

			// 구멍일 경우
			if(sb.charAt(toIndex(nr, nc))=='O') {
				// 빨간 구슬일 경우
				if(isRed) {
					clear = true;
					sb.setCharAt(toIndex(r, c), '.');
					return sb.toString();
				}
				// 파란 구슬일 경우
				else {
					// 동시에 들어간 경우 clear를 취소시킴.
					if(clear) {
						clear = false;
					}
					return null;
				}
			}
			// 벽을 만난 경우
			else if(sb.charAt(toIndex(nr, nc))=='#' || sb.charAt(toIndex(nr, nc))=='B' || sb.charAt(toIndex(nr, nc))=='R' ) {
				return sb.toString();
			}
			// 허공인 경우 구슬을 한칸 이동
			else {
				sb.setCharAt(toIndex(nr, nc), sb.charAt(toIndex(r, c)));
				sb.setCharAt(toIndex(r, c), '.');
				r = nr;
				c = nc;
			}
		}
	}
	
	// 2차원배열의 r, c을 1차원 index로
	public static int toIndex(int r, int c) {
		return r*M + c;
	}
	

}
