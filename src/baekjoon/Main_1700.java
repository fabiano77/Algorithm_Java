package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1700 {
	
	static class Pair implements Comparable<Pair>{
		int no;
		int priority;
		
		public Pair(int no, int priority) {
			this.no = no;
			this.priority = priority;
		}
		
		@Override
		public int compareTo(Pair o) {
			return Integer.compare(priority, o.priority);
		}

		@Override
		public String toString() {
			return "Pair [no=" + no + ", priority=" + priority + "]";
		}
		
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 멀티탭 구멍 개수
		int K = Integer.parseInt(st.nextToken());	// 전기용품 사용 횟수
		int[] arr = new int[K+1];
		st = new StringTokenizer(br.readLine());
		
		boolean[] pluged = new boolean[K+1];
		
		int[] priority = new int[K+1];
		int[] lastIdx = new int[K+1];
		Arrays.fill(priority, Integer.MAX_VALUE);
		Arrays.fill(lastIdx, Integer.MAX_VALUE);
		
		for(int i = 1; i <= K; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(lastIdx[arr[i]] != Integer.MAX_VALUE) {
				priority[lastIdx[arr[i]]] = i;
			}
			lastIdx[arr[i]] = i;
		}
		
		LinkedList<Pair> plugedList = new LinkedList<>();
		int ans = 0;
		for(int i = 1; i <= K; i++) {
			if(pluged[arr[i]]) {
				for(Pair p : plugedList) {
					if(p.no == arr[i]) {
						plugedList.remove(p);
						plugedList.add(new Pair(arr[i], priority[i]));
						break;
					}
				}
				continue;
			}
			if(plugedList.size() == N) {
				plugedList.sort(Collections.reverseOrder());
				pluged[plugedList.pollFirst().no] = false;
				ans++;
			}
			plugedList.add(new Pair(arr[i], priority[i]));
			pluged[arr[i]] = true;
		}
		
		System.out.println(ans);

		br.close();
		

	}

}
