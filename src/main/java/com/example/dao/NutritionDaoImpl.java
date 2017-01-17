package com.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.domain.Nutrition;


@Repository
public class NutritionDaoImpl implements NutritionDao {

	private static final Logger log = LoggerFactory.getLogger(NutritionDaoImpl.class);
	
	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Override
	public void add(Nutrition nutrition) {
		log.info("adding " + nutrition);
		jdbctemplate.update("INSERT INTO nutrition (product, calories, carbs) VALUES (?,?,?)",
				nutrition.getProduct(),
				nutrition.getCalories(),
				nutrition.getCarbs());
		log.info("exiting add method" + nutrition);
		
	}

	@Override
	public List<Nutrition> findAll() {
		log.info("finding all");
		List<Nutrition> nutritions = this.jdbctemplate.query("select * from nutrition", new NutritionMapper());
		log.info("exitting findAll");
		return nutritions;
	}
	
	private static class NutritionMapper implements RowMapper<Nutrition>{

		@Override
		public Nutrition mapRow(ResultSet rs, int rowNum) throws SQLException {
			Nutrition nutrition = new Nutrition();
			nutrition.setId(rs.getLong("id"));
			nutrition.setProduct(rs.getString("product"));
			nutrition.setCalories(rs.getInt("calories"));
			nutrition.setCarbs(rs.getInt("carbs"));
			return nutrition;
		}
		
	}

}
