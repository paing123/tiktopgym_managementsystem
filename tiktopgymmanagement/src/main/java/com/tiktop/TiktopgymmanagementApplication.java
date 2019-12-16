package com.tiktop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tiktop.restapitest.RestClientApiTest;

@SpringBootApplication
public class TiktopgymmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiktopgymmanagementApplication.class, args);
		RestClientApiTest rct= new RestClientApiTest();
		rct.selectMemberList();
	}
}
