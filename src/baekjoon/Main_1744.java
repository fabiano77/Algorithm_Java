package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main_1744 {
	static int N;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		System.setIn(Main_1744.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		Integer [] array = new Integer[N];
		for(int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(br.readLine());
		}
		//내림차순 정렬
		Arrays.sort(array, Collections.reverseOrder());
		
		// 양수 배열과 0포함 음수 배열 나눔.
		int lastPositiveIdx = -1;
		for(int i = 0; i < array.length; i++) {
			if(array[i] > 0) lastPositiveIdx = i;
			else break;
		}
		Integer [] posArr = Arrays.copyOfRange(array, 0, lastPositiveIdx+1);
		Integer [] negArr = Arrays.copyOfRange(array, lastPositiveIdx+1, N);
		
		// 0포함 음수 배열 오름차순 정렬
		Arrays.sort(negArr);
		
		// 양수 배열에 대해 1보다 클 경우 2개씩 묶는다.
		int result = 0;
		for(int i = 0; i < posArr.length; i++) {
			if(i+1 < posArr.length && posArr[i+1] > 1) {
					result += posArr[i] * posArr[i + 1];
					i++;
			}
			else {
				result += posArr[i];
			}
		}
		
		// 음수 배열에 대해 무조건 2개씩 묶는다.
		for(int i = 0; i < negArr.length; i++) {
			if(i+1 < negArr.length) {
				result += negArr[i]*negArr[i+1];
				i++;
			}
			else {
				result += negArr[i];
			}
		}
		System.out.println(result);
		br.close();
	}

}
