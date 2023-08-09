package com.CWS.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CWS.blog.dto.CategoryDto;
import com.CWS.blog.entity.Category;
import com.CWS.blog.exception.ResourceNotFoundException;
import com.CWS.blog.repository.CategoryRepo;
import com.CWS.blog.service.CategoryService;

@Service
public class CategoryServiceIMPL implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat= this.modelMapper.map(categoryDto, Category.class);
		Category addCat= this.categoryRepo.save(cat);
		return this.modelMapper.map(addCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCat = this.categoryRepo.save(cat);
		return this.modelMapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {

		Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
		// line 52 
		return this.modelMapper.map(cat, CategoryDto.class);
		//return cat; // wee cant do this becoz type is mismatching here to reslove this we will first covert by help of modelMapper

	}

	@Override
	public List<CategoryDto> getAllCategory() {
		
		List<Category> categories =(List<Category>) this.categoryRepo.findAll();
//		we cannot return categories becoz we are passing List<Category> in method
		List<CategoryDto> catDtos = categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());			
		
		return catDtos;
	}

	
}
