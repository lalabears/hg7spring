package com.java.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.Product;
import com.java.mapper.PMapper;

@Service
public class PServiceImpl implements PService {
	@Autowired PMapper pMapper;
	@Override
	public ArrayList<Product> selectAll() {
		ArrayList<Product> list = pMapper.selectAll();
		return list;
	}
	@Override
	public Product insertOne(Product pdto) {
		System.out.println(pdto.getPno());
		System.out.println(pdto.getName());
		pMapper.insertOne(pdto);		
		Product product = pMapper.selectOne(pdto);
		return product;
	}

}
