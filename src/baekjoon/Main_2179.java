package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2179 {
	
	static class Pair{
		String str;
		int idx;
		
		public Pair(String str, int idx) {
			this.str = str;
			this.idx = idx;
		}

		@Override
		public String toString() {
			return str;
		}
		
	}


	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		List<Pair> list = new ArrayList<>();
		Set<String> visited = new HashSet<>();
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			if(!visited.contains(str)) {				
				list.add(new Pair(str, i));
				visited.add(str);
			}
		}

		list.sort((a, b)->a.str.compareTo(b.str));
		List<Pair> prefixList = new ArrayList<>();
		
		int ans = -1; 
		for(int i = 0; i < N-1; i++) {
			
			int len = 0;
			for(int idx = 0; idx < list.get(i).str.length() && idx < list.get(i+1).str.length(); idx++) {
				if(list.get(i).str.charAt(idx) != list.get(i+1).str.charAt(idx)) {
					break;
				}
				len++;
			}
			
			if(len == ans) {
				int min = Math.min(list.get(i).idx, list.get(i+1).idx);
				prefixList.add(new Pair(list.get(i).str.substring(0, len), min));
			}
			else if(len > ans) {
				ans = len;
				prefixList = new ArrayList<>();
				int min = Math.min(list.get(i).idx, list.get(i+1).idx);
				prefixList.add(new Pair(list.get(i).str.substring(0, len), min));
			}
		}
		
		prefixList.sort((a, b)->Integer.compare(a.idx, b.idx));
		String prefix = prefixList.get(0).str;
		
		int cnt = 0;
		list.sort((a, b)->Integer.compare(a.idx, b.idx));
		for(Pair pair : list) {
			if(pair.str.startsWith(prefix)) {
				System.out.println(pair.str);
				if(++cnt == 2) {
					break;
				}
			}
		}
		
		

		
		

	}

}
