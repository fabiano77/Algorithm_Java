package baekjoon;

import java.io.*;
import java.util.*;

public class Main_19237 {
	static int[][] deltas = {{}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N, M, k;
	
	static class Smell {
		int time;
		int sharkNo;
		public Smell(int time, int shark) {
			this.time = time;
			this.sharkNo = shark;
		}
	}
	static class Shark {
		int no;
		int r;
		int c;
		int dir;
		boolean isAlive = true;
		int[][] directions = new int[5][5];
		
		public Shark(int r, int c, int no) {
			this.r = r;
			this.c = c;
			this.no = no;
			
			for(int i = 0; i < 5; i++) {
				Arrays.fill(this.directions[i], -1);
			}
		}
		
		public String posString() {
			return r + " " + c;
		}

		@Override
		public String toString() {
			return "Shark [no=" + no + ", r=" + r + ", c=" + c + ", dir=" + dir + ", isAlive=" + isAlive + "]";
		}
		
		

		
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// ~ 맵 크기
		M = Integer.parseInt(st.nextToken());	// ~ 1이상 M이하 상어 번호
		k = Integer.parseInt(st.nextToken());	// 상어 k번 이동후 냄새 사라짐
		
		Shark[] sharkArr = new Shark[M+1]; 
		sharkArr[0] = new Shark(-1, -1, -1);
		sharkArr[0].isAlive = false;
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				int number = Integer.parseInt(st.nextToken());
				if(number > 0) {
					sharkArr[number] = new Shark(r, c, number);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int m = 1; m <= M; m++) {
			sharkArr[m].dir = Integer.parseInt(st.nextToken());
		}
		
		for(int m = 1; m <= M; m++) {
			for(int dir = 1; dir <= 4; dir++) {
				st = new StringTokenizer(br.readLine());
				for(int i = 1; i <= 4; i++) {
					sharkArr[m].directions[dir][i] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		Smell[][] smellMap = new Smell[N][N];
		
		
		int ans = -1;
		for(int time = 1; time <= 1000; time++) {
			putSmell(sharkArr, smellMap, time);
			
			moveShark(sharkArr, smellMap, time);
			
			Set<String> visited = new HashSet<>();
			checkOverlap(sharkArr, visited);

			if(M == 1) {
				ans = time;
				break;
			}
		}

		System.out.println(ans);
	}
	
	private static void putSmell(Shark[] sharkArr, Smell[][] smellMap, int time) {
		for(Shark shark : sharkArr) {
			if(shark.isAlive) {
				smellMap[shark.r][shark.c] = new Smell(time, shark.no);
			}
		}
	}
	
	private static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
	
	private static void moveShark(Shark[] sharkArr, Smell[][] smellMap, int time) {
		nextShark:
		for(Shark shark : sharkArr) {
			if(shark.isAlive) {
				int mySmellR = -1;
				int mySmellC = -1;
				int mySmellDir = -1;
				//for(int dir : shark.directions[shark.dir]) {
				for(int d = 1; d <= 4; d++) {
					int dir = shark.directions[shark.dir][d];
					int nr = shark.r + deltas[dir][0];
					int nc = shark.c + deltas[dir][1];
					if(!isIn(nr, nc)) continue;
					
					// 냄새가 없는 경우 (k초 지난 냄새)
					if(smellMap[nr][nc] == null || time - smellMap[nr][nc].time >= k) {
						// 이동
						shark.r = nr;
						shark.c = nc;
						shark.dir = dir;
						continue nextShark;
					}
					// 냄새가 있는데 내 냄새일경우
					else if(mySmellR == -1 && smellMap[nr][nc].sharkNo == shark.no) {
						mySmellR = nr;
						mySmellC = nc;
						mySmellDir = dir;
					}
				}
				shark.r = mySmellR;
				shark.c = mySmellC;
				shark.dir = mySmellDir;
			}
		}
	}
	
	private static void checkOverlap(Shark[] sharkArr, Set<String> visited) {
		for(Shark shark : sharkArr) {
			if(shark.isAlive) {
				String posStr = shark.posString();
				// true 라면 겹치지 않는 것
				// false라면 겹치는 것
				if(!visited.add(posStr)) {
					// 상어 주금
					shark.isAlive = false;
					M--;
				}
			}
		}
	}

}
