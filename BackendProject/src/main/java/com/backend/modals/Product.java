package com.backend.modals;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="ProductTab")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int productId;
	
	@NotEmpty(message="Product Name is mandatory")
	private String productName;
	
	@NotNull(message="Price is Mandatory")
	@Min(value=200)
	private Double price;
	
	@NotNull(message="Quantity is Mandatory")
	@Min(value=1)
	private Integer quantity;
	
	@NotEmpty
	private String description;
	
	private int categoryId;
	private int supplierId;
	
	@ManyToOne
	@JoinColumn(name="categoryId",insertable=false,updatable=false)
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="supplierId",insertable=false,updatable=false)
	private Supplier supplier;
	
	@Transient
	private MultipartFile pimage1;
	private String imgName1;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public MultipartFile getPimage1() {
		return pimage1;
	}
	public void setPimage1(MultipartFile pimage1) {
		this.pimage1 = pimage1;
	}
	public String getImgName1() {
		return imgName1;
	}
	public void setImgName1(String imgName1) {
		this.imgName1 = imgName1;
	}
	
	
	
	
	
}
