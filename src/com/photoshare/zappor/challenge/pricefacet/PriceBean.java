package com.photoshare.zappor.challenge.pricefacet;

import org.json.JSONObject;

public class PriceBean {
	private double name;

	private int count;
	
	public PriceBean(double name, int count) {
		super();
		this.name = name;
		this.count = count;
	}

	public PriceBean() {
		
	}
	
	public PriceBean parse(JSONObject obj) {
		this.name = Double.parseDouble(obj.optString("name"));
		this.count = Integer.parseInt(obj.optString("count"));
		return this;
	}

	public double getName() {
		return name;
	}

	public void setName(double name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
