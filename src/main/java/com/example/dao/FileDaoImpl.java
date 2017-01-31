package com.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.domain.File;

public class FileDaoImpl implements FileDao {
	@Autowired
	JdbcTemplate jdbctemplate;
	@Autowired
	NutritionDaoImpl nutritionDao;

	@Override
	public List<String> fileList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFile(int id, File file) {
		if (nutritionDao.find(id) != null) {
			jdbctemplate.update("INSERT INTO file (file_name, nutrition_id) VALUES (?,?)", file.getFileName(), id);
		}
	}

	@Override
	public void deleteFile(int id) {
		if (nutritionDao.find(id) == null) {
			throw new RuntimeException();
		}
		if (nutritionDao.find(id) != null) {
			jdbctemplate.update("DELETE from file WHERE id = ?", id);
		}

	}

}
