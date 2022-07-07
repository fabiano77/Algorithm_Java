package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1082 {
	
	static class Numbers implements Comparable<Numbers>{
		int len;
		int[] nums;

		public Numbers() {
			this.len = 0;
			this.nums = new int[10];
		}
		
		@Override
		public int compareTo(Numbers o) {
			int ret = 0;
			if(len == 0 || o.len == 0) {
				return (len == 0)? -1 : 1;
			}
			if(nums[0] == len || o.nums[0] == o.len) {
				return (nums[0] == len)?-1:1;
			}
			ret = Integer.compare(len, o.len);
			if(ret == 0) {				
				for(int i = 9; i >= 0; i--) {
					ret = Integer.compare(nums[i], o.nums[i]);
					if(ret != 0) return ret;
				}
			}
			return ret;
		}
		
		public Numbers copy() {
			Numbers o = new Numbers();
			o.len = len;
			o.nums = nums.clone();
			return o;
		}
		
		public void add(int num) {
			len++;
			nums[num]++;
		}
		
		public String getString() {
            if(len == nums[0]) return "0";
			StringBuilder sb = new StringBuilder();
			for(int i = 9; i >= 0; i--) {
				for(int j = 0; j < nums[i]; j++) {
					sb.append(i);
				}
			}
			return sb.toString();
		}

		@Override
		public String toString() {
			return "[" + Arrays.toString(nums) + ", len="+len+"]";
		}
		
	}

	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());	// 1 ~ 10
		st = new StringTokenizer(br.readLine());	// 1 ~ 50
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());	// 1 ~ 50
		
		Numbers[][] dp = new Numbers[N][M+1];
		
		for(int j = 0; j <= M; j++) {
			dp[0][j] = new Numbers();
		}
		
		
		// N은 사용한 방번호 숫자
		// M은 사용한 가격
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j <= M; j++) {
				dp[i][j] = new Numbers();
				for(int k = 0; k <= i; k++) {					
					// i번째 숫자를 살 수 있는 경우
					if(dp[i][j].compareTo(dp[k][j]) < 0) {
						dp[i][j] = dp[k][j];
					}
					if(arr[i] <= j) {
						Numbers temp = dp[k][j-arr[i]].copy();
						temp.add(i);
						if(temp.compareTo(dp[i][j]) > 0) {
							dp[i][j] = temp;
						}
					}
				}
			}
		}
		
		// 파는 숫자 0~N-1
		// 가격 p0 ~ pn-1
		// M원을 모두 사용
		
		System.out.println(dp[N-1][M].getString());
		
		br.close();
		
	}

}
