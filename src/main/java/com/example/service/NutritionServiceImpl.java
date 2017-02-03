package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.FileDao;
import com.example.dao.NutritionDao;
import com.example.domain.Nutrition;


@Service
public class NutritionServiceImpl implements NutritionService {

	@Autowired
	NutritionDao nutritionDao;
	@Autowired
	FileDao fileDao;
	
	@Override
	@Transactional
	public int add(Nutrition nutrition) {
		return nutritionDao.add(nutrition);
	}

	@Override
	public List<Nutrition> findAll() {
		return nutritionDao.findAll();
	}

	@Override
	public Nutrition find(int id) {
		Nutrition nutrition = nutritionDao.find(id);
		List<String> fileNutList = nutrition.getFilenames();
		List<String> fileList = fileDao.fileList(id);
		fileNutList.addAll(0, fileList);
		System.out.println(fileNutList);
		return nutrition;
	}

	@Override
	@Transactional
	public void update(Nutrition nutrition) {
		nutritionDao.update(nutrition);
	}

	@Override
	@Transactional
	public void delete(long id) {
		nutritionDao.delete(id);
	}

	@Override
	@Transactional
	public void delete(List<Long> ids) {
		nutritionDao.delete(ids);
	}	
}
