package com.webservice.app.wservice.ui.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookRest {

	private String bookId;
	private String sku;
	private String name;
	private String description;
	private BigDecimal unitPrice;
	private String imageUrl;
	private boolean active;
	private int unitsinStock;
	private LocalDateTime createOn;
	private LocalDateTime updateOn;
	private BookCategoryRest category;
}
