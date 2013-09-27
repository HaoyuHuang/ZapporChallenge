package com.photoshare.zappor.challenge.pricefacet;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.photoshare.zappor.challenge.common.HttpResponse;

public class PriceFacetResponse extends HttpResponse {

	private List<PriceBean> priceList;

	public PriceFacetResponse(String response) {
		super(response);
	}

	public List<PriceBean> parse(JSONObject object) {
		priceList = new ArrayList<PriceBean>();
		JSONArray facets = object.optJSONArray("facets");
		JSONObject temp = facets.optJSONObject(0);
		JSONArray values = temp.optJSONArray("values");
		for (int i = 0; i < values.length(); i++) {
			priceList.add(new PriceBean().parse(values.optJSONObject(i)));
		}
		return priceList;
	}
}
