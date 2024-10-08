package com.java.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.Board;
import com.java.dto.GalleryList;
import com.java.mapper.BMapper;

@Service
public class BServiceImpl implements BService {
	@Autowired BMapper bmapper;
	@Override
	public ArrayList<Board> selectAll() {
		ArrayList<Board> list = bmapper.selectAll();
		return list;
	}
	@Override
	public Board insertBoard(Board board) {
		bmapper.insertBoard(board);
		Board bdto=bmapper.selectOne(board);
		return bdto;
	}
	@Override
	public void insertGallery(GalleryList gallerylist) {
		bmapper.insertGallery(gallerylist);
	}

}
