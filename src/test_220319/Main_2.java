package test_220319;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2 {

	public static void main(String[] args) {
		String[] arr1 = {"1","2","4","3","3","4","1","5"};
		String[] processes1 = { "read 	1 3 1 2",   //0
								"read 	2 6 4 7",	//1
								"write 	4 3 3 5 2",	//2
								"read 	5 2 2 5",	//3
								"write 	6 1 3 3 9", //4
								"read 	9 1 0 7"};	//5
		System.out.println(Arrays.toString(solution(arr1, processes1)));
		
		String[] arr2 = {"1","1","1","1","1","1","1"};
		String[] processes2 = {"write 1 12 1 5 8","read 2 3 0 2","read 5 5 1 2","read 7 5 2 5","write 13 4 0 1 3","write 19 3 3 5 5","read 30 4 0 6","read 32 3 1 5"};
		System.out.println(Arrays.toString(solution(arr2, processes2)));

	}
	
	static class Process{
		char type;
		int t1;
		int t2;
		int begin;
		int end;
		int value;
		public Process(char type, int t1, int t2, int begin, int end, int value) {
			this.type = type;
			this.t1 = t1;
			this.t2 = t2;
			this.begin = begin;
			this.end = end;
			this.value = value;
		}
		@Override
		public String toString() {
			return "Process [type=" + type + ", t1=" + t1 + ", t2=" + t2 + ", begin=" + begin + ", end=" + end
					+ ", value=" + value + "]";
		}
		
	}
    static public String[] solution(String[] arr, String[] processes) {
        String[] answer = {};
        
        // 여러 프로세스가 arr 하나에 접근하여 R, W 작업 수행
        // 여러 프로세스 동시에 R작업 수행 가능
    	// 		R 작업 중인 경우 새로운 R 요청도 즉시 수행 가능
        // 한 번에 하나의 프로세스만 W작업 수행 가능
    	// 		W 작업 중에는 R, W 프로세스 모두 대기
    	// 		R 작업 중에는 W 프로세스 모두 대기
        // W 작업 대기중인 경우 R 요청 또한 대기
        
        // 대기중인 W, R 작업중 선택하는 기준
    	//		W보다 R를 먼저 선택
        // 		W 중에서 먼저 요청된 작업 수행
        // 대기 작업을 수행하려 함과 동시에 새로운 요청이 온다면, 새작업을 포함하여 선택
        
        // 읽기 작업에서 읽은 내용을 정답 배열에 담고
        // 전체 시간을 배열의 마지막에 문자열로 담아라
        
        // process의 형태
        // "w/r t1 t2 A B [C]"
        
        // processes 는 t1을 기준으로 정렬되어있고 같은 t1은 존재하지 않음.
        
        StringTokenizer st;
        Process[] process = new Process[processes.length];
        for(int i = 0; i < process.length; i++) {
        	st = new StringTokenizer(processes[i]);
        	char type = st.nextToken().charAt(0);
        	int t1 = Integer.parseInt(st.nextToken());
        	int t2 = Integer.parseInt(st.nextToken());
        	int begin = Integer.parseInt(st.nextToken());
        	int end = Integer.parseInt(st.nextToken());
        	int value = -1;
        	if(st.hasMoreTokens()) {
        		value = Integer.parseInt(st.nextToken());
        	}
        	process[i] = new Process(type, t1, t2, begin, end, value);
        }
        
        List<String> answerList = new ArrayList<>();
        
        Queue<Process> waitRead = new LinkedList<Process>();
        Queue<Process> waitWrite = new LinkedList<Process>();
        
        Process processing = null;
        
        int usingTime = 0;
        int processCnt = 0;
        for(int time = 0; ; time++) {
        	// 작업중인 프로세스가 있고 종료시간이 되었다면
        	if(processing != null) {
        		if(--processing.t2 == 0) {
	        		// 종료시켜준다.
	        		processing = null;
	        		
        		}
        	}
        	
        	// 남아있는 프로세스가 있고 현재 시간에 프로세스 요청이 들어왔다면
        	if(processCnt < process.length && time == process[processCnt].t1) {
        		if(process[processCnt].type == 'r') {
        			waitRead.offer(process[processCnt]);
        		}
        		else {
        			waitWrite.offer(process[processCnt]);
        		}
        		processCnt++;
        	}
        	
        	// 작업중인 프로세스가 없다면
        	if(processing == null) {
        		// 대기중인 W 프로세스가 있다면
        		if(!waitWrite.isEmpty()) {
        			// W 프로세스중 가장 먼저들어온 것을 처리
        			
        			// 작업중인 프로세스로 기록
        			processing = waitWrite.poll();
        			// W 작업 수행
        			for(int idx = processing.begin; idx <= processing.end; idx++) {
        				arr[idx] = String.valueOf(processing.value);
        			}
        		}
        	}
        	
        	// 작업중인 프로세스가 없거나 read인데 w대기가 없을 경우
        	if(processing == null || (processing.type == 'r' && waitWrite.isEmpty())) {
    			// 현재 작업할 수 있는 모든 Read프로세스를 처리
    			while(!waitRead.isEmpty()) {
    				
    				// R 작업 수행
    				StringBuilder sb = new StringBuilder();
        			for(int idx = waitRead.peek().begin; idx <= waitRead.peek().end; idx++) {
        				sb.append(arr[idx]);
        			}
        			answerList.add(sb.toString());
    				
    				// 작업중인 프로세스로 기록
    				if(processing == null || waitRead.peek().t2 > processing.t2) {        					
    					processing = waitRead.peek();
    				}
    				waitRead.poll();
    			}
        	}
        	
        	// 배열이 사용중일 경우
        	if(processing != null) {
        		usingTime++;
        	}
        	
        	// 작업중인 프로세스가 없고, 요청 예정인 프로세스도 없을 경우
        	if(processing == null && processCnt == process.length) {
        		break;
        	}
        }
        
		answerList.add(String.valueOf(usingTime));
		answer = answerList.toArray(answer);
        
        return answer;
    }

}
