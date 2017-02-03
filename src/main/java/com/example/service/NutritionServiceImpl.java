package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.FileDao;
import com.example.dao.NutritionDao;
import com.example.domain.File;
import com.example.domain.Nutrition;


@Service
public class NutritionServiceImpl implements NutritionService {

	@Autowired
	NutritionDao nutritionDao;
	@Autowired
	FileDao fileDao;
	
	@Override
	@Transactional
	public Nutrition add(Nutrition nutrition) {
		return nutritionDao.save(nutrition);
	}

	@Override
	public List<Nutrition> findAll() {
		return nutritionDao.findAll();
	}

	@Override
	public Nutrition find(long id) {
		return nutritionDao.findOne(id);
	}

	@Override
	@Transactional
	public void update(Nutrition nutrition) {
		nutritionDao.save(nutrition);
	}

	@Override
	@Transactional
	public void delete(long id) {
		nutritionDao.delete(id);
	}

	@Override
	@Transactional
	public void delete(List<Long> ids) {
		for(long id: ids){
			nutritionDao.delete(id);
		}
	}

	@Override
	public Nutrition addFile(Long id, String fileName) {
		Nutrition nutrition = nutritionDao.findOne(id);
		File myFile = new File();
		myFile.setFileName(fileName);
		myFile.setNutrition(nutrition);
		myFile = fileDao.save(myFile);
		nutrition.getFiles().add(myFile);
		nutrition = nutritionDao.save(nutrition);
		return nutrition;
	}	
	
}
