package com.sheryians.major.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.ProductsService;

@Controller
public class HomeController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductsService productsService;

	@GetMapping({"home","/"})
	public String home() {
		return "index";
	}

	@GetMapping("shop")
	public String shop(Model model) {
		model.addAttribute("products", productsService.getAllProducts());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "shop";
	}

	@GetMapping("shop/category/{id}")
	public String shopByCategory(Model model, @PathVariable("id") int id) {
        model.addAttribute("products", productsService.getAllProductbyId(id));
		model.addAttribute("categories", categoryService.getAllCategory());
		return "shop";
	}
	@GetMapping("shop/viewproduct/{id}")
	public String shopByCategory(Model model, @PathVariable("id") long id) {
		model.addAttribute("product",productsService.getProductById(id).get());
        return "viewproduct";
	}


}
