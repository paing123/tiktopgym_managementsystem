package com.tiktop.dao;

import java.util.List;

import com.tiktop.model.Package;

public interface PackageDao {

	void save(Package pack);
	
	 List<Package> findPackage(Package pack);
	 
	 void delete(Integer id);
	 
	 void update(Package pack);
}
