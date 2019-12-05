package com.tiktop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClassesTest {
	private Classes classes;
	
	@BeforeEach
	public void setUp() {
		classes = new Classes();
	}
	
	@AfterEach
	public void tearDown() {
		classes = null;
	}
	
	@Test
	public void testGetClassesId() {
		classes.setClassesId(1);;
		assertEquals(1, classes.getClassesId(),"get class id");
	}
	
	@Test
	public void testSetClassesId() {
		classes.setClassesId(1);;
		assertEquals(1, classes.getClassesId(),"set class id");
	}

	@Test
	public void testGetClassesName() {
		classes.setClassesName("Gym");
		assertEquals("Gym", classes.getClassesName(),"get class name");
	}

	@Test
	public void setClassesName() {
		classes.setClassesName("Gym");
		assertEquals("Gym", classes.getClassesName(),"get class name");
	}

	@Test
	public void testGetCreateDate() {
		classes.setCreateDate("5/12/2019");
		assertEquals("5/12/2019", classes.getCreateDate(),"get create date");
	}

	@Test
	public void setCreateDate() {
		classes.setCreateDate("5/12/2019");
		assertEquals("5/12/2019", classes.getCreateDate(),"set create date");
	}

	@Test
	public void testGetUpdateDate() {
		classes.setUpdateDate("6/12/2019");
		assertEquals("6/12/2019", classes.getUpdateDate(),"get update date");
	}

	@Test
	public void testSetUpdateDate() {
		classes.setUpdateDate("6/12/2019");
		assertEquals("6/12/2019", classes.getUpdateDate(),"set update date");
	}
}
