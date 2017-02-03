package com.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.domain.Nutrition;

@Repository
public class FileDaoImpl implements FileDao {
	@Autowired
	JdbcTemplate jdbctemplate;
	@Autowired
	NutritionDaoImpl nutritionDao;
	
	
	@Override
	public List<String> fileList(int id) {
		List<String> fileNames = new ArrayList<>(); 
		if (nutritionDao.find(id) != null) {
			fileNames = jdbctemplate.query("select file_name from file WHERE nutrition_id = ?", new RowMapper<String>(){

				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString("file_name");
				}
				
			}, id);
		}
		System.out.println(fileNames);
		return fileNames;
	}

	@Override
	public void addFile(int id, String file) {
		if (nutritionDao.find(id) != null) {
			jdbctemplate.update("INSERT INTO file (file_name, nutrition_id) VALUES (?,?)", file, id);
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
