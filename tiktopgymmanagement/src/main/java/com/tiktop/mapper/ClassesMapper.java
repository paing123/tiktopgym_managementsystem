package com.tiktop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tiktop.model.Classes;

@Mapper
public interface ClassesMapper {
	public void save(@Param("classes") Classes classes);
	
	public List<Classes> findClasses(@Param("classes") Classes classes);
	
	public void update(@Param("classes") Classes classes);
	
	public void delete(@Param("id") Integer id);
}
