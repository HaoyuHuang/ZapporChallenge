package com.photoshare.zappor.challenge.main;

public class SelectedPrice {

	private double price;

	private int count;

	public SelectedPrice(double price, int count) {
		super();
		this.price = price;
		this.count = count;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
