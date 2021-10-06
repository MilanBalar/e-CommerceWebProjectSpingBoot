package com.sheryians.major.dto;

/*@Data*/
public class ProductsDTO {

	private long productId;
	private String name;
    private int tblCategoriesId;
    private double price;
	private double weight;
	private String description;
	private String imageName;
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTblCategoriesId() {
		return tblCategoriesId;
	}
	public void setTblCategoriesId(int tblCategoriesId) {
		this.tblCategoriesId = tblCategoriesId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}



}
