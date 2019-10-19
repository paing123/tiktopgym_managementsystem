package com.tiktop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.tiktop.model.Package;

@Mapper
public interface PackageMapper {
	public void save(@Param("pack") Package pack);
	
	public List<Package> findPackage(@Param("pack") Package pack);
	
	public void update(@Param("pack") Package pack);
	
	public void delete(@Param("id") Integer id);
}
