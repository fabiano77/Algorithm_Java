package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1041 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		long N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] sideNum = new int[6];
		int maxSide = 0;
		int sumSide = 0;
		for(int i = 0; i < 6; i++) {
			sideNum[i] = Integer.parseInt(st.nextToken());
			sumSide += sideNum[i];
			maxSide = Math.max(maxSide, sideNum[i]);
		}
		
		int[] side1 = {0, 1, 2, 3, 4, 5};
		
		int[][] side2 = {{0, 1}, {0, 2}, {0, 3}, {0, 4},
						{5, 1}, {5, 2}, {5, 3}, {5, 4},
						{1, 2}, {2, 4}, {4, 3}, {3, 1}};
		
		int[][] side3 = {{0, 1, 2}, {0, 1, 3}, {0, 2, 4}, {0, 3, 4},
				{5, 1, 2}, {5, 1, 3}, {5, 2, 4}, {5, 3, 4}};
		
		int minOfSide1 = Integer.MAX_VALUE;
		for(int sideIndex : side1) {
			minOfSide1 = Math.min(minOfSide1, sideNum[sideIndex]);
		}
		
		int minSumOfSide2 = Integer.MAX_VALUE;
		for(int[] sideIndexies : side2) {
			int sum = 0;
			for(int sideIndex : sideIndexies) {
				sum += sideNum[sideIndex];
			}
			minSumOfSide2 = Math.min(minSumOfSide2, sum);
		}
		
		int minSumOfSide3 = Integer.MAX_VALUE;
		for(int[] sideIndexies : side3) {
			int sum = 0;
			for(int sideIndex : sideIndexies) {
				sum += sideNum[sideIndex];
			}
			minSumOfSide3 = Math.min(minSumOfSide3, sum);
		}
		
		long total = 0;
		if(N == 1) 	{
			total = sumSide - maxSide;
		}
		else  		{
			total += minSumOfSide3 * 4;
			total += minSumOfSide2 * ((8 * (N-2)) + 4);
			total += minOfSide1 * ((5 * (N-2) * (N-2)) + (4 * (N-2)));
		}

		System.out.println(total);

	}

}

