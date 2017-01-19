package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Nutrition;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NutritionServiceTest {
	Random random = new Random();

	@Autowired
	NutritionService nutritionService;

	@Test
	public void testUpdateFail() {
		Nutrition nutrition1 = this.createNewNutrition();
		int nut1Id = nutritionService.add(nutrition1);

		Nutrition nutrition2 = nutritionService.find(nut1Id);
		nutrition2.setProduct("A VarChar that is tooooooooooooooooooo longggggg");
		nutrition2.setCalories(random.nextInt());
		try {
			nutritionService.update(nutrition2);
			Assert.fail("Should not be able to update a nutriton with product that is over 40" + nutrition2);
		} catch (DataIntegrityViolationException e) {
			Assert.assertTrue(true);
		}

	}
	
	@Test
	public void testDeleteListFail(){
		Nutrition nutriton1 = this.createNewNutrition();
		Nutrition nutrion2 = this.createNewNutrition();
		int id1 = nutritionService.add(nutriton1);
		int id2 = nutritionService.add(nutrion2);

		Assert.assertNotNull(nutritionService.find(id1));
		Assert.assertNotNull(nutritionService.find(id2));

		List<Long> nutritionIds = new ArrayList<>();
		nutritionIds.add((long) id1);
		long id3 = 100000000;
		nutritionIds.add(id3);
		try{
		nutritionService.delete(nutritionIds);
		Assert.fail("should not be able to delete id3 not valid" + id3);
		}catch(DataIntegrityViolationException e){
			Assert.assertTrue(true);
		}
		
		List<Nutrition> nutritions = nutritionService.findAll();
		boolean found = false;
		for(Nutrition n : nutritions){
			if(n.equals(nutriton1)){
				found = true;
				Assert.assertTrue(true);
				break;
			}
		}
		Assert.assertTrue("Should find " + id1, !found);
	}

	@Test
	public void testDelete() {
		int id = nutritionService.add(createNewNutrition());
		Nutrition nutrition2 = nutritionService.find(id);
		Assert.assertNotNull(nutrition2);
		nutritionService.delete(id);
		Nutrition nutrtion3 = nutritionService.find(id);
		Assert.assertNull(nutrtion3);
	}

	@Test
	public void testUpdate() {
		Nutrition nutrition1 = this.createNewNutrition();
		int nut1Id = nutritionService.add(nutrition1);

		Nutrition nutrition2 = nutritionService.find(nut1Id);
		nutrition2.setCalories(random.nextInt());
		nutrition2.setCarbs(random.nextInt());
		nutritionService.update(nutrition2);

		Nutrition nutrition3 = nutritionService.find(nut1Id);
		Assert.assertEquals(nutrition2, nutrition3);
	}

	@Test
	public void testDeleteList() {
		Nutrition nutriton1 = this.createNewNutrition();
		Nutrition nutrion2 = this.createNewNutrition();
		int id1 = nutritionService.add(nutriton1);
		int id2 = nutritionService.add(nutrion2);

		Assert.assertNotNull(nutritionService.find(id1));
		Assert.assertNotNull(nutritionService.find(id2));

		List<Long> nutritionIds = new ArrayList<>();
		nutritionIds.add((long) id1);
		nutritionIds.add((long) id2);

		nutritionService.delete(nutritionIds);

		Assert.assertNull(nutritionService.find(id1));
		Assert.assertNull(nutritionService.find(id2));

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

		int id = nutritionService.add(nutrition);
		Nutrition nutrition2 = nutritionService.find(id);
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

		nutritionService.add(nutrition);

		List<Nutrition> nutritions = nutritionService.findAll();
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

	private Nutrition createNewNutrition() {
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
