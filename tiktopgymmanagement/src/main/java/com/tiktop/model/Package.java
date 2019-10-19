package com.tiktop.model;

import javax.validation.constraints.NotNull;

public class Package {
	
	public Integer packageId;
	@NotNull
	public String packageName;
	@NotNull
	public Integer packageDuration;
	@NotNull
	public Integer packageFees;
	public String createDate;
	public String updateDate;
	
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

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Integer getPackageFees() {
		return packageFees;
	}

	public void setPackageFees(Integer packageFees) {
		this.packageFees = packageFees;
	}

	public Integer getPackageDuration() {
		return packageDuration;
	}

	public void setPackageDuration(Integer packageDuration) {
		this.packageDuration = packageDuration;
	}
}
