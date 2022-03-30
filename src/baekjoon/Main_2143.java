package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2143 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// t에 대한 입력
		int T = Integer.parseInt(br.readLine());	// ~1,000,000,000
		
		// 배열1에 대한 입력
		int N = Integer.parseInt(br.readLine());	// ~1,000
		long[] arr1 = new long[N];
		// 배열1의 누적합 구하기
		long[] prefixSum1 = new long[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());	// ~1,000,000,000
			prefixSum1[i+1] = prefixSum1[i] + arr1[i];
		}
		// 누적합을 이용하여 가능한 모든 부배열의 합을 모두 구하기
		long[] subArraySums1 = new long[N*(N+1)/2];
		int idx1 = 0;
		for(int start = 0; start < N; start++) {
			for(int end = start+1; end <= N; end++) {
				subArraySums1[idx1++] = prefixSum1[end]-prefixSum1[start];
			}
		}
		// 부배열의 합 배열 정렬
		Arrays.sort(subArraySums1);
		
		
		// 배열2에 대해 동일 과정.
		int M = Integer.parseInt(br.readLine());	// ~1,000
		long[] arr2 = new long[M];
		long[] prefixSum2 = new long[M+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
			prefixSum2[i+1] = prefixSum2[i] + arr2[i];
		}
		
		long[] subArraySums2 = new long[M*(M+1)/2];
		int idx2 = 0;
		for(int start = 0; start < M; start++) {
			for(int end = start+1; end <= M; end++) {
				subArraySums2[idx2++] = prefixSum2[end]-prefixSum2[start];
			}
		}
		Arrays.sort(subArraySums2);
		
		
		// 합이 T가되는 부배열의 개수를 구하는 과정
		long ans = 0;
		for(long sum1 : subArraySums1) {
			long target = T - sum1;
			
			// lower bound
			int leftIdx = leftBinSearch(subArraySums2, target);
			// upper bound
			int rightIdx = rightBinSearch(subArraySums2, target);

			// 배열2의 부배열합중에 여러개일 경우, right-left로 개수를 구함.
			if(leftIdx >= 0 && rightIdx >= 0) {				
				ans += 1 + (rightIdx - leftIdx);
			}
			
		}
		System.out.println(ans);

		

	}
	
	public static int leftBinSearch(long[] array, long key) {
		int start = 0;
		int end = array.length-1;
		int retIdx = -1;
		while(start <= end) {
			int mid = (start+end)/2;
			if(array[mid] == key) {
				retIdx = mid;
				end = mid-1;
			}
			else if(array[mid] > key) {
				end = mid-1;
			}
			else {
				start = mid+1;
			}
			
		}
		
		return retIdx;
	}
	public static int rightBinSearch(long[] array, long key) {
		int start = 0;
		int end = array.length-1;
		int retIdx = -1;
		while(start <= end) {
			int mid = (start+end)/2;
			if(array[mid] == key) {
				retIdx = mid;
				start = mid+1;
			}
			else if(array[mid] > key) {
				end = mid-1;
			}
			else {
				start = mid+1;
			}
			
		}
		
		return retIdx;
	}

}