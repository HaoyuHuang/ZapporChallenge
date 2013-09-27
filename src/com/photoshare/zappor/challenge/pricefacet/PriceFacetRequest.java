package com.photoshare.zappor.challenge.pricefacet;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.photoshare.zappor.challenge.common.ZapposRestRequest;
import com.photoshare.zappor.challenge.main.ZapporRestUtils;

public class PriceFacetRequest extends ZapposRestRequest {

	// private static final String action =
	// "/Search?term=&includes=["facets","onSale"]&filters={"onSale":["true"]}&facets=["price"]&facetSort=name&excludes=["results"]";

	private boolean includeOnSale;

	private boolean facetSortByName;

	private boolean excludeResults;

	private String term;

	private boolean onSale;

	private boolean filters;

	private boolean filterOnSale;

	public PriceFacetRequest(boolean includeOnSale, boolean facetSortByName,
			boolean excludeResults, String term, boolean onSale,
			boolean filters, boolean filterOnSale) {
		super();
		this.includeOnSale = includeOnSale;
		this.facetSortByName = facetSortByName;
		this.excludeResults = excludeResults;
		this.term = term;
		this.onSale = onSale;
		this.filters = filters;
		this.filterOnSale = filterOnSale;
	}

	public String action() {
		StringBuilder action = new StringBuilder();
		action.append(ZapporRestUtils.SEARCH_ACTION);
		action.append("?term=");
		if (term != null) {
			action.append(term);
		}
		action.append("&includes=");
		StringBuilder includeValue = new StringBuilder();
		includeValue.append("[\"facets\",");
		if (includeOnSale) {
			includeValue.append("\"onSale\",");
		}
		includeValue.deleteCharAt(includeValue.length() - 1);
		includeValue.append("]");

		try {
			action.append(URLEncoder.encode(includeValue.toString(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (filters) {
			action.append("&filters=");
			includeValue = new StringBuilder();
			includeValue.append("{");
			if (filterOnSale) {
				includeValue.append("\"onSale\":[\"");
				includeValue.append(onSale);
				includeValue.append("\"]");
			}
			includeValue.append("}");
			try {
				action.append(URLEncoder.encode(includeValue.toString(),
						"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		action.append("&facets=");

		try {
			action.append(URLEncoder.encode("[\"price\"]", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (facetSortByName) {
			action.append("&facetSort=name");
		}
		if (excludeResults) {
			action.append("&excludes=");
			try {
				action.append(URLEncoder.encode("[\"results\"]", "utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return action.toString();
	}

	public static class PriceFacetRequestBuilder {
		private boolean includeOnSale;

		private boolean facetSortByName;

		private boolean excludeResults;

		private String term;

		private boolean onSale;

		private boolean filters;

		private boolean filterOnSale;

		public PriceFacetRequestBuilder includeOnSale(boolean includeOnSale) {
			this.includeOnSale = includeOnSale;
			return this;
		}

		public PriceFacetRequestBuilder filter(boolean filters) {
			this.filters = filters;
			return this;
		}

		public PriceFacetRequestBuilder filterOnSale(boolean filterOnSale) {
			this.filterOnSale = filterOnSale;
			return this;
		}

		public PriceFacetRequestBuilder onSale(boolean onSale) {
			this.onSale = onSale;
			return this;
		}

		public PriceFacetRequestBuilder facetSortByName(boolean facetSortByName) {
			this.facetSortByName = facetSortByName;
			return this;
		}

		public PriceFacetRequestBuilder excludeResults(boolean excludeResults) {
			this.excludeResults = excludeResults;
			return this;
		}

		public PriceFacetRequestBuilder term(String term) {
			this.term = term;
			return this;
		}

		public PriceFacetRequest build() {
			return new PriceFacetRequest(includeOnSale, facetSortByName,
					excludeResults, term, onSale, filters, filterOnSale);
		}
	}

	public static void main(String[] args) {
		PriceFacetRequest request = new PriceFacetRequestBuilder()
				.excludeResults(true).facetSortByName(true).filter(true)
				.filterOnSale(true).includeOnSale(true).onSale(true).term("")
				.build();
		System.out.println(request.action());
	}
}
