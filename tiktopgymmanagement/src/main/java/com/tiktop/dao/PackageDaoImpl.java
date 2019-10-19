package com.tiktop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiktop.mapper.PackageMapper;
import com.tiktop.model.Package;

@Repository("PackageRepository")
public class PackageDaoImpl implements PackageDao {

	@Autowired
	PackageMapper packageMapper;
	
	public void save(Package pack) {
		packageMapper.save(pack);
	}
	
	public List<Package> findPackage(Package pack) {
		List<Package> packs = packageMapper.findPackage(pack);
		return packs;
	}
	
	public void delete(Integer id) {
		packageMapper.delete(id);
	}
	
	public void update(Package pack) {
		packageMapper.update(pack);
	}
}
