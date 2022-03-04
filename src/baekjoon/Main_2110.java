package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2110 {
	static int N;
	static int C;
	static long[] positions;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		positions = new long[N];

		for(int n = 0; n < N; n++) {
			positions[n] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(positions);
		
		System.out.println(binSearch(0, 1_000_000_000));
		

	}
	
	public static long binSearch(long start, long end) {
		long max = -1;
		while(start <= end) {
			long mid = (start + end) / 2;
			
			// mid간격으로 와이파이 설치 가능할 때
			if(checkInterval(mid)) {
				max = Math.max(max, mid);
				start = mid+1;
			}
			else {
				end = mid-1;
			}
			
		}
		
		return max;
	}
	
	public static boolean checkInterval(long interval) {
		long lastWifi = positions[0];
		int wifiCnt = 1;
		for(int idx = 1; idx < N; idx++) {
			if((positions[idx] - lastWifi) >= interval) {
				lastWifi = positions[idx];
				wifiCnt++;
			}
		}
		
		return wifiCnt >= C;
	}

}
