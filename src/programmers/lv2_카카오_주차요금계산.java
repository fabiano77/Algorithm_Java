package programmers;

import java.io.*;
import java.util.*;

class lv2_카카오_주차요금계산 {

    public int[] solution(int[] fees, String[] records) {
        
        int baseTime = fees[0];
        int baseFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        
        // 차량 번호가 작은 자동차부터 청구할 요금
        
        HashMap<String, Integer> CarAccTime = new HashMap<>();
        HashMap<String, Integer> inCars = new HashMap<>();
        
        for(String record : records){
            String[] strs = record.split(" ");
            String[] time = strs[0].split(":");
            int hour = Integer.parseInt(time[0]);
            int min = Integer.parseInt(time[1]);
            min += hour * 60;
            
            String number = strs[1];
            String type = strs[2];
            System.out.println(record);
            
            if(type.equals("IN")){
                inCars.put(number, min);
            }
            else{
                outCar(inCars, CarAccTime, number, min);
                inCars.remove(number);
            }
        }
        
        int endTime = 23*60 + 59;
        // 출차 기록이 없는 차들에게 23:59 출차 시킴
        for(String number : inCars.keySet()){
            outCar(inCars, CarAccTime, number, endTime);
        }
        
        // 결과 저장할 treeMap
        TreeMap<String, Integer> result = new TreeMap<>();
        
        for(Map.Entry<String, Integer> e : CarAccTime.entrySet()){
            int time = e.getValue();
            int fee = baseFee;
            time -= baseTime;
            if(time > 0) {
                fee += unitFee * (time/unitTime);
                if(time % unitTime != 0) fee += unitFee;
            }
            result.put(e.getKey(), fee);
        }
        
        
        System.out.println(result);

        int[] answer = new int[result.size()];
        int i = 0;
        for(int fee : result.values()) {
            answer[i++] = fee;
        }
        
        
        return answer;
    }
    
    public static void outCar (HashMap<String, Integer> inCars, HashMap<String, Integer> CarAccTime, String number, int outTime) {
        int inTime = inCars.get(number);
        int term = outTime - inTime;

        if(!CarAccTime.containsKey(number)) {
            CarAccTime.put(number, term);
        }
        else {
            CarAccTime.put(number, term + CarAccTime.get(number));
        }
    }
}