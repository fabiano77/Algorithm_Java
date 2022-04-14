package baekjoon;

import java.io.*;
import java.util.*;

public class Main_21939 {
	static class Problem implements Comparable<Problem>{
		int no;
		int difficulty;
		
		public Problem(StringTokenizer st) {
			no = Integer.parseInt(st.nextToken());
			difficulty = Integer.parseInt(st.nextToken());
		}
		
		@Override
		public int compareTo(Problem o) {
			if(difficulty == o.difficulty) return no - o.no;
			return difficulty - o.difficulty;
		}
		
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 객체를 정렬상태로 저장할 treeSet
		TreeSet<Problem> treeSet = new TreeSet<>();
		// 문제 번호로 객체를 찾기위한 map
		Map<Integer, Problem> map = new HashMap<>();
		
		
		int N = Integer.parseInt(br.readLine()); //~100,000
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Problem item = new Problem(st);
			treeSet.add(item);
			map.put(item.no, item);
		}
		
		
		int M = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			
			if(command.equals("add")) {
				Problem item = new Problem(st);
				treeSet.add(item);
				map.put(item.no, item);
			} 
			else if(command.equals("recommend")) {
				boolean min = (Integer.parseInt(st.nextToken()) == -1)? true : false;
				// 가장 큰, 가장 작은 문제 출력
				Problem item = (min) ? treeSet.first() : treeSet.last();
				System.out.println(item.no);
			} 
			else if(command.equals("solved")) {
				// 객체를 찾아서 treeSet에서 지운다
				int deleteNo = Integer.parseInt(st.nextToken());
				Problem delItem = map.get(deleteNo);
				treeSet.remove(delItem);
			}
			
		}

		br.close();
	}

}