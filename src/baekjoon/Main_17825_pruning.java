package baekjoon;

import java.io.*;
import java.util.*;

public class Main_17825_pruning {
	static class Space {
		public static final Space lastSpace = new Space(0);
		private int value;
		private Space nextRed;
		private Space nextBlue;
		
		public Space(int value) {
			this.value = value;
		}
		
		
		public Space attachRed(int value) {
			return attachRed(new Space(value));
		}
		public Space attachRed(Space space) {
			return nextRed = space;
		}
		
		public Space attachBlue(int value) {
			return attachBlue(new Space(value));
		}
		public Space attachBlue(Space space) {
			return nextBlue = space;
		}
		
		public Space next(int n) {
			return next(0, n);
		}
		private Space next(int cur, int n) {
			if(cur == n) {
				return this;
			}
			else if(nextRed == null) {
				return lastSpace;
			}
			// cur == 0 이면 출발하는 점이고 파란색 화살표로 진행가능.
			else if(cur == 0 && nextBlue != null) {
				return nextBlue.next(cur+1, n);
			}
			else {
				return nextRed.next(cur+1, n);
			}
		}
		
		public boolean isOverlap(Space[] arr, int len) {
			int cnt = 0;
			for(int i = 0; i < len; i++) {
				// 참조값이 같다면? 겹치는 것
				if(this == arr[i]) {
					if(++cnt == 2) {
						return true;
					}
				}
			}
			return false;
		}
	}
	
	static int[] dice;
	static int ans;
	static Space startSpace;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		dice = new int[10];
		for(int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		// space의 배열 생성해서 재사용할 목적
		Space[] space = new Space[41];
		for(int value = 0; value < 41; value++) 
			space[value] = new Space(value);
		
		// 2부터 40까지 바깥쪽 둘레를 연결하기위한 코드
		Space tail = space[0];
		// 2의 배수인 칸들을 연결한다.
		for(int value = 2; value <= 40; value += 2) {
			tail = tail.attachRed(space[value]);
		}
		
		// 10 -> 13 -> 16 -> 19 -> 25 연결
		space[10].attachBlue(13).attachRed(16).attachRed(19).attachRed(space[25]);
		// 20 -> 22 -> 24 -> 25 연결
		space[20].attachBlue(22).attachRed(24).attachRed(space[25]);
		// 30 -> 28 -> 27 -> 26 -> 25 연결
		space[30].attachBlue(28).attachRed(27).attachRed(26).attachRed(space[25]);
		
		// 25 -> 30 -> 35 -> 40 연결
		space[25].attachRed(30).attachRed(35).attachRed(space[40]);

		// 백트래킹을 적용하기 위한 음의 점수 방법(1)
		ans = 400;
		
		// 윷놀이 시작
		startSpace = space[0];
		play(0, new Space[4], 0, 0);
		
		// 백트래킹을 적용하기 위한 음의 점수 방법(2)
		System.out.println(400 - ans);

		
		br.close();
	}
	
	public static void play(int diceIdx, Space[] piece, int pieceCnt, int score) {
		// 백트래킹(가지치기)
		if(score > ans) return;
		
		if(diceIdx == 10) {
			ans = Math.min(ans, score);
			return;
		}
		
		// 기존에 나간 말들로 움직이기
		for(int idx = 0; idx < pieceCnt; idx++) {
			// 도착칸에 있는 말은 움직이지 않는다.
			if(piece[idx] == Space.lastSpace) continue;
			
			Space[] nextPiece = Arrays.copyOf(piece, 4);
			// 현재 주사위 칸 만큼 움직이기
			nextPiece[idx] = nextPiece[idx].next(dice[diceIdx]);
			// 도착한 칸이 마지막 칸이 아닌데 다른 말이 있다면 취소
			if(nextPiece[idx] != Space.lastSpace && nextPiece[idx].isOverlap(nextPiece, pieceCnt)) {
				continue;
			}
			// 도착한 지점의 수를 더하고 다음 주사위로 넘어간다
			play(diceIdx+1, nextPiece, pieceCnt, score + (40 - nextPiece[idx].value));		
						
		}
		
		// 남은 말이있다면 새로 꺼내서 움직이기
		if(pieceCnt < 4) {
			Space[] nextPiece = Arrays.copyOf(piece, 4);
			// 새로운 말을 꺼내서 주사위 칸 만큼 움직이기
			nextPiece[pieceCnt] = startSpace.next(dice[diceIdx]);
			// 도착한 칸에 다른 말이 있다면 취소
			if(nextPiece[pieceCnt].isOverlap(nextPiece, pieceCnt+1)) {
				return;
			}
			// 도착한 지점의 수를 더하고 다음 주사위로 넘어간다
			play(diceIdx+1, nextPiece, pieceCnt+1, score +(40 - nextPiece[pieceCnt].value));		
			
		}
		
		
	}

}
