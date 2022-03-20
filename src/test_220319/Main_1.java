package test_220319;
import java.util.Arrays;
import java.util.TreeSet;

public class Main_1 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String[] {"pencil","cilicon","contrabase","picturelist"})));
		System.out.println(Arrays.toString(solution(new String[] {"abcdeabcd","cdabe","abce","bcdeab"})));
	}
	
    static public String[] solution(String[] goods) {
        String[] answer = new String[goods.length];
        
        // 문자열 abcde는 부분문자열 abc, bcde 로 검색할 수 있음
        
        // 특정 단어로 검색해서 검색된 상품이 하나 일 때 '고유 검색어'
        
        // 상품마다 길이가 가장 짧은 고유 검색어 목록을 구한다.
        // 검색어 목록은 사전순, 중복불가
        // 고유 검색어 없다면 None을 담는다.
        
        // 모든 goods 문자열에 대해
        for(int i = 0; i < goods.length; i++) {
        	// 사전순, 중복되지 않기 위해 TreeSet 사용
        	TreeSet<String> uniques = new TreeSet<>(); 
        	
        	// 길이가 len인 부분문자열을 만들어 본다.
        	for(int len = 1; len <= goods[i].length(); len++) {
        		boolean exist = false;
        		
        		for(int startIdx = 0; startIdx + len <= goods[i].length(); startIdx++) {
        			
        			boolean unique = true;
        			String subStr = goods[i].substring(startIdx, startIdx + len);
        			// 다른 문자열에 대해 부분 문자열인지 확인한다.
        			for(int j = 0; j < goods.length; j++) {
        				if(i == j) continue;
        				// 다른 문자열의 부분문자열이라면 
        				if(goods[j].contains(subStr)) {
        					unique = false;
        					break;
        				}
        				
        			}
        			
        			if(unique) {
        				uniques.add(subStr);
        			}
        		}
        		
        		// 길이가 len인 고유 검색어가 있다면 더 긴 고유검색어는 찾지 않음.
        		if(uniques.size() > 0) {
        			break;
        		}
        	}
        	
        	
        	// 가장 짧은 고유검색어 결과 입력
        	if(uniques.size() == 0) {
        		answer[i] = "None";
        	}
        	else {
        		StringBuilder sb = new StringBuilder();
        		for(String str : uniques) {
        			sb.append(str).append(" ");
        		}
        		sb.setLength(sb.length()-1);
        		answer[i] = sb.toString();
        	}
        }
        
        return answer;
    }
}
