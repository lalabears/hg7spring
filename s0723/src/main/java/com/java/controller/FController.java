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
	
	@PostMapping("/pList")
	@ResponseBody
	public ArrayList<Product> plist(){
		ArrayList<Product> list =pService.selectAll();
		return list;
	}
	
	@PostMapping("/insertProduct")
	@ResponseBody
	public Product insertProduct(Product pdto){
		//System.out.println(pdto.getName());
		//System.out.println(pdto.getCategory());
		//System.out.println(pdto.getPrice());
		
		Product product = pService.insertOne(pdto);
		System.out.println("pcontroller "+product.getPno());
		System.out.println("pcontroller "+product.getName());
		return product;
	}
	
	
	
	
	
	
	
	
}
