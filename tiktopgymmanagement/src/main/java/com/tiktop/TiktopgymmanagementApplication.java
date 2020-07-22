package com.tiktop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tiktop.restapitest.RestClientApiTest;

@SpringBootApplication
public class TiktopgymmanagementApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TiktopgymmanagementApplication.class, args);
		RestClientApiTest rct= new RestClientApiTest();
		rct.selectMemberList();
		rct.selectMember();
		rct.countCovid19WorldWideList();
	}
}
