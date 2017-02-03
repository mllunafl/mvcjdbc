package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.common.NutritionCrudTest;
import com.example.domain.Nutrition;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NutritionServiceTest extends NutritionCrudTest {

	@Autowired
	NutritionService nutritionService;
	
	@Before
	public void before(){
		nutritionCrud = nutritionService;
	}

	@Test
	public void testUpdateFail() {
		Nutrition nutrition1 = this.createNewNutrition();
		int nut1Id = (int) nutritionService.add(nutrition1).getId();

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
		int id1 = (int) nutritionService.add(nutriton1).getId();
		int id2 = (int) nutritionService.add(nutrion2).getId();

		Assert.assertNotNull(nutritionService.find(id1));
		Assert.assertNotNull(nutritionService.find(id2));

		List<Long> nutritionIds = new ArrayList<>();
		nutritionIds.add((long) id1);
		long id3 = 100000000;
		nutritionIds.add(id3);
		try{
		nutritionService.delete(nutritionIds);
		Assert.fail("should not be able to delete id3 not valid" + id3);
		}catch(RuntimeException e){
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
		Assert.assertTrue("Should find " + id1, found);
	}

	
}
