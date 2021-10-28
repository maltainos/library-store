package com.webservice.app.wservice.shared.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookCategoryDTO {

	private Long id;
	private String bookCategoryId;
	private String categoryName;
	
}
