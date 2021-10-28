package com.webservice.app.wservice.io.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "books", schema = "book-store-dev")
@Getter
@Setter
@ToString
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "book_id", nullable = false, unique = true, length = 35)
	private String bookId;
	
	@Column(length = 255)
	private String sku;
	
	@Column(nullable = false, length = 75)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name ="unit_price")
	private BigDecimal unitPrice;
	
	@Column(name = "image_url", length = 255)
	private String imageUrl;
	
	@Column(columnDefinition = "boolean default true")
	private boolean active;
	
	@Column(name = "units_in_stock")
	private int unitsinStock;
	
	@Column(name ="date_create")
	private LocalDateTime createOn;
	
	@Column(name = "last_update")
	private LocalDateTime updateOn;
	
	@ManyToOne
	@JoinColumn(name ="category_id", nullable = false)
	private BookCategory category;
}


















