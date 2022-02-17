package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1561 {
	static int N;
	static int M;
	static int[] rides;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 탑승하는 인원 수
		N = Integer.parseInt(st.nextToken());
		
		// 놀이기구 개수, 정보 입력받음
		M = Integer.parseInt(st.nextToken());
		rides = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int m = 0; m < M; m++) {
			rides[m] = Integer.parseInt(st.nextToken());
		}
		
		// 가능한 최대 시각 (30분 * 20억명)
		long timeLimit = 30 * 2_000_000_000L; 
		
		// 이분 탐색
		binSearch(0, timeLimit);
	}
	
	public static void binSearch(long start, long end) {
		long middle;
		// 이분탐색 반복
		while (start <= end) {
			middle = (start+end)/2;
			
			// 현재 시각(middle)까지 탑승한 사람 수
			long cnt = getCount(middle);

			
			// 탑승 인원이 N보다 적으면 시간을 뒤로
			if(cnt < N) {
				start = middle+1;
			}
			// N번째 사람이 middle 시각에 탑승했다면
			else if((cnt - M) < N ) {
				int nowRideCnt = 0;
				for(int m = M-1; m >= 0; m--) {
					// 현재 m번째 놀이기구에서 사람이 탑승했다면
					if((middle%rides[m]) == 0) {
						if(cnt - nowRideCnt == N) {						
							// 놀이기구 출력 후 종료
							System.out.println(m+1);
							return;
						}
						nowRideCnt++;
						
					}
				}
				end = middle-1;
			}
			// 탑승 인원이 N보다 많으면 시간을 뒤로
			else {
				end = middle-1;
			}
			
		}
		return;
	}
	
	// 현재 시각(time)까지 탑승한 사람 수
	public static long getCount(long time) {
		long cnt = 0;
		for(int m = 0; m < M; m++) {
			cnt += (time/rides[m])+1;
		}
		return cnt;
	}
	
	// 정확히 현재 시각에 탑승한 인원 수
	public static int getNowRideCount(long time) {
		int cnt = 0;
		for(int m = 0; m < M; m++) {
			if(time%rides[m] == 0) cnt++;
		}
		return cnt;
	}

}

