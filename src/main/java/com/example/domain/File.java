package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name= "file")
public class File {
	
	private int id;
	private int nutrition_id;
	private String fileName;
	private Nutrition nutrition;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne()
	@JoinColumn(name = "nutrition_id")
	public Nutrition getNutrition() {
		return nutrition;
	}
	public void setNutrition(Nutrition nutrition) {
		this.nutrition = nutrition;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "File [id=" + id + ", nutrition_id=" + nutrition_id + ", fileName=" + fileName + "]";
	}	
	
}
