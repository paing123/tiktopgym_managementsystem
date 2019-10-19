package com.tiktop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tiktop.model.Trainer;

@Mapper
public interface TrainerMapper {
	public void save(@Param("trainer") Trainer trainer);
	
	public List<Trainer> findTrainer(@Param("trainer") Trainer trainer);
	
	public void update(@Param("trainer") Trainer trainer);
	
	public void delete(@Param("id") Integer id);
}
