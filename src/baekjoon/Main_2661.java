package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2661 {
	static int N;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		solve(arr, 0);
		
		br.close();
	}
	
	public static boolean solve(int[] arr, int i) {
		if(i == N) {
			Arrays.stream(arr)
				.forEach(System.out::print);
			return true;
		}
		int len = i + 1;
		int half = len/2;
		
		for(int num = 1; num <= 3; num++) {
			arr[i] = num;
			boolean bad = false;

			for(int j = 1; j <= half; j++) {
				bad = true;
				for(int idx = 0; idx < j; idx++) {
					if(arr[i-j+idx+1] != arr[i-(2*j)+idx+1]) {
						bad = false;
						break;
					}
				}
				if(bad) {
					break;
				}
			}
			if(!bad) {
				if(solve(arr, i+1)) {
					return true;
				}
			}
		}
		return false;
	}

}
