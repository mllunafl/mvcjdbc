package com.example.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table( name = "nutrition")
public class Nutrition implements Serializable {
	
	private long id;
	
	@NotNull
	@Size(min=1,max=40)
	private String product;

	@NotNull
	private int calories;
	
	@NotNull
	private int carbs;
	
	private List<File> files = new ArrayList<>();
	
	@OneToMany(mappedBy = "nutrition", fetch=FetchType.LAZY)
	public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> files) {
		this.files = files;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public int getCarbs() {
		return carbs;
	}
	public void setCarbs(int carbs) {
		this.carbs = carbs;
	}

	@Override
	public String toString() {
		return "Nutrition [id=" + id + ", product=" + product + ", calories=" + calories + ", carbs=" + carbs
				+ ", file=" + files + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Nutrition other = (Nutrition) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
