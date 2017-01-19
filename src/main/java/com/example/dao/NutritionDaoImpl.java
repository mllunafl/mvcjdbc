package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.domain.Nutrition;
import com.mysql.jdbc.Statement;

@Repository
public class NutritionDaoImpl implements NutritionDao {

	private static final Logger log = LoggerFactory.getLogger(NutritionDaoImpl.class);

	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public Nutrition find(int id) {
		List<Nutrition> nutritions = jdbctemplate.query("select * from nutrition WHERE id = ?", new NutritionMapper(),
				id);
		if (nutritions.size() > 0) {
			return nutritions.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void update(Nutrition nutrition) {
		if(this.find((int) nutrition.getId())!=null){
		jdbctemplate.update("UPDATE nutrition set product = ?, calories = ?, carbs = ? WHERE id = ?",
				nutrition.getProduct(),
				nutrition.getCalories(),
				nutrition.getCarbs(),
				nutrition.getId());
		}
	}

	@Override
	public void delete(long id) {
		int intId = (int) id;
		if(this.find(intId)!=null){
			jdbctemplate.update("DELETE from nutrition WHERE id = ?", id);
		}
	}

	@Override
	public void delete(List<Long> ids) {
		for (Long id: ids){
			int intId = id.intValue();
			if(find(intId)!=null){
			this.delete(id);
			}
		}
	}

	@Override
	public int add(Nutrition nutrition) {
		log.info("adding " + nutrition);
		KeyHolder holder = new GeneratedKeyHolder();
		String sql = "INSERT INTO nutrition (product, calories, carbs) VALUES (?,?,?)";
		jdbctemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, nutrition.getProduct());
				ps.setInt(2, nutrition.getCalories());
				ps.setInt(3, nutrition.getCarbs());
				return ps;
			}
		}, holder);
		log.info("exiting add method" + nutrition);
		return (int) holder.getKey().longValue();
	}

	@Override
	public List<Nutrition> findAll() {
		log.info("finding all");
		List<Nutrition> nutritions = this.jdbctemplate.query("select * from nutrition", new NutritionMapper());
		log.info("exitting findAll");
		return nutritions;
	}

	private static class NutritionMapper implements RowMapper<Nutrition> {

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
