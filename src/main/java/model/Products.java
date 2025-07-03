package model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_DEFAULT)
public class Products {

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("price")
	private Double price;

	@JsonProperty("description")
	private String description;

	@JsonProperty("stock")
	private Integer stock;
	
	@JsonProperty("categoryId")
	private Long categoryId;

	@JsonFormat(pattern= "yyyy-MM-dd")
	private Date expDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

//	public LocalDate getExpDate() {
//		return expDate;
//	}
//
//	public void setExpDate(LocalDate expDate) {
//		this.expDate = expDate;
//	}

	
	
}
