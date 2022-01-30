package baekjoon;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_11501 {
	static int T;
	static int N;
	static List<Pair> prices;
	static int[] values;
	static long result;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(Main_11501.class.getResourceAsStream("input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트 케이스
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			prices = new ArrayList<>();
			values = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; st.hasMoreTokens(); i++) {
				int value = Integer.parseInt(st.nextToken());
				prices.add(new Pair(i, value));
				values[i] = value;
			}
			
			prices.sort(new Comparator<Pair>() {
				@Override
				public int compare(Pair o1, Pair o2) {
					// TODO Auto-generated method stub
					if(o1.value == o2.value) {
						return Integer.compare(o1.index, o2.index);
					}
					return Integer.compare(o1.value, o2.value)*-1;
				}
			});
			
			long sum = 0;
			int Bigindex = 0;
			for(int i = 0; i < N-1; i++) {
				while(i >= prices.get(Bigindex).index) {// && values[i] <= prices.get(Bigindex).value) {
					Bigindex++;
				}
				//System.out.println("Bindex = " + prices.get(Bigindex).index+", bValue = "+prices.get(Bigindex).value+", values["+i+"], ="+values[i]+"");
				if(prices.get(Bigindex).value - values[i] > 0) {
					//System.out.println(prices.get(Bigindex).value - values[i]);
					sum += prices.get(Bigindex).value - values[i];
				}
				
			}
			//System.out.println(prices);
			//System.out.println(Arrays.toString(values));
			
			System.out.println(sum);
			//System.out.println();
		}
	}
	
	
	static class Pair{
		public int index;
		public int value;
		public Pair(int index, int value) {
			this.index = index;
			this.value = value;
		}
		@Override
		public String toString() {
			return "Pair [index=" + index + ", value=" + value + "]";
		}
		
	}

}
