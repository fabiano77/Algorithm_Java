package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1920 {
	static int N;
	static int M;
	static int[] arr;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException{
		System.setIn(Main_1920.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(arr);
		M = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		for(int m : Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()){
			int idx = Arrays.binarySearch(arr, m);
			if(idx < 0) {
				sb.append(0+"\n");
			}
			else {
				sb.append(1+"\n");
			}
		}
		System.out.println(sb.toString());
	}

}
