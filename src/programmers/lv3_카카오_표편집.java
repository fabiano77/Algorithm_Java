package programmers;

import java.util.*;

public class lv3_카카오_표편집 {
    
    static class MyList{
        
        static class Node {
            Node next;
            Node prev;
            boolean isAlive = true;
        }
        
        Node head = null;
        Node tail = null;
        Node current = null;
        Stack<Node> stack = new Stack<>();
        Node[] arr;
        
        public MyList(int len, int k){
            arr = new Node[len];
            head = new Node();
            tail = head;
            arr[0] = head;
            for(int i = 1; i < len; i++){
                Node newNode = new Node();
                newNode.prev = tail;
                tail.next = newNode;
                tail = newNode;
                arr[i] = newNode;
            }
            current = arr[k];
        }
        
        public void up(){
            current = current.prev;
        }
        public void down(){
            current = current.next;
        }
        public void delete(){
            if(current.prev != null) current.prev.next = current.next;
            if(current.next != null) current.next.prev = current.prev;
            current.isAlive = false;
            stack.push(current);
            
            if(current.next != null) current = current.next;
            else current = current.prev;
        }
        public void restore(){
            Node restoreNode = stack.pop();
            restoreNode.isAlive = true;
            if(restoreNode.next != null) restoreNode.next.prev = restoreNode;
            if(restoreNode.prev != null) restoreNode.prev.next = restoreNode;
        }
        public String getString(){
            StringBuilder sb = new StringBuilder();
            for(Node node : arr){
                sb.append((node.isAlive)?'O':'X');
            }
            return sb.toString();
        }
        
    }
     
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        
        // 행 개수 n ~ 1,000,000
        // 초기 선택 행의 위치 정수 k ~ n
        // cmd.length ~ 200,000
        
        // U x : x칸 위로
        // D x : x칸 아래로
        // C   : 삭제, 아래 행 선택
        // Z   : 가장 최근에 삭제된 행을 원래대로 복구한 후, 현재 선택 행 유지
        
        MyList linkedList = new MyList(n, k);
        
        for(String command : cmd){
            StringTokenizer st = new StringTokenizer(command);
            char type = st.nextToken().charAt(0);
            if(type == 'U' || type == 'D'){ //커서 이동
                boolean up = (type == 'U');
                int num = Integer.parseInt(st.nextToken());
                while(num-- > 0){
                    if(up) linkedList.up();
                    else   linkedList.down();
                }
            }
            else if(type == 'C'){
                linkedList.delete();
            }
            else if(type == 'Z'){
                linkedList.restore();
            }
        }
        
        answer = linkedList.getString();
        
        return answer;
    }
}
