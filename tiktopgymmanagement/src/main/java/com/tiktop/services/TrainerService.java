package com.tiktop.services;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.tiktop.model.Trainer;

public interface TrainerService {

	void save(Trainer trainer);

	List<Trainer> findTrainer(Trainer trainer);

	void delete(Integer id);

	void update(Trainer trainer);
	
	void init();

	void store(MultipartFile file);

	Stream<Path> loadAll();

	Path load(String filename);
}
