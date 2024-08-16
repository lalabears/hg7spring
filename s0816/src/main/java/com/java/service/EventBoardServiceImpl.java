package com.java.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.EventBoard;
import com.java.dto.Page;
import com.java.mapper.EventBoardMapper;

@Service
public class EventBoardServiceImpl implements EventBoardService {

	@Autowired EventBoardMapper ebmapper;
	@Override
	public HashMap<String, Object> selectAllEventBoard(Page pageDto) {
		HashMap<String, Object> map = new HashMap<>();
		pageDto = pageMethod(pageDto) ; // pageDto 새로운 정보로 업데이트 
		ArrayList<EventBoard> list = ebmapper.selectAllEventBoard(pageDto);
		map.put("list", list);
		map.put("pageDto", pageDto);
		return map;
	}
	
	private Page pageMethod(Page pageDto) {
		// 전체 게시글 수 저장 
		pageDto.setListCount(   ebmapper.selectListCount()   );
		// 최대 넘버링 페이지 
		pageDto.setMaxPage( (int)Math.ceil( (double)pageDto.getListCount()/10  ));
		// 시작 넘버링페이지
		pageDto.setStartPage(  (int)((pageDto.getPage()-1)/10)*10 +1     );
		// 끝 넘버링 페이지 
		pageDto.setEndPage(  pageDto.getStartPage() + 10 - 1   );
		// 게시글 시작번호 
		pageDto.setStartRow( (pageDto.getPage() - 1 ) * 10 + 1   );
		// 게시글 끝나는 번호
		pageDto.setEndRow( pageDto.getStartRow() + 10 - 1 );
		
		System.out.println(pageDto.getStartPage());
		System.out.println(pageDto.getEndPage());
		System.out.println(pageDto.getMaxPage());
		return pageDto;
	}
	
	

}
