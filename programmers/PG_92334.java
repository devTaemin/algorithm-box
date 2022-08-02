package com.ssafy.prog;

import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 프로그래머스 - 신고 결과 받기
 * @author devTaemin
 *
 */
class PG_92334 {
	/** HashMap storing accused with accusers */
    public static Map<String, HashSet<String>> Accuser = new HashMap<>();

    /** HashMap storing the number of mail*/
    public static Map<String, Integer> Mails = new HashMap<>();
    
    public int[] solution(String[] id_list, String[] report, int k) {
        for (String id : id_list) {
            Accuser.put(id, new HashSet<>());
            Mails.put(id, 0);
        }
        
        /** Store accusers */
        for (String rep : report) {
            String[] names = rep.split(" ");
            String accuser = names[0];
            String accused = names[1];
            
            HashSet<String> temp = Accuser.get(accused);
            temp.add(accuser);
            Accuser.replace(accused, temp);
        }
        
        /** Store mails */
        for (String id : id_list) {
            int NumOfAccused = Accuser.get(id).size();
            if (NumOfAccused >= k) {
                HashSet<String> set = Accuser.get(id);
                for (String ac : set) {
                    Mails.replace(ac, Mails.get(ac) + 1);
                }
            }
        }
        
        int[] answer = new int[id_list.length];
        int index = 0;
        for (String id : id_list) {
            answer[index++] = Mails.get(id);
        }
    
        return answer;
    }
}