package com.sheryians.major.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheryians.major.entity.TblProducts;
import com.sheryians.major.repository.ProductsRepository;

@Service
public class ProductsService {

	@Autowired
	private ProductsRepository productsRepository;

	  //get all Products
		public List<TblProducts> getAllProducts(){
			return productsRepository.findAll();
		}

}
