package test_220312;
import java.util.*;

public class Main_4 {

	public static void main(String[] args) {
		System.out.println(solution(5, new int[][] {{0, 1}, {0, 2}, {1, 3}, {1, 4}}));
		System.out.println(solution(4, new int[][] {{2, 3}, {0, 1}, {1, 2}}));
	}
	
    static public long solution(int n, int[][] edges) {
    	// 조건을 만족하는 순서쌍 개수 찾기
    	long answer = 0;
    	
    	// a번노드 b번노드 사이 간선의 개수 distance(a, b)
    	// distance(i, j) + distance(j, k) = distance(i, k);

    	
    	
        return answer;
    }
    
    

}
