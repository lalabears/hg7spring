package com.java.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.Product;
import com.java.service.PService;

@Controller
public class FController {
	@Autowired PService pService;
	@RequestMapping("/")
	public String index() {
		return "main";
	}
	@RequestMapping("/ajax01")
	public String axaj01() {
		return "ajax01";
	}
	@RequestMapping("/ajax02")
	public String ajax02() {
		return "ajax02";
	}
	@RequestMapping("/product")
	public String product() {
		return "product";
	}
	
	@RequestMapping("/productList")
	@ResponseBody
	public ArrayList<Product> productList() {
		ArrayList<Product> plist = pService.selectAll();
		return plist;
	}
	@PostMapping("/insertProductInfo")
	@ResponseBody
	public Product insertProductInfo(Product pdto) {
		System.out.println("name : "+pdto.getName());
		System.out.println("price : "+pdto.getPrice());
		System.out.println("category : "+pdto.getCategory());
		
		Product product = pService.insertProductInfo(pdto);
		
		return product;
	}
	
	
	
	
	
	
	
	
}
