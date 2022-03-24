package baekjoon;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main_2374 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		
		BigInteger cnt = new BigInteger("0");
		int first = Integer.parseInt(br.readLine());
		int prev = first;
		int max = first;
		for(int i = 1; i < N; i++) {
			int next = Integer.parseInt(br.readLine());
			max = Math.max(max, next);
			
			if(prev < next) {
				cnt = cnt.add(new BigInteger(""+(next - prev)));
			}
			
			prev = next;
		}
		if(prev < max) {
			cnt = cnt.add(new BigInteger(""+(max - prev)));
		}
		System.out.println(cnt);
		
		

	}

}
