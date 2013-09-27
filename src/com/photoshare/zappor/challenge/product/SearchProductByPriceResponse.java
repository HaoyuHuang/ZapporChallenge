package com.photoshare.zappor.challenge.product;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.photoshare.zappor.challenge.common.HttpResponse;

public class SearchProductByPriceResponse extends HttpResponse {

	List<ProductBean> list;

	public SearchProductByPriceResponse(String response) {
		super(response);
	}

	public List<ProductBean> parse(JSONObject obj) {
		list = new ArrayList<ProductBean>();

		JSONArray array = obj.optJSONArray("results");
		for (int i = 0; i < array.length(); i++) {
			try {
				list.add(new ProductBean().parse(array.getJSONObject(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
