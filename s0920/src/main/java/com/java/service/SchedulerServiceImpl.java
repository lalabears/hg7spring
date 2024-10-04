package com.java.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerServiceImpl implements SchedulerService {

	@Scheduled(cron = "0 0 0 * * *")	// 매일 00시 정각
	public void perDay() throws Exception {
		
		System.out.println("매일 00시");
		
	}
	
	
}
