package com.example.domain;

public class File {

	private int id;
	private int nutrition_id;
	private String fileName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNutrition_id() {
		return nutrition_id;
	}
	public void setNutrition_id(int nutrition_id) {
		this.nutrition_id = nutrition_id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "File [id=" + id + ", nutrition_id=" + nutrition_id + ", fileName=" + fileName + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + nutrition_id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		File other = (File) obj;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (nutrition_id != other.nutrition_id)
			return false;
		return true;
	}
	
	
}
