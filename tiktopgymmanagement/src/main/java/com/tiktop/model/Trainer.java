package com.tiktop.model;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Trainer {
	public Integer trainerId;
	@NotNull
	public String trainerName;
	@NotNull
	public String trainerType;
	public MultipartFile trainerImage;
	public final String imagePath = "src/main/resources/static/trainer-image/";
	@NotNull
	public String trainerPhone;
	@NotNull
	public String trainerAddress;
	public String createDate;
	public String updateDate;
	@NotNull
	public String trainerDescription;
	@NotNull
	public Integer trainerFees;
	public String trainerImageName;
 	
	public String getTrainerImageName() {
		return trainerImageName;
	}

	public void setTrainerImageName(String trainerImageName) {
		this.trainerImageName = trainerImageName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getTrainerId() {
		return trainerId;
	}
	
	public void setTrainerId(Integer trainerId) {
		this.trainerId = trainerId;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public String getTrainerType() {
		return trainerType;
	}

	public void setTrainerType(String trainerType) {
		this.trainerType = trainerType;
	}

	public MultipartFile getTrainerImage() {
		return trainerImage;
	}

	public void setTrainerImage(MultipartFile trainerImage) {
		this.trainerImage = trainerImage;
	}

	public String getTrainerPhone() {
		return trainerPhone;
	}

	public void setTrainerPhone(String trainerPhone) {
		this.trainerPhone = trainerPhone;
	}

	public String getTrainerAddress() {
		return trainerAddress;
	}

	public void setTrainerAddress(String trainerAddress) {
		this.trainerAddress = trainerAddress;
	}
	
	public String getImagePath() {
		return imagePath;
	}

	public String getTrainerDescription() {
		return trainerDescription;
	}

	public void setTrainerDescription(String trainerDescription) {
		this.trainerDescription = trainerDescription;
	}

	public Integer getTrainerFees() {
		return trainerFees;
	}

	public void setTrainerFees(Integer trainerFees) {
		this.trainerFees = trainerFees;
	}
}
