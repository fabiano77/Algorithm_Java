package baekjoon;

import java.io.*;
import java.util.*;

public class Main_16434 {
	static class RoomInfo {
		long type;
		long attack;
		long hp;
		public RoomInfo(long type, long attack, long hp) {
			this.type = type;
			this.attack = attack;
			this.hp = hp;
		}
		
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long myAttack = Integer.parseInt(st.nextToken());
		
		List<RoomInfo> rooms = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			long type = Integer.parseInt(st.nextToken());
			long attack = Integer.parseInt(st.nextToken());
			long hp = Integer.parseInt(st.nextToken());
			rooms.add(new RoomInfo(type, attack, hp));
		}

		long minHp = binSearch(1L, Long.MAX_VALUE-1, rooms, myAttack);
		System.out.println(minHp);
		
	}
	
	public static boolean check(long maxHp, long attack, List<RoomInfo> rooms) {
		long hp = maxHp;
		for(RoomInfo room : rooms) {
			// 몬스터
			if(room.type == 1) {
				// 몬스터에게 공격횟수 구함
				long attackCnt = room.hp / attack;
				if(room.hp % attack > 0)attackCnt++;
				hp -= room.attack * (attackCnt - 1);
				
				if(hp <= 0) return false;
				
			}
			// 물약
			else {
				hp += room.hp;
				// 최대 체력 이상으로 회복할 수 없음
				if(hp > maxHp) hp = maxHp;
				attack += room.attack;
			}
		}
		return true;
	}
	
	public static long binSearch(long start, long end, List<RoomInfo> rooms, long myAttack) {
		long ans = end;
		while(start <= end) {
			long mid = (start+end)/2;
			// 무찌른다면 더 작은 hp 탐색
			if(check(mid, myAttack, rooms)) {
				ans = Math.min(ans, mid);
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}
		}
		return ans;
	}

}
