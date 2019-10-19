package com.tiktop.dao;

import java.util.List;

import com.tiktop.model.Trainer;

public interface TrainerDao {

	void save(Trainer trainer);

	List<Trainer> findTrainer(Trainer trainer);

	void delete(Integer id);

	void update(Trainer trainer);
}
