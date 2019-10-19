package com.tiktop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiktop.mapper.ClassesMapper;
import com.tiktop.model.Classes;

@Repository("classesRepository")
public class ClassesDaoImpl implements ClassesDao {

	@Autowired
	ClassesMapper classesMapper;
	
	public void save(Classes classes) {
		classesMapper.save(classes);
	}
	
	public List<Classes> findClasses(Classes classes) {
		List<Classes> classess = classesMapper.findClasses(classes);
		return classess;
	}
	
	public void delete(Integer id) {
		classesMapper.delete(id);
	}
	
	public void update(Classes classes) {
		classesMapper.update(classes);
	}
}
