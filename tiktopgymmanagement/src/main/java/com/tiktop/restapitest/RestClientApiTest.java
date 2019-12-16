package com.tiktop.restapitest;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.tiktop.model.Member;

public class RestClientApiTest {
	final String uri = "http://localhost:8080";

	public void selectMember() {
		RestTemplate restTemplate = new RestTemplate();
		Member member = restTemplate.getForObject(uri+"/memberlist/22", Member.class);
		System.out.println(member.getMemberId()+" "+member.getMemberName());
	}
	
	public void selectMemberList() {
		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unchecked")
        List<LinkedHashMap<String, Object>> membersMap = restTemplate.getForObject(uri+"/memberlist", List.class);
        for(LinkedHashMap<String, Object> map : membersMap){
            System.out.println("Member : id="+map.get("memberId")+", Name="+map.get("memberName"));;
        }
	}
}
