package com.tiktop.services;

import java.util.List;

import com.tiktop.model.Classes;

public interface ClassesService {

	void save(Classes classes);

	List<Classes> findClasses(Classes classes);

	void delete(Integer id);

	void update(Classes classes);
}
