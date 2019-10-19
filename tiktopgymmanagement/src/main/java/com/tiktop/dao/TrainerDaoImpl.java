package com.tiktop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiktop.mapper.TrainerMapper;
import com.tiktop.model.Trainer;

@Repository("trainerRepository")
public class TrainerDaoImpl implements TrainerDao {
	
	@Autowired
	TrainerMapper trainerMapper;
	
	public void save(Trainer trainer) {
		trainerMapper.save(trainer);
	}
		
	public List<Trainer> findTrainer(Trainer trainer) {
		List<Trainer> trainers = trainerMapper.findTrainer(trainer);
		return trainers;
	}
	
	public void delete(Integer id) {
		trainerMapper.delete(id);
	}
	
	public void update(Trainer trainer) {
		trainerMapper.update(trainer);
	}
}
