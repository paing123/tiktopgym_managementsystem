package com.tiktop.model;

import javax.validation.constraints.NotNull;

public class Classes {

	public Integer classesId;
	@NotNull
	public String classesName;
	public String createDate;
	public String updateDate;
	
	public Integer getClassesId() {
		return classesId;
	}
	
	public void setClassesId(Integer classesId) {
		this.classesId = classesId;
	}

	public String getClassesName() {
		return classesName;
	}

	public void setClassesName(String classesName) {
		this.classesName = classesName;
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
}
