package com.sheryians.major.service;

import java.util.List;
import java.util.Optional;

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
		public Optional<TblProducts> getProductById(Long id) {
			return productsRepository.findById(id);
		}
		public void addProduct(TblProducts products) {
		    productsRepository.save(products);
		}
        public void deleteProduct(long id) {
        	productsRepository.deleteById(id);
        }
        public Optional<TblProducts> getProduct(long id) {
        	 return productsRepository.findById(id);
        }

}
