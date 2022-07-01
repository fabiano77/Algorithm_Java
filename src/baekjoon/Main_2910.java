package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2910 {
	
	static class Info{
		int cnt, num, idx;
		
		public Info(int num) {
			this(1, num);
		}
		
		public Info(int cnt, int num) {
			this(cnt, num, Integer.MAX_VALUE);
		}


		public Info(int cnt, int num, int idx) {
			this.cnt = cnt;
			this.num = num;
			this.idx = idx;
		}

		
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Map<Integer, Info> map = new HashMap<>();
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			map.merge(num, new Info(1, num, i), (o1, o2)->{
				o1.cnt += o2.cnt;
				o1.idx = Math.min(o1.idx, o2.idx);
				return o1;
			});
		}
		
		List<Info> list = new ArrayList<>(map.values());
		list.sort((o1, o2)->{
			int ret = Integer.compare(o1.cnt, o2.cnt)*-1;
			if(ret == 0) ret = Integer.compare(o1.idx, o2.idx);
			return ret;
		});
		
		for(Info o : list) {
			for(int i = 0; i < o.cnt; i++) {
				System.out.print(o.num+" ");
			}
		}
		

		br.close();
		

	}

}
