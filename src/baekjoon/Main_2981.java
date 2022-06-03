package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2981 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(nums);
		
		int gcdVal = nums[1] - nums[0];
		
		for(int i = 2; i < N; i++) {
			gcdVal = gcd(gcdVal, nums[i] - nums[i-1]);
		}
		
		for(int i = 2; i <= gcdVal; i++) {
			if(gcdVal % i == 0) {
				System.out.print(i+" ");
			}
		}
		
		
		br.close();
	}
	
	public static int gcd(int a, int b) {
		while(b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

}
