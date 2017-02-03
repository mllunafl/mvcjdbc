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
	public Nutrition find(int id) {
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
		nutritionDao.delete((int) id);
	}

	@Override
	@Transactional
	public void delete(List<Long> ids) {
		for(long id: ids){
			nutritionDao.delete((int) id);
		}
	}

	@Override
	public Nutrition addFile(Integer id, String fileName) {
		File myFile = new File();
		myFile.setFileName(fileName);
		myFile = fileDao.save(myFile);
		Nutrition nutrition = nutritionDao.findOne(id);
		nutrition.getFiles().add(myFile);
		nutrition = nutritionDao.save(nutrition);
		return nutrition;
	}	
	
}
