package com.example;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.dao.NutritionDaoTest;
import com.example.service.NutritionServiceTest;


@RunWith(Suite.class)

@Suite.SuiteClasses({
   MvcjdbcApplicationTests.class,
  NutritionDaoTest.class,
  NutritionServiceTest.class
})

public class TestSuite {}

