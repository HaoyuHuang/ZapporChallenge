package com.photoshare.zappor.challenge.product;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.photoshare.zappor.challenge.common.ZapposRestRequest;
import com.photoshare.zappor.challenge.main.ZapporRestUtils;

public class SearchProductByPriceRequest extends ZapposRestRequest {

	private double price;

	private boolean includeOnSale;

	private boolean onSale;

	private int limit = 10;

	private String term;

	private SearchProductByPriceRequest(double price, boolean includeOnSale,
			boolean onSale, int limit, String term) {
		super();
		this.price = price;
		this.includeOnSale = includeOnSale;
		this.onSale = onSale;
		this.limit = limit;
		this.term = term;
	}

	@Override
	public String action() {
		StringBuilder builder = new StringBuilder();
		builder.append(ZapporRestUtils.SEARCH_ACTION);
		builder.append("?term=");
		if (term != null) {
			builder.append(term);
		}

		builder.append("&filters=");

		StringBuilder value = new StringBuilder();
		value.append("{\"price\":");
		value.append("[\"");
		value.append(price);
		value.append("\"]");

		if (includeOnSale) {
			value.append(",\"onSale\":");
			value.append(":[\"");
			value.append(onSale);
			value.append("\"]");
		}

		value.append("}");
		
		try {
			builder.append(URLEncoder.encode(value.toString(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		builder.append("&limit=");
		builder.append(limit);
		return builder.toString();
	}

	public static class SearchProductByPriceRequestBuilder {
		private double price;

		private boolean includeOnSale;

		private boolean onSale;

		private int limit = 10;

		private String term;

		public SearchProductByPriceRequestBuilder Price(double price) {
			this.price = price;
			return this;
		}

		public SearchProductByPriceRequestBuilder IncludeOnSale(
				boolean includeOnSale) {
			this.includeOnSale = includeOnSale;
			return this;
		}

		public SearchProductByPriceRequestBuilder OnSale(boolean onSale) {
			this.onSale = onSale;
			return this;
		}

		public SearchProductByPriceRequestBuilder Limit(int limit) {
			this.limit = limit;
			return this;
		}

		public SearchProductByPriceRequestBuilder Term(String term) {
			this.term = term;
			return this;
		}

		public SearchProductByPriceRequest build() {
			return new SearchProductByPriceRequest(price, includeOnSale,
					onSale, limit, term);
		}
	}

	public static void main(String[] args) {
		SearchProductByPriceRequest productByPriceRequest = new SearchProductByPriceRequest.SearchProductByPriceRequestBuilder()
				.Price(39.99).IncludeOnSale(true).OnSale(true).Limit(100)
				.build();
		System.out.println(productByPriceRequest.action());
	}

}
