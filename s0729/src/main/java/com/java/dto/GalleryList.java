package com.java.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GalleryList {
	private String galContentId;
	private String galContentTypeId;
	private String galTitle;
	private String galWebImageUrl;
	private String galCreatedtime;
	private String galModifiedtime;
	private String galPhotographyMonth;
	private String galPhotographyLocation;
	private String galPhotographer;
	private String galSearchKeyword;
}
