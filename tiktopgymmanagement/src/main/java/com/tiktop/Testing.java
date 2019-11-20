package com.tiktop;

import java.util.Arrays;
import java.util.Collections;

public class Testing {
	public static void main(String[] args) {
		String date = "20/11/2019";
		String dates[]=date.split("/");
		Collections.reverse(Arrays.asList(dates)); 
		String newDate = "";
		for (String str : dates) {
			newDate=newDate+"-"+str;
		}
		System.out.println(newDate.substring(1));
	}
}
