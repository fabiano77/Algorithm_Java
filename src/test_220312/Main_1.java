package test_220312;

public class Main_1 {

	public static void main(String[] args) {
		System.out.println(solution(4578, new int[] {1, 4, 99, 35, 50, 1000}));
		System.out.println(solution(1999, new int[] {2, 11, 20, 100, 200, 600}));
	}

    static public int solution(int money, int[] costs) {
        int answer = 0;
        int[] values = {1, 5, 10, 50, 100, 500};
        
        for(int i = 5; i >= 0; i--) {
        	if(money < values[i]) continue;
        	int minCost = Integer.MAX_VALUE;
        	double minUnitCost = Integer.MAX_VALUE;
        	int minIdx = -1;
        	
        	// 더 작은 동전으로도 계산해본다.
        	for(int j = i; j >= 0; j--) {
        		int curCost = (money / values[j])*costs[j];
        		double unitCost = (double)curCost / (money - money%values[j]);
        		if(unitCost < minUnitCost) {
        			minUnitCost = unitCost;
        			minCost = curCost;
        			minIdx = j;
        		}
        	}
        	
        	answer += minCost;        	
        	money = money % values[minIdx];
        }
        
        return answer;
    }

}
