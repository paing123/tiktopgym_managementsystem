package com.tiktop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiktop.dao.ClassesDao;
import com.tiktop.model.Classes;

@Service("classesService")
public class ClassesServiceImpl implements ClassesService {

	@Autowired
	ClassesDao classesDao;

	@Override
	public void save(Classes classes) {
		classesDao.save(classes);
	}

	@Override
	public List<Classes> findClasses(Classes classes) {
		return classesDao.findClasses(classes);
	}
	
	@Override
	public void delete(Integer id) {
		classesDao.delete(id);
	}

	@Override
	public void update(Classes classes) {
		classesDao.update(classes);
	}
}
