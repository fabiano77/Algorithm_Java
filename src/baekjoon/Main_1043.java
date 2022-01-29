package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1043 {
	static int N;
	static int M;
	static List<List<Integer>> parties = new ArrayList<>();
	static Queue<Integer> q = new LinkedList<>();
	
	

	public static void main(String[] args) throws IOException{
		System.setIn(Main_1043.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		// 사람 수 N 파티 수 M
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 진실을 아는 사람 입력
		st = new StringTokenizer(br.readLine(), " ");
		int knowingCnt = Integer.parseInt(st.nextToken()); 
		for(int i = 0; i < knowingCnt; i++) {
			q.add(Integer.parseInt(st.nextToken()));
		}
		
		// 파티 정보 입력받음
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			List<Integer> newParty = new ArrayList<>(); 
			int num = Integer.parseInt(st.nextToken());
			for(int j = 0; j < num; j++) {
				newParty.add(Integer.parseInt(st.nextToken()));
			}
			parties.add(newParty);
		}
		
		// 진실을 말해야하는 파티에는 진실을 말함
		speechTruth();
		
		// 남은 파티에게 거짓 말함
		System.out.println(parties.size());
		
		
		br.close();
	}
	
	static void speechTruth() {
		// 큐에 사람이 있을 경우 (진실을 아는 사람)
		while(!q.isEmpty()) {
			int person = q.poll();
			for(int i = 0; i < parties.size(); i++) {
				List<Integer> party = parties.get(i);
				
				// 진실을 아는 사람이 있는 파티면, 다른 사람도 큐에 추가한다(진실을 알게됨)
				if(party.contains(person)) {
					party.stream().forEach(q::add);
					
					// 그 파티는 이제 제외
					parties.remove(party);
				}
			}			
		}
	}
	

}
