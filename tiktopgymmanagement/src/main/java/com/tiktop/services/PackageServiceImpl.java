package com.tiktop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiktop.dao.PackageDao;
import com.tiktop.model.Package;

@Service("packageService")
public class PackageServiceImpl implements PackageService {

	@Autowired
	PackageDao packageDao;

	@Override
	public void save(Package pack) {
		packageDao.save(pack);
	}

	@Override
	public List<Package> findPackage(Package pack) {
		return packageDao.findPackage(pack);
	}
	
	@Override
	public void delete(Integer id) {
		packageDao.delete(id);
	}

	@Override
	public void update(Package pack) {
		packageDao.update(pack);
	}
}
