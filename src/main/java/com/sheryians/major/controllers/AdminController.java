package com.sheryians.major.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sheryians.major.dto.ProductsDTO;
import com.sheryians.major.entity.TblCategories;
import com.sheryians.major.entity.TblProducts;
import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.ProductsService;

@Controller
public class AdminController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductsService productsService;

	public final static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	//private final static String uploadDir="D:\\product-pic";

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
    @PostMapping("/admin/products/add")
    public String addProductpost(@ModelAttribute("productDTO") ProductsDTO productDTO,
    		@RequestParam("productImage") MultipartFile file,
    		@RequestParam("imgName") String imgName,
    		Model model) throws IOException {

    	TblProducts tbProducts=new TblProducts();
    	if(productDTO.getProductId()!=0 && !ObjectUtils.isEmpty(productDTO.getProductId())) {
    		tbProducts.setProductId(productDTO.getProductId());
        }
    	tbProducts.setName(productDTO.getName());
    	tbProducts.setTblCategories(categoryService.getCategoryById(productDTO.getTblCategoriesId()).get());
    	tbProducts.setPrice(productDTO.getPrice());
    	tbProducts.setWeight(productDTO.getWeight());
    	tbProducts.setDescription(productDTO.getDescription());
        String imageName;
    	if(!file.isEmpty()) {
    		Random random=new Random();
    		imageName =random. nextInt(100000)+"_"+file.getOriginalFilename();
    		Path path;
			try {
				path = Paths.get(uploadDir, imageName);//here path contain file name and path
				Files.write(path, file.getBytes());
			} catch (Exception e) {
				e.printStackTrace();
			}

        }else {
    		imageName=imgName;
    	}
    	tbProducts.setImageName(imageName);
        productsService.addProduct(tbProducts);
       return "redirect:/admin/products";
    }

    @GetMapping("admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id,Model model) {
    	try {
    	   productsService.deleteProduct(id);
    	   return "redirect:/admin/products";
    	}catch (Exception e) {
    		e.printStackTrace();
			return "pagenotfound";
		}

    }
    @GetMapping("admin/product/update/{id}")
    public String updateProduct(@PathVariable("id") long id,Model model) {
    	try {
			Optional<TblProducts> productOptional = productsService.getProductById(id);
			if (productOptional.isPresent()) {
				 TblProducts tblProducts = productsService.getProductById(id).get();
				ProductsDTO productsDTO=new ProductsDTO();
				productsDTO.setProductId(tblProducts.getProductId());
				productsDTO.setName(tblProducts.getName());
				productsDTO.setTblCategoriesId(tblProducts.getTblCategories().getCategoryId());
				productsDTO.setPrice(tblProducts.getPrice());
				productsDTO.setWeight(tblProducts.getWeight());
				productsDTO.setImageName(tblProducts.getImageName());
				productsDTO.setDescription(tblProducts.getDescription());

				model.addAttribute("productDTO", productsDTO);
				model.addAttribute("categories",categoryService.getAllCategory());
				return "productsAdd";
			} else {
				return "pagenotfound";
			}



    	}catch (Exception e) {
    		e.printStackTrace();
			return "pagenotfound";
		}

    }


    /*-----------for products logic end-----------*/

}
