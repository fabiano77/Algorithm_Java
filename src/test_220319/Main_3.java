package test_220319;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main_3 {

	public static void main(String[] args) {
		solution(new int[][] {{1, 2}, {2, 3}}, new int[][] {{1, 3}, {3, 2}}, 1);
		solution(new int[][] {{1, 2}, {3, 1}, {2, 4}, {3, 5}}, new int[][] {{2, 1}, {4, 1}, {2, 5}, {3, 2}}, 1);
		// [[3, 4], [7, 2], [5, 4], [2, 3], [6, 5], [1, 2]]
		// [[2, 1], [3, 6], [1, 4], [1, 5], [7, 1], [3, 2]]	
		// 2

	}
	
	static class Edge{
		int v1, v2;

		public Edge(int v1, int v2) {
			// this.v1가 더 작도록 유지
			if(v1 < v2) {				
				this.v1 = v1;
				this.v2 = v2;
			}
			else {
				this.v1 = v2;
				this.v2 = v1;
			}
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + v1;
			result = prime * result + v2;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Edge other = (Edge) obj;
			if (v1 != other.v1)
				return false;
			if (v2 != other.v2)
				return false;
			return true;
		}
		
	}
	static int answer = Integer.MAX_VALUE; 
	
    static public int solution(int[][] a, int[][] b, int m) {
        
        // 트리 a 는 1~n번호를 겹치지 않게 존재, b도 마찬가지
        // 최대 정점 12개.
        
        // 트리 b를 a와 똑같이 만들고 싶다.
        
        // 할 수 있는 작업
        //		1. b에 존재하는 간선 삭제하고 하나 추가
        //		2. b에 존재하는 정점의 두 번호를 바꾼다.
        // 1은 무제한이지만 2 는 최대 m번 제한. < 3
        
        // b를 a와 똑같이 만들기위한 1번작업의 최소 횟수를 return
        
        // 노드 개수
        int n = a.length+1;
        
        // 트리 A 의 간선 집합
        HashSet<Edge> treeA = makeSet(a);

        answer = Integer.MAX_VALUE;
        
        // 2번작업을 i번 수행
        for(int i = 0; i <= m; i++) {
        	
        	HashSet<Edge> selected = new HashSet<>();
        	solve(treeA, b, i, n, selected);
        	
        }

        
        
        System.out.println(answer);
        return answer;
    }
    
    static HashSet<Edge> makeSet(int[][] a){
    	HashSet<Edge> tree = new HashSet<>();
        for(int i = 0; i < a.length; i++) {
        	tree.add(new Edge(a[i][0], a[i][1]));
        }
        return tree;
    }
    
    static int count(HashSet<Edge> a , HashSet<Edge> b) {
    	Set<Edge> interSection = new HashSet<Edge>(a);
    	interSection.retainAll(b);
    	
    	return a.size() - interSection.size();
    }
    
    static void swap(int[][] a, int x, int y) {
    	for(int i = 0; i < a.length; i++) {
    		for(int j = 0; j < 2; j++) {
    			if(a[i][j] == x) 		a[i][j] = y;
    			else if(a[i][j] == y) 	a[i][j] = x;
    		}
    	}
    	//{{1, 2}, {3, 2}}
    	
    	//{{1, 3}, {2, 3}} 
    }
    
    static void solve(HashSet<Edge> treeA, int[][] b, int toChoose, int n, HashSet<Edge> selected) {
    	if(toChoose == 0) {
    		int cnt = count(treeA, makeSet(b));
    		answer = Math.min(answer, cnt);
    		return;
    	}
    	
    	for(int x = 1; x < n ; x++) {
    		for(int y = x+1; y <= n; y++) {
    			if(x == y) continue;
    			Edge select = new Edge(x, y);
    			if(selected.contains(select)) continue;
    			
    			swap(b, x, y);
    			selected.add(select);
    			solve(treeA, b, toChoose-1, n, selected);
    			selected.remove(select);
    			swap(b, x, y);
    		}
    	}
    	
    }


}
