package com.example.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Nutrition;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NutritionCrudTest {

	protected Random random = new Random();

	protected NutritionCrud nutritionCrud;

	@Test
	public void testDelete(){
		int id = nutritionCrud.add(createNewNutrition());
		Nutrition nutrition2 = nutritionCrud.find(id);
		Assert.assertNotNull(nutrition2);
		nutritionCrud.delete(id);
		Nutrition nutrtion3 = nutritionCrud.find(id);
		Assert.assertNull(nutrtion3);
	}
	
	@Test
	public void testUpdate() {
		Nutrition nutrition1 = this.createNewNutrition();
		int nut1Id = nutritionCrud.add(nutrition1);
		
		Nutrition nutrition2 = nutritionCrud.find(nut1Id);
		nutrition2.setCalories(random.nextInt());
		nutrition2.setCarbs(random.nextInt());
		nutritionCrud.update(nutrition2);
		
		Nutrition nutrition3 = nutritionCrud.find(nut1Id);
		Assert.assertEquals(nutrition2, nutrition3);
	}
	
	@Test
	public void testDeleteList(){
		Nutrition nutriton1 = this.createNewNutrition();
		Nutrition nutrion2 = this.createNewNutrition();
		int id1 = nutritionCrud.add(nutriton1);
		int id2 = nutritionCrud.add(nutrion2);
		
		Assert.assertNotNull(nutritionCrud.find(id1));
		Assert.assertNotNull(nutritionCrud.find(id2));
		
		List<Long> nutritionIds = new ArrayList<>();
		nutritionIds.add((long) id1);
		nutritionIds.add((long) id2);
		
		nutritionCrud.delete(nutritionIds);
		
		Assert.assertNull(nutritionCrud.find(id1));
		Assert.assertNull(nutritionCrud.find(id2));
		
	}
	
	@Test
	public void testFind() {
		Nutrition nutrition = new Nutrition();

		String product = Integer.toString(random.nextInt());
		nutrition.setProduct(product);

		int calories = random.nextInt();
		nutrition.setCalories(calories);

		int carbs = random.nextInt();
		nutrition.setCarbs(carbs);

		int id = nutritionCrud.add(nutrition);
		Nutrition nutrition2 = nutritionCrud.find(id);
		Assert.assertEquals(nutrition, nutrition2);
	}

	@Test
	public void testCreate() {
		Nutrition nutrition = new Nutrition();

		String product = Integer.toString(random.nextInt());
		nutrition.setProduct(product);

		int calories = random.nextInt();
		nutrition.setCalories(calories);

		int carbs = random.nextInt();
		nutrition.setCarbs(carbs);

		nutritionCrud.add(nutrition);

		List<Nutrition> nutritions = nutritionCrud.findAll();
		Assert.assertNotNull(nutritions);
		Assert.assertTrue(nutritions.size() > 0);
		boolean found = false;
		for (Nutrition nut : nutritions) {
			if (nut.equals(nutrition)) {
				found = true;
				break;
			}
		}

		Assert.assertTrue("Count not find " + nutrition, found);

	}
	
	protected Nutrition createNewNutrition(){
		Nutrition nutrition = new Nutrition();

		String product = Integer.toString(random.nextInt());
		nutrition.setProduct(product);

		int calories = random.nextInt();
		nutrition.setCalories(calories);

		int carbs = random.nextInt();
		nutrition.setCarbs(carbs);
		
		return nutrition;
	}

}
