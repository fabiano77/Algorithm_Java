package baekjoon;

import java.io.*;
import java.util.*;

public class Main_16401 {
	
	static int N, M;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); // 조카의 수	~ 1,000,000
		N = Integer.parseInt(st.nextToken()); // 과자의 수	~ 1,000,000
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			// 나눌 수는 있지만 합칠 수는 없다.
		}
		

		Arrays.sort(arr);
		System.out.println(binSearch(1, 1_000_000_000, arr));

		
		
		

		br.close();
	}
	
	public static int binSearch(int start, int end, int[] arr) {
		int retVal = 0;
		while(start <= end) {
			int mid = (start+end)/2;
			
			int cnt = getCnt(mid, arr);
			
			if(cnt >= M) {
				start = mid+1;
				retVal = mid;
			}
			else {
				end = mid-1;
			}
			
			
		}
		
		
		return retVal;
	}
	
	public static int getCnt(int len, int[] arr) {
		int cnt = 0;
		for(int num : arr) {
			cnt += (int)(num/len);
		}
		return cnt;
	}

}
