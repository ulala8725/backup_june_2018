package com.studyspring.dogshop.vo;

public class Cart {
	private int id;
	private String image;
	private String kind;
	private int price;
	private int qty;
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", image=" + image + ", kind=" + kind + ", price=" + price + ", qty=" + qty + "]";
	}

}
