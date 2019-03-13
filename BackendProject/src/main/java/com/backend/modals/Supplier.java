package com.backend.modals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="SupplierTab")
public class Supplier {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int supplierId;
	
	@NotEmpty(message="Supplier Name Is Mandatory")
	private String supplierName;
	
	@NotEmpty(message="Supplier Address Is Mandatory")
	private String supplierAddress;
	
	@OneToMany(mappedBy="supplier",cascade=CascadeType.ALL)
	private List<Product> products=new ArrayList<>();
	
	
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierAddress() {
		return supplierAddress;
	}
	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}
	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", supplierName=" + supplierName + ", supplierAddress="
				+ supplierAddress + "]";
	}
	
	
	
	


}
