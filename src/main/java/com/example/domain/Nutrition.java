package com.example.domain;

import java.io.Serializable;

public class Nutrition implements Serializable {

	private enum FoodGroup {
	    DAIRY, GRAIN, VEGETABLE, FRUIT, PROTEIN
	}
	private long id;
	private String product;
	private int calories;
	private int carbs;
	private FoodGroup foodGroup;

	public FoodGroup getFoodGroup() {
		return foodGroup;
	}
	public void setFoodGroup(FoodGroup foodGroup) {
		this.foodGroup = foodGroup;
	}
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
				+ ", foodGroup=" + foodGroup + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + calories;
		result = prime * result + carbs;
		result = prime * result + ((foodGroup == null) ? 0 : foodGroup.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		if (calories != other.calories)
			return false;
		if (carbs != other.carbs)
			return false;
		if (foodGroup != other.foodGroup)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
}
