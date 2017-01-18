package com.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.domain.Nutrition;


@Repository
public class NutritionDaoImpl implements NutritionDao {

	private static final Logger log = LoggerFactory.getLogger(NutritionDaoImpl.class);
	
	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public Nutrition find(int id) {
		List<Nutrition> nutritions = jdbctemplate.query("select * from nutrition WHERE id = ?", new NutritionMapper(), id);
		if(nutritions.size() > 0){
			return nutritions.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void update(Nutrition nutrition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(List<Long> ids) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int add(Nutrition nutrition) {
		log.info("adding " + nutrition);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("prodcut", nutrition.getProduct());
		params.addValue("calories", nutrition.getCalories());
		params.addValue("carbs", nutrition.getCarbs());
		
		String [] keys = {"id"};
		jdbctemplate.update("INSERT INTO nutrition (product, calories, carbs) VALUES (?,?,?)",params, keyHolder, keys);
		log.info("exiting add method" + nutrition);
		return keyHolder.getKey().intValue();
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
