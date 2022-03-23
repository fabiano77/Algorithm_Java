package baekjoon;
import java.io.*;
import java.util.*;

public class Main_14867 {
	
	static class Pair{
		int a;
		int b;
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
		public Pair() {	}


		@Override
		public String toString() {
			return "Pair [a=" + a + ", b=" + b + "]";
		}
		
	}

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		
		int volA = Integer.parseInt(st.nextToken());
		int volB = Integer.parseInt(st.nextToken());
		int targetA = Integer.parseInt(st.nextToken());
		int targetB = Integer.parseInt(st.nextToken());
		String target = targetA+" "+targetB;
		
		String[] cases = {"fillA", "fillB", "emptyA", "emptyB", "moveToA", "moveToB"};
		
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(0, 0));
		Map<String, Integer> visited = new HashMap<>();
		visited.put("0 0", 0);
		while(!q.isEmpty()) {
			Pair cur = q.poll(); 
			String curState = cur.a + " " + cur.b;
			Pair next;
			for(String str : cases) {
				switch (str) {
				case "fillA":
					next = new Pair(volA, cur.b);
					break;
				case "fillB":
					next = new Pair(cur.a, volB);
					break;
				case "emptyA":
					next = new Pair(0, cur.b);
					break;
				case "emptyB":
					next = new Pair(cur.a, 0);
					break;
				case "moveToA":
					next = new Pair(cur.a + cur.b, 0);
					if(next.a > volA) {
						next.b = next.a - volA;
						next.a = volA;
					}
					break;
				case "moveToB":
					next = new Pair(0, cur.a + cur.b);
					if(next.b > volB) {
						next.a = next.b - volB;
						next.b = volB;
					}
					break;
				default:
					next = null;
					break;
				}
				
				String nextState = next.a + " " + next.b;
				if(!visited.containsKey(nextState)) {
					q.offer(next);
					visited.put(nextState, visited.get(curState)+1);
				}
			}
			
			
		}
		
		if(visited.get(target) != null) {			
			System.out.println(visited.get(target));
		}
		else {
			System.out.println(-1);
		}
	}

}