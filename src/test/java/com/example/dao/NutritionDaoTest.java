package com.example.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Nutrition;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NutritionDaoTest {

	Random random = new Random();

	@Autowired
	NutritionDao nutritionDao;

	@Test
	public void testDelete(){
		int id = nutritionDao.add(createNewNutrition());
		Nutrition nutrition2 = nutritionDao.find(id);
		Assert.assertNotNull(nutrition2);
		nutritionDao.delete(id);
		Nutrition nutrtion3 = nutritionDao.find(id);
		Assert.assertNull(nutrtion3);
	}
	
	@Test
	public void testUpdate() {
		Nutrition nutrition1 = this.createNewNutrition();
		int nut1Id = nutritionDao.add(nutrition1);
		
		Nutrition nutrition2 = nutritionDao.find(nut1Id);
		nutrition2.setCalories(random.nextInt());
		nutrition2.setCarbs(random.nextInt());
		nutritionDao.update(nutrition2);
		
		Nutrition nutrition3 = nutritionDao.find(nut1Id);
		Assert.assertEquals(nutrition2, nutrition3);
	}
	
	@Test
	public void testDeleteList(){
		Nutrition nutriton1 = this.createNewNutrition();
		Nutrition nutrion2 = this.createNewNutrition();
		int id1 = nutritionDao.add(nutriton1);
		int id2 = nutritionDao.add(nutrion2);
		
		Assert.assertNotNull(nutritionDao.find(id1));
		Assert.assertNotNull(nutritionDao.find(id2));
		
		List<Long> nutritionIds = new ArrayList<>();
		nutritionIds.add((long) id1);
		nutritionIds.add((long) id2);
		
		nutritionDao.delete(nutritionIds);
		
		Assert.assertNull(nutritionDao.find(id1));
		Assert.assertNull(nutritionDao.find(id2));
		
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

		int id = nutritionDao.add(nutrition);
		Nutrition nutrition2 = nutritionDao.find(id);
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

		nutritionDao.add(nutrition);

		List<Nutrition> nutritions = nutritionDao.findAll();
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
	
	private Nutrition createNewNutrition(){
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
