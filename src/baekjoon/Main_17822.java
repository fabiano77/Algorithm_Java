package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17822 {
	static int N;	// 원판의 수
	static int M;	// 원판의 정수 개수
	static int T; 	// 회전 수
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public static void main(String[] args) throws IOException{
		System.setIn(Main_17822.class.getResourceAsStream("input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 데이터 입력 처리
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		int[][] disks = new int[N+1][];
		for(int n = 1; n <= N; n++) disks[n] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		
		for(int t = 0; t < T; t++) {
			// x d k 입력받음
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			// x의 배수인 원판들을 d방향으로 k칸만큼 회전
			for(int i = x; i <= N; i+=x) rotate(disks[i], d, k);
			
			// 인접하면서 수가 같은 것들을 모두 찾는다
			boolean isEquals = false;
			boolean[][] visited = new boolean[N+1][M];
			for(int i = 1; i <= N; i++) {
				for(int j = 0; j < M; j++) {
					// 인접하며 같은 수를 찾는다면 isEquals에 true 대입.
					if(bfs(disks, visited, i, j) == true) isEquals = true; 
				}
			}
			
			// 인접하며 같은 수가 없었다면
			if(!isEquals) {
				adjust(disks, getAvg(disks));
			}
			
		}
		
		// 원판의 숫자들 출력
		System.out.println(getTotal(disks));
	}
	
	public static class Point{
		public int disk;
		public int numIdx;
		public Point(int disk, int numIdx) {
			this.disk = disk;
			this.numIdx = numIdx;
		}
	}
	
	private static void printDisks(int[][] disks) {
		for(int i = 1; i <= N; i++) {
			System.out.println(Arrays.toString(disks[i]));
		}
		System.out.println();
	}
	
	private static void rotate(int[] disk, int dir, int cnt) {
		// 회전시키는 것을 cnt만큼 반복
		for(int c = 0; c < cnt; c++) {
			
			// d == 0 시계, d == 1 반시계
			if(dir == 0) {
				int temp = disk[M-1];
				for(int i = M-1; i > 0; i--) {
					disk[i] = disk[i-1];
				}
				disk[0] = temp;
			}
			else {
				int temp = disk[0];
				for(int i = 0; i < (M-1); i++) {
					disk[i] = disk[i+1];
				}
				disk[M-1] = temp;
			}			
		}
	}
	
	private static boolean bfs(int[][] disks, boolean[][] visited, int diskIdx, int numIdx) {
		if(visited[diskIdx][numIdx] || disks[diskIdx][numIdx] == 0) {
			return false;
		}
		visited[diskIdx][numIdx] = true;
		int curNumber = disks[diskIdx][numIdx];
		boolean equal = false;
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(diskIdx, numIdx));
		while(!q.isEmpty()) {
			Point cur = q.poll();
			for(int[] delta : deltas) {
				int nextDisk = cur.disk + delta[0];
				int nextNumIdx = cur.numIdx + delta[1];
				if(nextDisk <= 0 || nextDisk > N) {
					continue;
				}
				if(nextNumIdx == -1) nextNumIdx = M-1;
				else if (nextNumIdx == M) nextNumIdx = 0;
				
				if(visited[nextDisk][nextNumIdx] || disks[nextDisk][nextNumIdx] != curNumber) {
					continue;
				}
				equal = true;
				disks[nextDisk][nextNumIdx] = 0;
				visited[nextDisk][nextNumIdx] = true;
				q.offer(new Point(nextDisk, nextNumIdx));
				
			}
			
		}
		if(equal) {
			disks[diskIdx][numIdx] = 0;
			return true;
		}
		
		
		return false;
	}
	
	private static int getTotal(int[][] disks) {
		int sum = 0;
		for(int i = 1; i <= N; i++) {
			for(int num : disks[i]) {
				sum += num;
			}
		}
		return sum;
	}
	
	private static double getAvg(int[][] disks) {
		int cnt = 0;
		for(int i = 1; i <= N; i++) {
			for(int num : disks[i]) {
				if(num != 0) cnt++;
			}
		}
		return (double)getTotal(disks)/cnt;
	}
	
	private static void adjust(int[][] disks, double avg) {
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				if(disks[i][j] != 0) {
					if(disks[i][j] > avg) {
						disks[i][j]--;
					} else if (disks[i][j] < avg){
						disks[i][j]++;
					}
				}
			}
		}
	}

}
