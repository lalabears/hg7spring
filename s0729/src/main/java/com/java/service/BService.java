package com.java.service;

import java.util.ArrayList;

import com.java.dto.Board;
import com.java.dto.GalleryList;

public interface BService {

	ArrayList<Board> selectAll();

	Board insertBoard(Board board);

	void insertGallery(GalleryList gallerylist);

}
