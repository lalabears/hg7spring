package com.java.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.Board;
import com.java.dto.GalleryList;

@Mapper
public interface BMapper {

	ArrayList<Board> selectAll();

	void insertBoard(Board board);

	Board selectOne(Board board);

	void insertGallery(GalleryList gallerylist);

}
