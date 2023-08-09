package com.CWS.blog.repository;

import org.springframework.data.repository.CrudRepository;

import com.CWS.blog.entity.Category;

public interface CategoryRepo extends CrudRepository<Category, Integer>{
	
}


