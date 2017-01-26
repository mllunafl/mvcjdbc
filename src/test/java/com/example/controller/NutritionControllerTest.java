package com.example.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class NutritionControllerTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private JdbcTemplate template;
	
	@Test
	public void nutritionsReturnList() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.set("product", "carrots");
	    params.set("calories", "100");
	    params.set("carbs", "100");
	    restTemplate.postForLocation("http://localhost:"+port+"/nutrition", params, Collections.emptyMap());
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/nutritions", String.class)).contains("carrots");
	}
	
	@Test
	public void nutrReturn() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.set("product", "carrots");
	    params.set("calories", "100");
	    params.set("carbs", "100");
	    restTemplate.postForLocation("http://localhost:"+port+"/nutrition", params, Collections.emptyMap());
	    int nutId = this.getMaxIdFromNutrition();
	    assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/view-nutrition/" + nutId, String.class)).contains("carrots");
	    
	    params.set("product", "carrots");
	    params.set("calories", "100");
	    params.set("carbs", "50");
	    restTemplate.postForLocation("http://localhost:" + port + "/edit-nutrition/" + nutId, params, Collections.emptyMap());
	    assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/view-nutrition/" + nutId, String.class)).contains("50");
	}
	
	@Test
	public void nutritionDelte() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.set("product", "pickle");
	    params.set("calories", "100");
	    params.set("carbs", "100");
	    restTemplate.postForLocation("http://localhost:"+port+"/nutrition", params, Collections.emptyMap());
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/nutritions", String.class)).contains("pickle");
		int nutId = this.getMaxIdFromNutrition();
	    restTemplate.postForLocation("http://localhost:"+port+"/delete-nutrition/" + nutId, params, Collections.emptyMap());
	    assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/nutritions", String.class)).doesNotContain("pickle");

	}
	
	@Test
	public void deleteListNutritions() throws Exception {
	List<Integer> nutIds = new ArrayList<>();
	
	MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
    params.set("product", "deleteone");
    params.set("calories", "100");
    params.set("carbs", "100");
    restTemplate.postForLocation("http://localhost:"+port+"/nutrition", params, Collections.emptyMap());
    int nutIdone = this.getMaxIdFromNutrition();
    nutIds.add(nutIdone);
    
    params.set("product", "deletetwo");
    params.set("calories", "100");
    params.set("carbs", "100");
    restTemplate.postForLocation("http://localhost:"+port+"/nutrition", params, Collections.emptyMap());
    int nutIdtwo = this.getMaxIdFromNutrition();
    nutIds.add(nutIdtwo);
    
    params.set("deleteIds", nutIds.toString() );
 
    System.out.println(params);
    restTemplate.postForLocation("http://localhost:"+port+"/nutritions", params, Collections.emptyMap());
    assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/nutritions", String.class)).doesNotContain("deleteone");
    assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/nutritions", String.class)).doesNotContain("deletetwo");
	
	}
	
	private int getMaxIdFromNutrition() {
	       return template.queryForObject("select max(id) from nutrition", null, Integer.class);
	   }
	
	private String getMaxIdFromNutr() {
	       return template.queryForObject("select max(id) from nutrition", null, String.class);
	   }
	
}
