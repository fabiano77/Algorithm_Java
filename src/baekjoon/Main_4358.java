package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main_4358 {

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Map<String, Integer> map = new HashMap<>();
		int totalCnt = 0;
		
		while(br.ready()) {
			String key = br.readLine();
			if(map.containsKey(key)) {
				map.put(key, map.get(key)+1);
			}
			else {
				map.put(key, 1);
			}
			totalCnt++;
		}
		
		List<Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
		list.sort((e1, e2)->e1.getKey().compareTo(e2.getKey()));
		
		StringBuilder sb = new StringBuilder();
		final int total = totalCnt; 
		list.forEach((item)->{
			sb.append(String.format("%s %.4f\n", item.getKey(), (100*(double)item.getValue()/total)));
		});
		
		System.out.println(sb);

		br.close();
		

	}

}
