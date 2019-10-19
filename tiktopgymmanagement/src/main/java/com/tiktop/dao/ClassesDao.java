package com.tiktop.dao;

import java.util.List;

import com.tiktop.model.Classes;

public interface ClassesDao {

	void save(Classes classes);
	
	 List<Classes> findClasses(Classes classes);
	 
	 void delete(Integer id);
	 
	 void update(Classes classes);
}
