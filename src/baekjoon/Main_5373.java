package baekjoon;

import java.io.*;
import java.util.*;

public class Main_5373 {
	
	// 큐브의 한 면(face)을 표현한 객체
	static class Face {
		
		// Face의 정적 멤버 클래스로 한 칸의 색깔 나타내는 객체
		static class Square {
			final char color;
			public Square(char color) {
				this.color = color;
			}
			@Override
			public String toString() {
				return "Square [color=" + color + "]";
			}
		}
		
		// Face의 모서리 4방향 라인 칸들의 index를 나타낸 정적 배열
		static int[][][] lineIdx = {{{0, 2}, {0, 1}, {0, 0}},
									{{2, 2}, {1, 2}, {0, 2}},
									{{2, 0}, {2, 1}, {2, 2}},
									{{0, 0}, {1, 0}, {2, 0}}};
		
		// Face가 가지고있는 9개의 정사각형
		Square[][] squares = new Square[3][3];
		
		// Face를 한 색깔로 초기화.
		public Face(char color) {
			Square colorSquare = new Square(color);
			int idx = 0;
			for(int r = 0; r < 3; r++) {
				for(int c = 0; c < 3; c++) {
					squares[r][c] = colorSquare;
					//squares[r][c] = new Square((char)(color+(idx++))); //디버깅용
				}
			}
		}
		
		// Face를 회전시키는 메서드
		public void rotate(boolean right) {
			Square[][] rotatedSquares = new Square[3][3];
			if(right) {
				for(int r = 0; r < 3; r++) {
					for(int c = 0; c < 3; c++) {
						rotatedSquares[c][2-r] = squares[r][c];
					}
				}
			}
			else {
				for(int r = 0; r < 3; r++) {
					for(int c = 0; c < 3; c++) {
						rotatedSquares[2-c][r] = squares[r][c];
					}
				}
			}
			squares = rotatedSquares;
		}
		
		// 원하는 방향의 모서리 line(3칸)을 반환
		public Square[] getLine(int num) {
			Square[] ret = new Square[3];
			int len = 0;
			for(int[] idx : lineIdx[num]) {
				ret[len++] = squares[idx[0]][idx[1]];
			}
			return ret;
		}
		
		// 원하는 방향의 모서리 line(3칸)을 새로 설정함
		public void setLine(int num, Square[] line) {
			int len = 0;
			for(int[] idx : lineIdx[num]) {
				squares[idx[0]][idx[1]] = line[len++];
			}
		}
		
		// Face 색깔을 2차원 형태로 출력
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for(int r = 0; r < 3; r++) {
				for(int c = 0; c < 3; c++) {
					sb.append(squares[r][c].color);
				}
				sb.append('\n');
			}
			return sb.toString();
		}
	}
	
	// 큐브 객체
	static class Cube {
		// 회전할 때 main face에 대해 인접하여 모서리에서 line이 같이 돌아가는 {face번호, 방향}들
		final static int[][][] logic = {  {{3, 0}, {5, 0}, {2, 0}, {4, 0}},
									{{3, 2}, {4, 2}, {2, 2}, {5, 2}},
									{{0, 2}, {5, 3}, {1, 2}, {4, 1}},
									{{0, 0}, {4, 3}, {1, 0}, {5, 1}},
									{{0, 3}, {2, 3}, {1, 1}, {3, 1}},
									{{0, 1}, {3, 3}, {1, 3}, {2, 1}}  };
		
		final static String colors  = "wyrogb"; // 색깔 index
		final static String faceIdx = "UDFBLR"; // 방향 index
		final Face[] faces = new Face[6]; // 6면(Face) 객체

		// 6면을 정해진 색으로 초기화
		public Cube() {
			for(int i = 0; i < 6; i++)
				faces[i] = new Face(colors.charAt(i));
		}
		
		// 큐브 조작 메서드
		public void action(String action) {
			int mainFaceIdx = faceIdx.indexOf(action.charAt(0));
			boolean right = (action.charAt(1) == '+');
			
			// mainFace는 시계, 반시계로 회전시킨다.
			faces[mainFaceIdx].rotate(right);
			
			// 인접한 면들의 line을 수집한다.
			Deque<Face.Square[]> deque = new LinkedList<>(); 
			for(int[] info : logic[mainFaceIdx]) {
				deque.offer(faces[info[0]].getLine(info[1]));
			}
			
			// deque를 이용해 시계방향, 반시계방향을 손쉽게 회전한다.
			if(right) {
				deque.addFirst(deque.pollLast());
			}
			else {
				deque.addLast(deque.pollFirst());
			}
			
			// 회전시킨 line들을 다시 set한다.
			for(int[] info : logic[mainFaceIdx]) {
				faces[info[0]].setLine(info[1], deque.poll());
			}
		}

		// 가장 윗면만 출력
		@Override
		public String toString() {
			return faces[0].toString();
		}
		
	}
	

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());
		
		// 테스트 케이스
		while(tc-- > 0) {
			Cube cube = new Cube();
			// 큐브 조작 회수
			int n = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				 cube.action(st.nextToken());
			}
			System.out.print(cube);
		}

		br.close();
		

	}

}
