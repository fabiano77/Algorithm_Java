package baekjoon;


import java.io.*;
import java.util.*;

public class Main_1253_HashMap {
	static class Pair{
		int idx;
		boolean good;
		
		public Pair(int idx, boolean good) {
			this.idx = idx;
			this.good = good;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (good ? 1231 : 1237);
			result = prime * result + idx;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (good != other.good)
				return false;
			if (idx != other.idx)
				return false;
			return true;
		}

		
	}
	
	static int N;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		
		
		N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Map<Integer, Pair> map = new HashMap<>();
		for(int i = 0; i < N; i++) {
			map.put(arr[i], new Pair(i, false));
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				int sum = arr[i] + arr[j];
				if(map.containsKey(sum)) {
					Pair info = map.get(sum);
					if(info.idx != i && info.idx != j) {
						info.good = true;
					}
				}
			}
		}
		
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			if(map.get(arr[i]).good) {
				cnt++;
			}
		}
		System.out.println(cnt);

		

		

	}

}