package com.tiktop.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tiktop.dao.TrainerDao;
import com.tiktop.model.Trainer;

@Service("trainerService")
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	TrainerDao trainerDao;

	private final Path rootLocation;

	public TrainerServiceImpl(Trainer trainer) {
		this.rootLocation = Paths.get(trainer.getImagePath());
	} 
	
	@Override
	public void save(Trainer trainer) {
		trainerDao.save(trainer);
	}

	@Override
	public List<Trainer> findTrainer(Trainer trainer) {
		return trainerDao.findTrainer(trainer);
	}
	
	@Override
	public void delete(Integer id) {
		trainerDao.delete(id);
	}

	@Override
	public void update(Trainer trainer) {
		trainerDao.update(trainer);
	}
	
	@Override
	public void store(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try (InputStream inputStream = file.getInputStream()) {
			Files.copy(inputStream, this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(this.rootLocation::relativize);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	// create directory
	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

}
