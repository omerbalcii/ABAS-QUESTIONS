package com.example.api.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.api.model.Konu;



@Repository
//@Lazy // veya application properties 'te
public class KonuRepository
{
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<Konu> getAll()
	{
		
		return jdbcTemplate.query("select * from \"public\".\"KONU\" order by \"ID\" asc", BeanPropertyRowMapper.newInstance(Konu.class));
	}

	

	public boolean save(Konu konu)
	{
		String sql = "insert into \"public\".\"KONU\" (\"NAME\") values (:NAME)";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("NAME", konu.getNAME());
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	
}
