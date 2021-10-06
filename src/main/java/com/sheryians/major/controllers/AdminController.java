package com.sheryians.major.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sheryians.major.dto.ProductsDTO;
import com.sheryians.major.entity.TblCategories;
import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.ProductsService;

@Controller
public class AdminController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductsService productsService;

	@GetMapping("/admin")
	public String adminHome()
	{
		return "adminHome";
	}

	/*-----------for category logic start-----------*/

	@GetMapping("/admin/categories")
	public String getAllCategories(Model model)
	{   model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}
	@GetMapping("/admin/categories/add")
	public String getCategories(Model model)
	{
		model.addAttribute("category", new TblCategories());
		return "categoriesAdd";
	}
	@PostMapping("/admin/categories/add")
	public String postCategories(@ModelAttribute("category") TblCategories categories)
	{
		categoryService.addCategory(categories);
		return "redirect:/admin/categories";
	}
    @GetMapping("/admin/categories/delete/{id}")
	public String deleteCategories(@PathVariable("id") int id)
	{
		try {
			categoryService.deleteData(id);
			return "redirect:/admin/categories";
		} catch (Exception e) {
			e.printStackTrace();
			return "pagenotfound";
		}
    }
    @GetMapping("/admin/categories/update/{id}")
	public String updateCategories(@PathVariable("id") int id,Model model,Principal principal)
	{
		try {
            Optional<TblCategories> category = categoryService.getCategoryById(id);
            if(category.isPresent()) {
            	model.addAttribute("category", category.get());
            	return "categoriesAdd";
            }else {
            	return "pagenotfound";
            }
         } catch (Exception e) {
			e.printStackTrace();
			return "pagenotfound";
		}
    }
    /*-----------for category logic end-----------*/

	/*-----------for products logic start-----------*/
    @GetMapping("/admin/products")
    public String product(Model model) {
    	model.addAttribute("products", productsService.getAllProducts());
    	return "products";
    }

    @GetMapping("/admin/products/add")
    public String addProduct(Model model) {
    	model.addAttribute("productDTO", new ProductsDTO());
    	model.addAttribute("categories", categoryService.getAllCategory());
    	return "productsAdd";
    }



    /*-----------for products logic end-----------*/

}
