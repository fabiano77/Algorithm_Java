package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_6603 {
	static int K;
	static int[] numbers;
	static int[] arr;

	public static void main(String[] args) throws IOException{
		//System.setIn(Main_6603.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		while(!input[0].equals("0")) {
			K = Integer.parseInt(input[0]);
			arr = new int[K];
			for(int i = 0; i < K; i++) {
				arr[i] = Integer.parseInt(input[1+i]);
			}
			numbers = new int[6];
			combi(0, 0);
			System.out.println();
			input = br.readLine().split(" ");
		}

	}
	
	private static void combi(int cnt, int start) {
		if(cnt == 6) {
			StringBuilder sb = new StringBuilder();
			for(int idx : numbers) sb.append(arr[idx]+" ");
			System.out.println(sb);
			return;
		}
		
		for(int i = start; i < K; i++) {
			numbers[cnt] = i;
			combi(cnt+1, i+1);
		}
	}

}
