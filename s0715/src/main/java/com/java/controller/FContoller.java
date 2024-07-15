package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.dto.BoardDto;
import com.java.service.BService;
import com.java.service.BServiceImpl;
import com.java.service.BServiceImpl2;

@Controller
public class FContoller {
	// iv 
	// autowired는 객체를 생성하지 않아도 사용할 수 있게 해줌 : DI라고함
	// 스프링은 내가 객체를 선언하지 않아도 스프링이해준다. 
	@Autowired 
	BoardDto bdto;
	// 다형성 
	//BService bservice1 = new BServiceImpl();
	//BService bservice2 = new BServiceImpl2();
	BServiceImpl bservice1 = new BServiceImpl();
	BServiceImpl2 bservice2 = new BServiceImpl2();
	//BService bservice3 = new BServiceImpl3();
	@Autowired   // @Service가 붙은 위치에 적용이 됨. 
	BService bservice;
	
	
	@RequestMapping("/index")
	public String index() {
		bdto.setBno(1);
		System.out.println(bdto.getBno());
		System.out.println(bservice1.add());   // 15
		System.out.println(bservice2.add());  // 300 
		System.out.println(bservice.add());  // 15
		return "index";
	}

}
