package com.CWS.blog.service;

import java.util.List;

import com.CWS.blog.dto.CategoryDto;

public interface CategoryService {

	 CategoryDto createCategory(CategoryDto categoryDto);
	 CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	 void deleteCategory(Integer categoryId);
	 CategoryDto getCategory(Integer categoryId);
	 List<CategoryDto> getAllCategory();

}
