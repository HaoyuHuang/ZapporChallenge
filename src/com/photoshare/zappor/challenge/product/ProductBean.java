package com.photoshare.zappor.challenge.product;

import org.json.JSONObject;

public class ProductBean {

	private long productId;

	private String price;

	private long styleId;

	private String productUrl;

	private long colorId;

	private String productName;

	private String brandName;

	private String thumbnailImageUrl;

	private String pecentOff;

	private String originalPrice;
	
	public ProductBean() {
		
	}

	public ProductBean(long productId, String price, long styleId,
			String productUrl, long colorId, String productName,
			String brandName, String thumbnailImageUrl, String pecentOff,
			String originalPrice) {
		super();
		this.productId = productId;
		this.price = price;
		this.styleId = styleId;
		this.productUrl = productUrl;
		this.colorId = colorId;
		this.productName = productName;
		this.brandName = brandName;
		this.thumbnailImageUrl = thumbnailImageUrl;
		this.pecentOff = pecentOff;
		this.originalPrice = originalPrice;
	}

	public ProductBean parse(JSONObject obj) {
		this.styleId = Long.parseLong(obj.optString("styleId"));
		this.price = obj.optString("price");
		this.brandName = obj.optString("brandName");
		this.colorId = Long.parseLong(obj.optString("colorId"));
		this.pecentOff = obj.optString("percentOff");
		this.productId = Long.parseLong(obj.optString("productId"));
		this.productName = obj.optString("productName");
		this.productUrl = obj.optString("productUrl");
		this.thumbnailImageUrl = obj.optString("thumbnailImageUrl");
		this.originalPrice = obj.optString("originalPrice");
		return this;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setStyleId(long styleId) {
		this.styleId = styleId;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public void setColorId(long colorId) {
		this.colorId = colorId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public void setThumbnailImageUrl(String thumbnailImageUrl) {
		this.thumbnailImageUrl = thumbnailImageUrl;
	}

	public void setPecentOff(String pecentOff) {
		this.pecentOff = pecentOff;
	}

	public String getPrice() {
		return price;
	}

	public long getProductId() {
		return productId;
	}

	public long getStyleId() {
		return styleId;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public long getColorId() {
		return colorId;
	}

	public String getProductName() {
		return productName;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getThumbnailImageUrl() {
		return thumbnailImageUrl;
	}

	public String getPecentOff() {
		return pecentOff;
	}

	@Override
	public String toString() {
		return "ProductBean [productId=" + productId + ", price=" + price
				+ ", styleId=" + styleId + ", productUrl=" + productUrl
				+ ", colorId=" + colorId + ", productName=" + productName
				+ ", brandName=" + brandName + ", thumbnailImageUrl="
				+ thumbnailImageUrl + ", pecentOff=" + pecentOff
				+ ", originalPrice=" + originalPrice + "]";
	}

}
