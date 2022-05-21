package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1269 {

	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Set<Integer> A = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A.add(Integer.parseInt(st.nextToken()));
		}
		Set<Integer> B = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			B.add(Integer.parseInt(st.nextToken()));
		}
		
		Set<Integer> union = new HashSet<>();
		union.addAll(A);
		union.addAll(B);
		int inter = A.size() + B.size() - union.size();
		
		System.out.println(union.size() - inter);

		
		br.close();
		

	}

}
