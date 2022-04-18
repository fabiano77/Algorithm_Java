package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2310 {
	
	static class Room {
		char type;
		int price;
		List<Integer> nextRoomNums;
		public Room(char type, int price) {
			this.type = type;
			this.price = price;
			nextRoomNums = new ArrayList<>();
		}
		
	}
	
	static class State {
		int roomNum;
		int money;
		public State(int roomNum, int money) {
			this.roomNum = roomNum;
			this.money = money;
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N;
		while((N = Integer.parseInt(br.readLine()))!= 0) {
			
			Room[] rooms = new Room[N+1];
			
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				char type = st.nextToken().charAt(0);
				int price = Integer.parseInt(st.nextToken());
				rooms[i] = new Room(type, price);
				int next;
				while((next = Integer.parseInt(st.nextToken())) != 0) {
					rooms[i].nextRoomNums.add(next);
				}
			}
			
			Queue<State> q = new LinkedList<>();
			int[] visited = new int[N+1];
			Arrays.fill(visited, -1);
			q.offer(new State(1, 0));
			visited[1] = 0;
			
			while(!q.isEmpty()) {
				int curRoomNum = q.peek().roomNum;
				int curMoney = q.poll().money;
				
				Room curRoom = rooms[curRoomNum];
				
				if(curRoom.type == 'L') {
					if(curMoney < curRoom.price) {
						curMoney = curRoom.price;
					}
				} else if(curRoom.type == 'T') {
					curMoney -= curRoom.price;
				}
				
				
				if(curMoney >= 0) {
					visited[curRoomNum] = Math.max(visited[curRoomNum], curMoney);
					for(int next : curRoom.nextRoomNums) {
						if(visited[next] < curMoney) {
							q.offer(new State(next, curMoney));
						}
					}
				}
				
			}
			if(visited[N] >= 0) {
				System.out.println("Yes");
			}
			else {
				System.out.println("No");
			}
			
			
		}
		
		
		br.close();
	}

}
