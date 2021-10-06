package com.sheryians.major.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheryians.major.entity.TblCategories;
import com.sheryians.major.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
    private CategoryRepository categoryRepository;

	//addCategory
	public void addCategory(TblCategories categories)
	{
		categoryRepository.save(categories);
	}

	//get all Category
	public List<TblCategories> getAllCategory()
	{
		return categoryRepository.findAll();
	}
	public void deleteData(int id) {
		categoryRepository.deleteById(id);
	}
	public Optional<TblCategories> getCategoryById(int id) {
		return categoryRepository.findById(id);
	}
}
