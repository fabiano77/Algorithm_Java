package test_220319;
import java.util.*;

public class Main_4 {
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(8, 4, 4, new int[][]{
			{1, 5, 1, 3}, {5, 7, 5, 6}
		})));
		
//		System.out.println(Arrays.toString(solution(8, 4, 4, new int[][]{
//			{1, 5, 1, 3}
//		})));
//		
//		System.out.println(Arrays.toString(solution(10, 3, 3, new int[][]{
//			{1, 2, 3}, {5, 7, 10}
//		})));
	}
	
    static public int[] solution(int n, int m, int k, int[][] records) {
        int[] answer = {};
        
        // n 버튼의 개수 <= 200,000
        // m 표시되는 수 범위 <= n 
        // k 비밀번호의 길이 <= 5,000
        // records 의 길이 <= 1,000
        // records[i] 의 길이 = k
        
        
        // [ 1~m 사이의 정수 k개로 조합된 비밀번호 ] 
        // 비번 의 숫자 개수는 k
        
        
        // n 개의 버튼이 일렬로 배열되어 있는 잠금장치
        // 1부터 n까지의 번호로 위치 표현
        // 1~m 사이의 정수 k개로 조합된 비밀번호 가능
        
        // 잠금장치 해제할 때마다 n개 버튼중 무작위 m개 위치에 1~m사이 정수 표시
        
        // m개중  x개를 순서없이 고르는 경우의 수
        
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < k; i++) {
        	set.add(records[0][i]);
        }
        int x = set.size();
        
        TreeSet<String> candidate = new TreeSet<>();
        boolean[] selected = new boolean[m+1];
        
        // 비밀번호 후보 정수집합
        combination(x, 1, selected, candidate, m);
        
        
        // records를 이용해서 비밀번호 추론
        
        // records의 값이 붙어있거나 버튼에서 맨 끝에 해당한다면 범위 좁힐 수 있음
        
        
        System.out.println(candidate);
 
        
        
        return answer;
    }
    
    static public void combination(int toChoose, int start, boolean[] selected, TreeSet<String> candidate, int m) {
    	if(toChoose == 0) {
    		StringBuilder sb = new StringBuilder();
    		for(int i = 1; i <= m; i++) {
    			if(selected[i]) sb.append(i);
    		}
    		candidate.add(sb.toString());
    		return;
    	}
    	
    	for(int i = start; i <= m; i++) {
    		
    		selected[i] = true;
    		combination(toChoose - 1, i + 1, selected, candidate, m);
    		selected[i] = false;
    		
    	}
    	
    }
	
}
