package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Nutrition;


public interface NutritionDao extends JpaRepository<Nutrition, Long> {
}
