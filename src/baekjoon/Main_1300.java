package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1300 {
	static long N;
	static long K;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Long.parseLong(br.readLine());
		K = Long.parseLong(br.readLine());
		
		System.out.println(binSearch(1, N*N));
	}
	
	public static long binSearch(long start, long end) {
		while(start <= end) {
			long mid = (start+end)/2;
			
			// mid가 B[]행렬에서 가지는 순서
			long order = getOrder(mid);
			
			// mid의 순서가 K인데 mid가 실제로 있을 경우
			if(order == K && isIn(mid)) {
				return mid;
			}
			// mid 의 순서가 K보다 작은 경우
			else if(order < K) {
				start = mid+1;
			}
			// mid 의 순서가 K보다 큰 경우
			else { 
				// mid-1의 순서는 K보다 작을 경우, mid가 K번째 이다.
				if(getOrder(mid-1) < K) {
					return mid;
				}
				end = mid-1;
			}
		}
		
		return -1;
	}
	
	// 2차원배열에서 row의 요소들은 row의 배수이므로
	// num을 row 로 나누어 준 몫이 row에서 num보다 작은 요소의 개수이다.
	public static long getOrder(long num) {
		long order = 0;
		for(long r = 1; r <= N; r++) {
			long quotient = num / r;
			if(quotient > N) order += N;
			else order += quotient;
		}
		return order;
	}
	
	// 해당 배열에 실제로 존재하는 수인지 확인하는 메소드
	public static boolean isIn(long num) {
		for(long r = 1; r <= N; r++) {
			long quotient = num / r;
			if(quotient <= N && num % r == 0) {
				return true; 
			}
		}
		return false;
	}

}
