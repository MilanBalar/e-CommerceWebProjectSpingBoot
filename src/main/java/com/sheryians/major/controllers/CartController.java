package com.sheryians.major.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sheryians.major.entity.TblProducts;
import com.sheryians.major.service.GlobalData;
import com.sheryians.major.service.ProductsService;

@Controller
public class CartController {

	@Autowired
	ProductsService productsService;

	@GetMapping("/addToCart/{id}")
	public String addcart(@PathVariable long id) {
		GlobalData.cart.add(productsService.getProductById(id).get());
		return "redirect:/shop";
	}

	@GetMapping("/cart")
	public String cartGET(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(TblProducts::getPrice).sum());
		model.addAttribute("cart", GlobalData.cart);
        return "cart";
	}
	@GetMapping("cart/removeItem/{index}")
	public String removeItem(@PathVariable("index") int index) {
		GlobalData.cart.remove(index);
		return "redirect:/cart";
	}
    @GetMapping("/checkout")
    public String checkout(Model model) {
    	model.addAttribute("total", GlobalData.cart.stream().mapToDouble(TblProducts::getPrice).sum());
    	return "checkout";
    }


}
