package com.tiktop.restapitest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.client.RestTemplate;

public class RestClientApiTest {
	final String uri = "http://localhost:8080";
	
	//sample for REST web service API for java obj 
//	public void selectMember() {
//		RestTemplate restTemplate = new RestTemplate();
//		Member member = restTemplate.getForObject(uri+"/memberlist/22", Member.class);
//		System.out.println(member.getMemberId()+" "+member.getMemberName());
//	}
	
	//sample for REST web service API for java obj list
//	public void selectMemberList() {
//		RestTemplate restTemplate = new RestTemplate();
//		@SuppressWarnings("unchecked")
//        List<LinkedHashMap<String, Object>> membersMap = restTemplate.getForObject(uri+"/memberlist", List.class);
//        for(LinkedHashMap<String, Object> map : membersMap){
//            System.out.println("Member : id="+map.get("memberId")+", Name="+map.get("memberName"));;
//        }
//	}
	
	//sample for REST web service API for JSON format
	public void selectMember() {
		RestTemplate restTemplate = new RestTemplate();
		String memberObj = restTemplate.getForObject(uri+"/memberlist/22", String.class);
		System.out.println("Member Obj = "+memberObj);
	}
	
	//sample for REST web service API for JASON format list
	public void selectMemberList() {
		RestTemplate restTemplate = new RestTemplate();
        String memberList = restTemplate.getForObject(uri+"/memberlist", String.class);
        System.out.println("Member List = "+memberList);
	}
	
	public void countCovid19WorldWideList() throws Exception {		
		RestTemplate restTemplate = new RestTemplate();
		String generalCovid19List = restTemplate.getForObject("https://corona-virus-stats.herokuapp.com/api/v1/cases/general-stats", String.class);
		
		JSONParser parser = new JSONParser(); 
		JSONObject jsonAllData = (JSONObject) parser.parse(generalCovid19List);
		JSONObject jsonSeparateData = (JSONObject) jsonAllData.get("data");
		System.out.println("Total Cases : "+jsonSeparateData.get("total_cases"));
		System.out.println("Recovery Cases : "+jsonSeparateData.get("recovery_cases"));
		System.out.println("Death Cases : "+jsonSeparateData.get("death_cases"));
		System.out.println("Last Update : "+jsonSeparateData.get("last_update"));
		System.out.println("Currently Infected : "+jsonSeparateData.get("currently_infected"));
		System.out.println("Cause With OutCome : "+jsonSeparateData.get("cases_with_outcome"));
		System.out.println("Mild Condition Active Cases : "+jsonSeparateData.get("mild_condition_active_cases"));
		System.out.println("Critical Condition Active Cases : "+jsonSeparateData.get("critical_condition_active_cases"));
		System.out.println("Recovered Closed Cases : "+jsonSeparateData.get("recovered_closed_cases"));
		System.out.println("Death Closed Cases : "+jsonSeparateData.get("death_closed_cases"));
		System.out.println("Closed Cases Recovered Percentage : "+jsonSeparateData.get("closed_cases_recovered_percentage"));
		System.out.println("Closed Cases Death Percentage : "+jsonSeparateData.get("closed_cases_death_percentage"));
		System.out.println("Active Cases Mild Percentage : "+jsonSeparateData.get("active_cases_mild_percentage"));
		System.out.println("Active Cases Critical Percentage : "+jsonSeparateData.get("active_cases_critical_percentage"));
		System.out.println("General Death Rate : "+jsonSeparateData.get("general_death_rate"));
	}
	
	
}
