package baekjoon;

import java.io.*;
import java.util.*;

public class Main_20437 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			char[] str = br.readLine().toCharArray();
			int K = Integer.parseInt(br.readLine());
			
			int[] count = new int[26];
			
			@SuppressWarnings("unchecked")
			Queue<Integer>[] idxQueue = new Queue[26];
			for(int i = 0; i < 26; i++) {
				idxQueue[i] = new LinkedList<>();
			}
			
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for(int idx = 0; idx < str.length; idx++) {
				int alphaOrder = str[idx] - 'a';
				count[alphaOrder]++;
				idxQueue[alphaOrder].offer(idx);
				if(count[alphaOrder] >= K) {
					int len = idx - idxQueue[alphaOrder].poll()+1;
					min = Math.min(len, min);
					max = Math.max(len, max);
				}
			}
			
			if(min == Integer.MAX_VALUE) {
				System.out.println(-1);
			}
			else {
				System.out.println(min+" "+max);
			}
			
		}

		br.close();
		

	}

}
